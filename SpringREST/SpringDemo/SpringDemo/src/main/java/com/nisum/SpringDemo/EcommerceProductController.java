package com.nisum.SpringDemo.controller;

import com.nisum.SpringDemo.entity.EcommerceProduct;
import com.nisum.SpringDemo.exception.ProductNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ecommerce/products")
public class EcommerceProductController {
    
    private final ConcurrentHashMap<Long, EcommerceProduct> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    // Initialize with sample data
    public EcommerceProductController() {
        products.put(1L, new EcommerceProduct(1L, "Gaming Laptop", "High-performance gaming laptop", new BigDecimal("1299.99"), 15, "Electronics"));
        products.put(2L, new EcommerceProduct(2L, "Wireless Mouse", "Ergonomic wireless mouse", new BigDecimal("39.99"), 50, "Electronics"));
        products.put(3L, new EcommerceProduct(3L, "Office Chair", "Comfortable office chair", new BigDecimal("199.99"), 25, "Furniture"));
        idGenerator.set(4);
    }
    
    @GetMapping
    public ResponseEntity<List<EcommerceProduct>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "id,asc") String sort) {
        
        List<EcommerceProduct> filteredProducts = products.values().stream()
                .filter(product -> category == null || product.getCategory().toLowerCase().contains(category.toLowerCase()))
                .filter(product -> minPrice == null || product.getPrice().compareTo(minPrice) >= 0)
                .filter(product -> maxPrice == null || product.getPrice().compareTo(maxPrice) <= 0)
                .sorted(getSortComparator(sort))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(filteredProducts);
    }
    
    private Comparator<EcommerceProduct> getSortComparator(String sort) {
        String[] sortParams = sort.split(",");
        String field = sortParams[0];
        String direction = sortParams.length > 1 ? sortParams[1] : "asc";
        
        Comparator<EcommerceProduct> comparator;
        switch (field.toLowerCase()) {
            case "price":
                comparator = Comparator.comparing(EcommerceProduct::getPrice);
                break;
            case "name":
                comparator = Comparator.comparing(EcommerceProduct::getName);
                break;
            case "category":
                comparator = Comparator.comparing(EcommerceProduct::getCategory);
                break;
            case "stock":
                comparator = Comparator.comparing(EcommerceProduct::getStockQuantity);
                break;
            default:
                comparator = Comparator.comparing(EcommerceProduct::getId);
        }
        
        return "desc".equalsIgnoreCase(direction) ? comparator.reversed() : comparator;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EcommerceProduct> getProductById(@PathVariable Long id) {
        EcommerceProduct product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return ResponseEntity.ok(product);
    }
    
    @PostMapping
    public ResponseEntity<EcommerceProduct> createProduct(@Valid @RequestBody EcommerceProduct product) {
        Long id = idGenerator.getAndIncrement();
        product.setId(id);
        products.put(id, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EcommerceProduct> updateProduct(@PathVariable Long id, @Valid @RequestBody EcommerceProduct productDetails) {
        EcommerceProduct existingProduct = products.get(id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        
        productDetails.setId(id);
        products.put(id, productDetails);
        return ResponseEntity.ok(productDetails);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        EcommerceProduct product = products.remove(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}