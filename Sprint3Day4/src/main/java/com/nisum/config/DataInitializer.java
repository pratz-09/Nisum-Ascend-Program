package com.nisum.config;

import com.nisum.customer.entity.Customer;
import com.nisum.customer.repository.CustomerRepository;
import com.nisum.product.entity.Product;
import com.nisum.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize sample customers
        if (customerRepository.count() == 0) {
            customerRepository.save(new Customer("John Doe", "john.doe@example.com", LocalDate.of(2023, 1, 15)));
            customerRepository.save(new Customer("Jane Smith", "jane.smith@gmail.com", LocalDate.of(2023, 6, 20)));
            customerRepository.save(new Customer("Bob Johnson", "bob@company.com", LocalDate.of(2022, 12, 10)));
        }
        
        // Initialize sample products
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Laptop", "High-performance gaming laptop", 
                    new BigDecimal("1299.99"), 10, "Electronics"));
            productRepository.save(new Product("Smartphone", "Latest Android smartphone", 
                    new BigDecimal("699.99"), 25, "Electronics"));
            productRepository.save(new Product("T-Shirt", "Premium cotton t-shirt", 
                    new BigDecimal("24.99"), 50, "Clothing"));
            productRepository.save(new Product("Jeans", "Comfortable denim jeans", 
                    new BigDecimal("59.99"), 30, "Clothing"));
        }
    }
}