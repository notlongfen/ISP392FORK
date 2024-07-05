package utils;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;

public class DbUtils {


    private static Dotenv dotenv = Dotenv.configure().directory("D:\\FPT\\K5\\ISP392\\ISP392_Test").load();

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + dotenv.get("DB_NAME") + ";encrypt=true;trustServerCertificate=true";
        conn = DriverManager.getConnection(url, dotenv.get("DB_USERNAME"), dotenv.get("DB_PASSWORD"));
        return conn;
    }

    public static void closeConnection(Connection conn, PreparedStatement ptm, ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
            }
            
            if(ptm != null) {
                ptm.close();
            }

            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(dotenv.get("DB_USERNAME"));
//        System.out.println(Path.of("").toAbsolutePath().toString());
        String password = "super secret";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        String secpasss = "super secret";
        String hash1 = BCrypt.hashpw(secpasss, BCrypt.gensalt());
        System.out.println(hash);
        System.out.println(hash1);
        boolean isMatched = BCrypt.checkpw(password, hash);
        System.out.println(isMatched);
        System.out.println(hash == secpasss);
        // Test connection
        try {
            Connection conn = DbUtils.getConnection();
            if (conn != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
