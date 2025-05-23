package Assignment;

import java.util.*;

class Product {
    String name, category;
    double price;
    Product(String n, String c, double p) {
        name = n; category = c; price = p;
    }
    public String toString() {
        return name + " | " + category + " | $" + price;
    }
}

public class p14 {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Pen", "Stationery", 10),
            new Product("Notebook", "Stationery", 20),
            new Product("Apple", "Fruit", 5),
            new Product("Banana", "Fruit", 2)
        );
        products.sort(Comparator.comparing((Product p) -> p.category)
                .thenComparingDouble(p -> p.price));
        for (Product p : products) System.out.println(p);
    }
}
