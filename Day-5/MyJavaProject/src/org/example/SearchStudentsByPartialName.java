package org.example;

import java.sql.*;
import java.util.Scanner;

public class SearchStudentsByPartialName {
    public static void main(String[] args) {
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        // Let's add a student named "Ayush Kumar" for our search example
        try (
                // Establish database connection
                Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                // Create a statement for initial operations
                Statement statement = connection.createStatement();
                // Create scanner for user input
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to database successfully!");

            // First add an "Ayush" student if not exists
            try {
                // Check if we already have a student with ID 4
                ResultSet checkStudent = statement.executeQuery("SELECT * FROM students WHERE id = 4");
                if (!checkStudent.next()) {
                    // Insert Ayush if not already in the table
                    String insertSQL = "INSERT INTO students (id, name, age) VALUES (4, 'Ayush Kumar', 21)";
                    statement.executeUpdate(insertSQL);
                    System.out.println("Added student 'Ayush Kumar' with ID 4 for search example");
                }
                checkStudent.close();
            } catch (SQLException e) {
                System.out.println("Error checking/adding test student: " + e.getMessage());
            }

            // Display all students
            System.out.println("\n--- All Students in Database ---");
            displayAllStudents(statement);

            // Get partial name from user
            System.out.print("\nSearching for names starting with 'Ayus': ");
            String searchPrefix = "Ayus";

            // SQL with LIKE for prefix pattern matching
            String searchSQL = "SELECT * FROM students WHERE name LIKE ?";

            try (PreparedStatement searchStatement = connection.prepareStatement(searchSQL)) {
                // Set parameter with wildcard - prefix matching only
                // 'Ayus%' matches any name that starts with 'Ayus'
                searchStatement.setString(1, searchPrefix + "%");

                // Execute search query
                ResultSet resultSet = searchStatement.executeQuery();

                // Display search results
                System.out.println("\n--- Students with Names Starting with '" + searchPrefix + "' ---");
                System.out.println("ID\tName\t\tAge");
                System.out.println("----------------------------------");

                boolean found = false;
                while (resultSet.next()) {
                    found = true;
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");

                    System.out.println(id + "\t" + name + "\t" + age);
                }

                if (!found) {
                    System.out.println("No students found with names starting with '" + searchPrefix + "'");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to display all student records
    private static void displayAllStudents(Statement statement) throws SQLException {
        ResultSet allStudents = statement.executeQuery("SELECT * FROM students");

        // Print column headers
        System.out.println("ID\tName\t\tAge");
        System.out.println("----------------------------------");

        // Iterate through all records
        boolean hasRecords = false;
        while (allStudents.next()) {
            hasRecords = true;
            int id = allStudents.getInt("id");
            String name = allStudents.getString("name");
            int age = allStudents.getInt("age");

            System.out.println(id + "\t" + name + "\t" + age);
        }

        if (!hasRecords) {
            System.out.println("No students found in the table.");
        }

        // Close the ResultSet
        allStudents.close();
    }
}