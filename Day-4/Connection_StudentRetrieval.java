import java.sql.*;

public class Connection_StudentRetrieval {
    public static void main(String[] args) {
        // Step 1: Database URL, username and password
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        // Step 2: Try with resources for automatic closing
        try (
                Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                Statement statement = connection.createStatement();) {
            System.out.println("Connected to database successfully!");

            // Example: Create and execute a simple SELECT query
            String sql = "SELECT * FROM Student";
            ResultSet result = statement.executeQuery(sql);

            // Step 3: Process the result
            while (result.next()) {
                int id = result.getInt("studentID");
                String name = result.getString("FirstName") + " " + result.getString("LastName");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}