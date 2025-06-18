
package com.example.project.product;

public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
