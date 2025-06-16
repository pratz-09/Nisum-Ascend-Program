package com.nisum.product.repository;

import com.nisum.product.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    
    @Autowired
    private com.nisum.product.repository.ProductRepository productRepository;
    
    private Product product1;
    private Product product2;
    private Product product3;
    
    @BeforeEach
    void setUp() {
        product1 = new Product("Laptop", "High-performance laptop", 
                new BigDecimal("999.99"), 10, "Electronics");
        product2 = new Product("Smartphone", "Latest Android phone", 
                new BigDecimal("599.99"), 25, "Electronics");
        product3 = new Product("T-Shirt", "Cotton t-shirt", 
                new BigDecimal("19.99"), 50, "Clothing");
        
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
    
    @Test
    void testFindByCategory() {
        List<Product> electronics = productRepository.findByCategory("Electronics");
        
        assertThat(electronics).hasSize(2);
        assertThat(electronics).extracting(Product::getName)
                .containsExactlyInAnyOrder("Laptop", "Smartphone");
    }
    
    @Test
    void testFindByPriceBetween() {
        BigDecimal min = new BigDecimal("500");
        BigDecimal max = new BigDecimal("1000");
        List<Product> products = productRepository.findByPriceBetween(min, max);
        
        assertThat(products).hasSize(2);
        assertThat(products).extracting(Product::getName)
                .containsExactlyInAnyOrder("Laptop", "Smartphone");
    }
    
    @Test
    void testFindByNameContainingIgnoreCase() {
        List<Product> products = productRepository.findByNameContainingIgnoreCase("phone");
        
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Smartphone");
    }
    
    @Test
    void testFindByNameAndCategory() {
        List<Product> products = productRepository.findByNameAndCategory("Laptop", "Electronics");
        
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Laptop");
    }
    
    @Test
    void testFindLowStockProducts() {
        List<Product> lowStockProducts = productRepository.findLowStockProducts(20);
        
        assertThat(lowStockProducts).hasSize(1);
        assertThat(lowStockProducts.get(0).getName()).isEqualTo("Laptop");
    }
}