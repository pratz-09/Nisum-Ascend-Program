
import java.sql.*;

public class TransactionExampleSuccess {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "prateek1234";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

        System.out.println("Data before transaction");
        ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNTS");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " " + rs.getString("NAME") + " "
                    + rs.getInt("BALANCE"));
        }

        connection.setAutoCommit(false);

        try {
            statement.executeUpdate("UPDATE ACCOUNTS SET BALANCE = BALANCE + 100 WHERE NAME = 'Ayusman'");

            statement.executeUpdate("UPDATE ACCOUNTS SET BALANCE = BALANCE - 100 WHERE NAME = 'Asish'");

            connection.commit();
            System.out.println("Transaction is committed");
        } catch (Exception e) {
            System.out.println("Transaction is being rolled back");
            connection.rollback();
            e.printStackTrace();
        } finally {
            System.out.println("\nData after transaction");
            rs = statement.executeQuery("SELECT * FROM ACCOUNTS");
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + " " + rs.getString("NAME") + " "
                        + rs.getInt("BALANCE"));
            }

            rs.close();
            statement.close();
            connection.close();
        }
    }
}