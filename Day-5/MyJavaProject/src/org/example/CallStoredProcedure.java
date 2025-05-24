package org.example;

import java.sql.*;
import java.util.Scanner;

public class CallStoredProcedure {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            System.out.println("Connected to database successfully!");

            try (Statement statement = connection.createStatement()) {
                createStoredProcedure(statement);
            }

            System.out.print("Enter student ID to search: ");
            int studentId = scanner.nextInt();

            String procedureCall = "{CALL getStudentById(?)}";

            try (CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
                callableStatement.setInt(1, studentId);

                ResultSet resultSet = callableStatement.executeQuery();

                System.out.println("\n--- Student Details ---");
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
                    System.out.println("No student found with ID: " + studentId);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed!");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void createStoredProcedure(Statement statement) throws SQLException {
        // Drop procedure if it exists
        try {
            statement.execute("DROP PROCEDURE IF EXISTS getStudentById");
        } catch (SQLException e) {
            System.out.println("Warning: " + e.getMessage());
        }

        // Create the stored procedure
        String createProcedure = "CREATE PROCEDURE getStudentById(IN student_id INT) " +
                "BEGIN " +
                "    SELECT * FROM student WHERE id = student_id; " +
                "END";

        statement.execute(createProcedure);
        System.out.println("Stored procedure 'getStudentById' created successfully!");
    }
}