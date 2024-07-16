package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.reflect.InvocationTargetException;

public class DbUtils {

    private static final String CHECK_LOG_FORMAT = "INSERT INTO %s (EmpID, %s, FieldOld, FieldNew, Action) VALUES (?, ?, ?, ?, ?)";

<<<<<<< Updated upstream
    private static Dotenv dotenv = Dotenv.configure().directory("/home/notlongfen/code/java/ISP392/.env").load();
    private static final String CHECK_LOG_FORMAT = "INSERT INTO %s (EmpID, %s, FieldOld, FieldNew, Action) VALUES (?, ?, ?, ?, ?)"; 
    private static final String CHECK_LOG_GET = "SELECT * FROM %s WHERE empID = ?";

=======
    private static Dotenv dotenv = Dotenv.configure().directory("D://Semester5//ISP//ISP392SHOP//.env").load();
    // Adjusted dotenv configuration based on your environment setup
    // private static Dotenv dotenv = Dotenv.configure().directory("D://Semester5//ISP//ISP392SHOP//.env").load();
>>>>>>> Stashed changes

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + dotenv.get("DB_NAME") + ";encrypt=true;trustServerCertificate=true";
        conn = DriverManager.getConnection(url, dotenv.get("DB_USERNAME"), dotenv.get("DB_PASSWORD"));
        return conn;
    }

    public static <T> boolean addCheckLogToDB(String tableName, String attributeName, T manageDTO)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = getConnection();

            String query = String.format(CHECK_LOG_FORMAT, tableName, attributeName);
            ptm = conn.prepareStatement(query);

            ptm.setInt(1, (int) manageDTO.getClass().getMethod("getEmpID").invoke(manageDTO));
            ptm.setInt(2, (int) manageDTO.getClass().getMethod("get" + attributeName).invoke(manageDTO));
            ptm.setString(3, manageDTO.getClass().getMethod("getOldField").invoke(manageDTO).toString());
            ptm.setString(4, manageDTO.getClass().getMethod("getNewField").invoke(manageDTO).toString());
            ptm.setString(5, manageDTO.getClass().getMethod("getAction").invoke(manageDTO).toString());

            check = ptm.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ptm);
        }
        return check;
    }

    public static <T> boolean getCheckLogFromDB(String tableName, T manageDTO){
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = getConnection();
            String query = String.format(CHECK_LOG_GET, tableName, manageDTO.getClass().getMethod("getEmpID").invoke(manageDTO));
            ptm = conn.prepareStatement(query);
            ptm.setInt(1, (int) manageDTO.getClass().getMethod("getEmpID").invoke(manageDTO));
            rs = ptm.executeQuery();
            check = rs.next();
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, ptm, rs);
        }
        return check;
    }

    public static void closeConnection(Connection conn, PreparedStatement ptm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (ptm != null) {
                ptm.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement ptm) {
        try {
            if (ptm != null) {
                ptm.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(dotenv.get("DB_USERNAME"));

        // Example of password hashing with BCrypt
        String password = "super secret";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Hashed password: " + hash);

        // Example of password verification with BCrypt
        boolean isMatched = BCrypt.checkpw(password, hash);
        System.out.println("Password matches: " + isMatched);

        // Test connection
        try {
            Connection conn = DbUtils.getConnection();
            if (conn != null) {
                System.out.println("Connected to database successfully.");
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Example of generating a pie chart using JFreeChart
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("A", 2.0);
        dataset.setValue("B", 3.0);
        dataset.setValue("C", 4.0);
        dataset.setValue("D", 5.0);
        JFreeChart chart = ChartFactory.createPieChart("Test Chart", dataset, true, true, true);
        int width = 640;
        int height = 480;
        File pieChart = new File("./PieChart.jpeg");
        try {
            ChartUtils.saveChartAsJPEG(pieChart, chart, width, height);
            System.out.println("Pie chart saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
