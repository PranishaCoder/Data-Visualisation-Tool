import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/datavisualisationdb";
    private static final String USER = "root";
    private static final String PASSWORD = "pranisha";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; // For MySQL Connector/J 5.1

    public static Connection getConnection() throws SQLException {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            // This will throw a more specific error if the JAR is truly missing
            throw new SQLException("MySQL JDBC Driver not found. Please ensure the connector JAR is in the classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
} 