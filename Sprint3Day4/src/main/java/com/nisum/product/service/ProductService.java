package com.nisum.product.service;

import com.nisum.product.entity.Product;
import com.nisum.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    public List<Product> findByPriceBetween(BigDecimal min, BigDecimal max) {
        return productRepository.findByPriceBetween(min, max);
    }
    
    public List<Product> findByNameContainingIgnoreCase(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Product> findByNameAndCategory(String name, String category) {
        return productRepository.findByNameAndCategory(name, category);
    }
}