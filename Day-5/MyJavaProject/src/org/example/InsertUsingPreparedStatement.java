package org.example;

import java.sql.*;

public class InsertUsingPreparedStatement {
    public static void main(String[] args) {
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        try (
                // Establish database connection
                Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                // Create a statement for table operations
                Statement statement = connection.createStatement();) {
            System.out.println("Connected to database successfully!");

            // Drop students table if it exists
            String dropTableSQL = "DROP TABLE IF EXISTS student";
            statement.executeUpdate(dropTableSQL);
            System.out.println("Dropped existing student table if it existed.");

            // Create students table
            String createTableSQL = "CREATE TABLE student (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "age INT)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'student' is created.");

            // Student data to insert
            int[] studentIds = { 1, 2, 3 };
            String[] studentNames = { "Ayush Pradhan", "Binaya Bhushan Sahoo", "Asish Kumar Lenka" };
            int[] ages = { 20, 22, 19 };

            // SQL command for prepared statement
            String insertSQL = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";

            // Create a PreparedStatement object
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

                // Insert multiple records using the same prepared statement
                for (int i = 0; i < studentIds.length; i++) {
                    // Set parameters for each student
                    preparedStatement.setInt(1, studentIds[i]); // First parameter (id)
                    preparedStatement.setString(2, studentNames[i]); // Second parameter (name)
                    preparedStatement.setInt(3, ages[i]); // Third parameter (age)

                    // Execute the prepared statement
                    preparedStatement.executeUpdate();
                    System.out.println("Inserted student: " + studentNames[i]);
                }

                System.out.println("\nAll student records inserted successfully!");
            }

            // Display all records in the students table
            System.out.println("\n--- All Student in Database ---");
            ResultSet allStudent = statement.executeQuery("SELECT * FROM student");

            // Iterate through all records
            boolean hasRecords = false;
            while (allStudent.next()) {
                hasRecords = true;
                int id = allStudent.getInt("id");
                String name = allStudent.getString("name");
                int age = allStudent.getInt("age");

                System.out.println(id + "\t" + name + "\t" + age);
            }

            if (!hasRecords) {
                System.out.println("No student found in the table.");
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed!");
            e.printStackTrace();
        }
    }
}