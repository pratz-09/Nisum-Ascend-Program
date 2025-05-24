package org.example;

import java.sql.*;

public class TransactionExampleSuccess {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "prateek1234";

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {

            System.out.println("Data before transaction");
            try (ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNTS")) {
                while (rs.next()) {
                    System.out.println(rs.getInt("ID") + " " + rs.getString("NAME") + " " + rs.getInt("BALANCE"));
                }
            }

            connection.setAutoCommit(false);

            try {
                statement.executeUpdate("UPDATE ACCOUNTS SET BALANCE = BALANCE + 100 WHERE NAME = 'Ayusman'");
                statement.executeUpdate("UPDATE ACCOUNTS SET BALANCE = BALANCE - 100 WHERE NAME = 'Asish'");
                connection.commit();
                System.out.println("Transaction is committed");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transaction is being rolled back");
                System.out.println("Error Code: " + e.getErrorCode());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
            }

            System.out.println("\nData after transaction");
            try (ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNTS")) {
                while (rs.next()) {
                    System.out.println(rs.getInt("ID") + " " + rs.getString("NAME") + " " + rs.getInt("BALANCE"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
        }
    }
}
