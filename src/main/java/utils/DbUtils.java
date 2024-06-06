package utils;

import io.github.cdimascio.dotenv.Dotenv;
import java.net.FileNameMap;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DbUtils {

    private static Dotenv dotenv = Dotenv.configure().directory("D:\\Document\\FPT\\HK5_SU24\\ISP392\\Project\\test\\.env").load();
//    private static final String DB_NAME = "ISP392";
//    private static final String DB_USERNAME = "sa";
//    private static final String DB_PASS = "12345";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + dotenv.get("DB_NAME") + ";encrypt=true;trustServerCertificate=true";
        conn = DriverManager.getConnection(url, dotenv.get("DB_USERNAME"), dotenv.get("DB_PASSWORD"));
        return conn;
    }

    public static void main(String[] args) {
        System.out.println(dotenv.get("DB_USERNAME"));
//        System.out.println(Path.of("").toAbsolutePath().toString());
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
