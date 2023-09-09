import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserDao {

    private static final String INSERT_USER_SQL = "INSERT INTO Employs (name, email, country, joinDate, password, batch) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String READ_USER_SQL = "Select * from Employs";

    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, country, joinDate, password, batch FROM Employs WHERE id = ?";

    private static final String UPDATE_USER_SQL = "UPDATE Employs SET name = ?, email = ?, country = ?, joinDate = ?, password = ?, batch = ? WHERE id = ?";

    private static final String checkIdQuery = "SELECT id FROM Employs WHERE id = ?";

    private static final String DELETE_USER_SQL = "DELETE FROM Employs WHERE id = ?";

    public static void main(String[] args) throws Exception {
        System.out.println("Main Started...........");

        try {
            Connection connection = JDBCConnection.getConnections();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose an operation:");
                System.out.println("1. Insert");
                System.out.println("2. Read");
                System.out.println("3. Read by Id");
                System.out.println("4. Update");
                System.out.println("5. Delete");
                System.out.println("6. Exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        insertRecord(connection, scanner);
                        break;

                    case 2:
                        readRecords(connection, scanner);
                        break;

                    case 3:
                        readById(connection, scanner);
                        break;

                    case 4:
                        UpdateRecords(connection, scanner);
                        break;

                    case 5:
                        deleteRecords(connection, scanner);
                        break;

                    case 6:
                        scanner.close();
                        connection.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Insert Records
    private static void insertRecord(Connection connection, Scanner scanner) {

        try {
            System.out.println("Enter Name : ");
            String name = scanner.next();
            System.out.println("Enter Email : ");
            String email = scanner.next();
            System.out.println("Enter Country : ");
            String country = scanner.next();
            System.out.println("Enter join date (yyyy-MM-dd) : ");
            String joinDatestr = scanner.next();
            Date joinDate = new SimpleDateFormat("yyyy-MM-dd").parse(joinDatestr);
            System.out.print("Enter password : ");
            String password = scanner.next();
            System.out.print("Enter batch : ");
            String batch = scanner.next();

            PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, country);
            ps.setDate(4, new java.sql.Date(joinDate.getTime()));
            ps.setString(5, password);
            ps.setString(6, batch);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully...............");
            } else {
                System.out.println("Failed to insert record.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Read Records
    private static void readRecords(Connection connection, Scanner scanner) {

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(READ_USER_SQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String joinDate = rs.getString("joinDate");
                String password = rs.getString("password");
                String batch = rs.getString("batch");

                System.out.println("ID : " + id);
                System.out.println("Name : " + name);
                System.out.println("Email : " + email);
                System.out.println("Country : " + country);
                System.out.println("Join Date : " + joinDate);
                System.out.println("Password : " + password);
                System.out.println("Batch : " + batch);
                System.out.println("...................................");

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    // Read by id Database
    private static void readById(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Your Id : ");
            int id = scanner.nextInt();
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID);
            ps.setInt(1, id);
            if (isIdExists(connection, id)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String country = rs.getString("country");
                    String joinDate = rs.getString("joinDate");
                    String password = rs.getString("password");
                    String batch = rs.getString("batch");
                    System.out.println("ID : " + id);
                    System.out.println("Name : " + name);
                    System.out.println("Email : " + email);
                    System.out.println("Country : " + country);
                    System.out.println("Join Date : " + joinDate);
                    System.out.println("Password : " + password);
                    System.out.println("Batch : " + batch);
                    System.out.println("...................................");
                }
            } else {
                System.out.println("ID not found in the database. Update aborted.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean isIdExists(Connection connection, int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(checkIdQuery);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    // Update Records in Database
    private static void UpdateRecords(Connection connection, Scanner scanner) {

        try {
            System.out.println("Update Your Records by id : ");
            System.out.println("Enter your Id : ");
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_SQL);
            int id = scanner.nextInt();
            if (isIdExists(connection, id)) {
                System.out.println("Enter Name : ");
                String name = scanner.next();
                System.out.println("Enter Email : ");
                String email = scanner.next();
                System.out.println("Enter Country : ");
                String country = scanner.next();
                System.out.println("Enter join date (yyyy-MM-dd) : ");
                String joinDatestr = scanner.next();
                Date joinDate = new SimpleDateFormat("yyyy-MM-dd").parse(joinDatestr);
                System.out.print("Enter password : ");
                String password = scanner.next();
                System.out.print("Enter batch : ");
                String batch = scanner.next();

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, country);
                ps.setDate(4, new java.sql.Date(joinDate.getTime()));
                ps.setString(5, password);
                ps.setString(6, batch);
                ps.setInt(7, id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Records Update successful.................");

                } else {
                    System.out.println("Records are not Updated.................");
                }
            } else {
                System.out.println("ID not found in the database. Update aborted.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Delete Records in Database
    private static void deleteRecords(Connection connection, Scanner scanner) {
        try {
            System.out.println("Delete Records By Id ..");
            System.out.println("Enter your Id : ");
            int id = scanner.nextInt();
            PreparedStatement ps = connection.prepareStatement(DELETE_USER_SQL);
            ps.setInt(1, id);
            if (isIdExists(connection, id)) {
                ps.executeUpdate();
            } else {
                System.out.println("ID not found in the database. Update aborted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
