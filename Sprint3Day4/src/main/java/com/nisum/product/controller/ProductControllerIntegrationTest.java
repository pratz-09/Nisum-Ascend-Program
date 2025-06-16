package com.nisum.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.product.entity.Product;
import com.nisum.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureTestMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestMvc
public class ProductControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }
    
    @Test
    void testAddProduct() throws Exception {
        Product product = new Product("Laptop", "Gaming laptop", 
                new BigDecimal("1299.99"), 5, "Electronics");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1299.99))
                .andExpected(MockMvcResultMatchers.jsonPath("$.category").value("Electronics"));
    }
    
    @Test
    void testGetAllProducts() throws Exception {
        Product product1 = new Product("Laptop", "Gaming laptop", 
                new BigDecimal("1299.99"), 5, "Electronics");
        Product product2 = new Product("Mouse", "Wireless mouse", 
                new BigDecimal("29.99"), 20, "Electronics");
        
        productRepository.save(product1);
        productRepository.save(product2);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpected(MockMvcResultMatchers.status().isOk())
                .andExpected(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpected(MockMvcResultMatchers.jsonPath("$[*].name", Matchers.containsInAnyOrder("Laptop", "Mouse")));
    }
    
    @Test
    void testUpdateProduct() throws Exception {
        Product product = new Product("Laptop", "Gaming laptop", 
                new BigDecimal("1299.99"), 5, "Electronics");
        Product savedProduct = productRepository.save(product);
        
        savedProduct.setPrice(new BigDecimal("1199.99"));
        
        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/{id}", savedProduct.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpected(MockMvcResultMatchers.jsonPath("$.price").value(1199.99));
    }
    
    @Test
    void testGetProductsByCategory() throws Exception {
        Product product1 = new Product("Laptop", "Gaming laptop", 
                new BigDecimal("1299.99"), 5, "Electronics");
        Product product2 = new Product("T-Shirt", "Cotton t-shirt", 
                new BigDecimal("19.99"), 20, "Clothing");
        
        productRepository.save(product1);
        productRepository.save(product2);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/category/Electronics"))
                .andExpected(MockMvcResultMatchers.status().isOk())
                .andExpected(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpected(MockMvcResultMatchers.jsonPath("$[0].name").value("Laptop"));
    }
}