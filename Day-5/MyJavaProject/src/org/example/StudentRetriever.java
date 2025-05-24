package org.example;

import java.sql.*;

public class StudentRetriever {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/jdbc";
        String dbUser = "root";
        String dbPassword = "prateek1234";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("--------------------------------------");
            System.out.printf("%-5s %-20s %-5s\n", "ID", "NAME", "AGE");
            System.out.println("--------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.printf("%-5d %-20s %-5d\n", id, name, age);
            }

            System.out.println("--------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}