package org.example;

import java.sql.*;
import java.util.Scanner;

public class StudentRecordManager {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            System.out.println("Connected to database successfully!");

            // Display all students before operations
            displayAllStudents(connection);

            // PART 1: Update a student's name
            System.out.println("\n--- UPDATE STUDENT NAME ---");
            System.out.print("Enter student ID to update: ");
            int updateId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();

            // Update student name using PreparedStatement
            String updateSQL = "UPDATE student SET name = ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {
                updateStatement.setString(1, newName);
                updateStatement.setInt(2, updateId);

                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Student with ID " + updateId + " updated successfully!");
                } else {
                    System.out.println("No student found with ID " + updateId);
                }
            }

            // Display students after update
            System.out.println("\n--- AFTER UPDATE ---");
            displayAllStudents(connection);

            // PART 2: Delete a student record
            System.out.println("\n--- DELETE STUDENT ---");
            System.out.print("Enter student ID to delete: ");
            int deleteId = scanner.nextInt();

            // Delete student using PreparedStatement
            String deleteSQL = "DELETE FROM student WHERE id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
                deleteStatement.setInt(1, deleteId);

                int rowsDeleted = deleteStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Student with ID " + deleteId + " deleted successfully!");
                } else {
                    System.out.println("No student found with ID " + deleteId);
                }
            }

            // Display students after delete
            System.out.println("\n--- AFTER DELETE ---");
            displayAllStudents(connection);

        } catch (SQLException e) {
            System.out.println("Database operation failed!");
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void displayAllStudents(Connection connection) throws SQLException {
        System.out.println("\n--- All Students in Database ---");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            boolean hasRecords = false;
            while (resultSet.next()) {
                hasRecords = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.println(id + "\t" + name + "\t" + age);
            }

            if (!hasRecords) {
                System.out.println("No students found in the table.");
            }
        }
    }
}