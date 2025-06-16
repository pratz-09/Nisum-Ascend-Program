package com.nisum.product.repository;

import com.nisum.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find products by category
    List<Product> findByCategory(String category);
    
    // Find products by price range
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    
    // Find products by name containing (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Custom JPQL query to search by name and category
    @Query("SELECT p FROM Product p WHERE " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:category IS NULL OR LOWER(p.category) = LOWER(:category))")
    List<Product> findByNameAndCategory(@Param("name") String name, @Param("category") String category);
    
    // Find products with low stock
    @Query("SELECT p FROM Product p WHERE p.stockQuantity < :threshold")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
    
    // Find products by category with stock greater than zero
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.stockQuantity > 0")
    List<Product> findAvailableProductsByCategory(@Param("category") String category);
}