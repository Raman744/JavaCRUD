
import java.sql.DriverManager;

public class JDBCConnection {

    public static java.sql.Connection getConnections() {
        String url = "jdbc:mysql://localhost:3306/jdbcjavaguide?useSSL=false";
        String name = "root";
        String password = "root";
        java.sql.Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("connection Completed...");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
