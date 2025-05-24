
import java.sql.*;
import java.util.Scanner;

public class InsertUpdateDelete {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        try (
                Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to database successfully!");

            while (true) {
                System.out.println("\nChoose Operation:");
                System.out.println("1. Insert Student");
                System.out.println("2. Update Last Name");
                System.out.println("3. Delete Student");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice == 4)
                    break;

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter First Name: ");
                        String fname = scanner.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lname = scanner.nextLine();

                        String insertSQL = "INSERT INTO Student (StudentID, FirstName, LastName) VALUES (?, ?, ?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                            insertStmt.setInt(1, id);
                            insertStmt.setString(2, fname);
                            insertStmt.setString(3, lname);
                            int rows = insertStmt.executeUpdate();
                            System.out.println(rows + " student(s) inserted.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter ID of student to update: ");
                        int uid = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter new Last Name: ");
                        String newLname = scanner.nextLine();

                        String updateSQL = "UPDATE Student SET LastName = ? WHERE StudentID = ?";
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {
                            updateStmt.setString(1, newLname);
                            updateStmt.setInt(2, uid);
                            int rows = updateStmt.executeUpdate();
                            System.out.println(rows + " student(s) updated.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter ID of student to delete: ");
                        int did = scanner.nextInt();

                        String deleteSQL = "DELETE FROM Student WHERE StudentID = ?";
                        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL)) {
                            deleteStmt.setInt(1, did);
                            int rows = deleteStmt.executeUpdate();
                            System.out.println(rows + " student(s) deleted.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error!");
            e.printStackTrace();
        }
    }
}
