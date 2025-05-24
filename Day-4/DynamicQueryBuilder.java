
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DynamicQueryBuilder {
    static class Product {
        private int id;
        private String name;
        private double price;
        private String category;

        public Product(int id, String name, double price, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "prateek1234";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database successfully");

            String category = "Electronics";
            Double minPrice = 100.0;
            Double maxPrice = 500.0;

            List<Product> results = searchProducts(connection, category, minPrice, maxPrice);

            System.out.println("\nSearch Results:");
            for (Product product : results) {
                System.out.printf("ID: %d, Name: %s, Category: %s, Price: $%.2f%n",
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice());
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static List<Product> searchProducts(Connection connection,
            String category,
            Double minPrice,
            Double maxPrice) throws SQLException {

        List<Product> results = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM product WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        if (category != null && !category.isEmpty()) {
            queryBuilder.append(" AND category = ?");
            parameters.add(category);
        }

        if (minPrice != null) {
            queryBuilder.append(" AND price >= ?");
            parameters.add(minPrice);
        }

        if (maxPrice != null) {
            queryBuilder.append(" AND price <= ?");
            parameters.add(maxPrice);
        }

        String query = queryBuilder.toString();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String productCategory = rs.getString("category");

                    results.add(new Product(id, name, price, productCategory));
                }
            }
        }

        return results;
    }
}