package org.example;

import java.sql.*;

public class InsertDataUsingStatement {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        try (
                Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                Statement statement = connection.createStatement();) {
            System.out.println("Connected to database successfully!");

            String dropTableSQL = "DROP TABLE IF EXISTS student";
            statement.executeUpdate(dropTableSQL);

            String createTableSQL = "CREATE TABLE student (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "age INT)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'student' is created.");

            // SQL command for inserting a new student record
            String insertSQL = "INSERT INTO student (id, name, age) VALUES " +
                    "(1, 'Ayusman Pradhan', 21),(2, 'Asish Pradhan', 22),(3, 'Jagan Hota', 23)";

            // Execute the insert statement
            int rowsAffected = statement.executeUpdate(insertSQL);

            // Check if insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Student record inserted successfully!");
                System.out.println("Rows affected: " + rowsAffected);

                // Display the inserted record
                ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
                while (resultSet.next()) {
                    System.out.println(
                            "ID=" + resultSet.getInt("id") + ", " +
                                    "Name=" + resultSet.getString("name") + ", " +
                                    "Age=" + resultSet.getInt("age"));
                }
            } else {
                System.out.println("Failed to insert student record.");
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed!");
            e.printStackTrace();
        }
    }
}