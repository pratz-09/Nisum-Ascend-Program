package com.nisum.controller;

import com.nisum.entity.EcommerceProduct;
import com.nisum.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ecommerce/products")
public class EcommerceProductController {
    
    private final List<EcommerceProduct> products = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public EcommerceProductController() {
        // Initialize with sample data
        products.add(new EcommerceProduct(idGenerator.getAndIncrement(), "Gaming Laptop", "High-performance gaming laptop", new BigDecimal("1299.99"), 15, "Electronics"));
        products.add(new EcommerceProduct(idGenerator.getAndIncrement(), "Wireless Mouse", "Ergonomic wireless mouse", new BigDecimal("39.99"), 50, "Electronics"));
        products.add(new EcommerceProduct(idGenerator.getAndIncrement(), "Office Chair", "Comfortable office chair", new BigDecimal("199.99"), 25, "Furniture"));
        products.add(new EcommerceProduct(idGenerator.getAndIncrement(), "Coffee Mug", "Ceramic coffee mug", new BigDecimal("12.99"), 100, "Home"));
    }

    @GetMapping
    public ResponseEntity<List<EcommerceProduct>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort) {
        
        List<EcommerceProduct> filteredProducts = products.stream()
                .filter(product -> category == null || product.getCategory().toLowerCase().contains(category.toLowerCase()))
                .filter(product -> minPrice == null || product.getPrice().compareTo(minPrice) >= 0)
                .filter(product -> maxPrice == null || product.getPrice().compareTo(maxPrice) <= 0)
                .collect(Collectors.toList());
        
        // Apply sorting
        if (sort != null) {
            String[] sortParams = sort.split(",");
            String sortBy = sortParams[0];
            String sortDirection = sortParams.length > 1 ? sortParams[1] : "asc";
            
            Comparator<EcommerceProduct> comparator = null;
            switch (sortBy.toLowerCase()) {
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
            
            if ("desc".equalsIgnoreCase(sortDirection)) {
                comparator = comparator.reversed();
            }
            
            filteredProducts = filteredProducts.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }
        
        // Simple pagination
        int start = page * size;
        int end = Math.min(start + size, filteredProducts.size());
        
        if (start >= filteredProducts.size()) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        
        List<EcommerceProduct> paginatedProducts = filteredProducts.subList(start, end);
        return ResponseEntity.ok(paginatedProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcommerceProduct> getProductById(@PathVariable Long id) {
        EcommerceProduct product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<EcommerceProduct> createProduct(@Valid @RequestBody EcommerceProduct product) {
        product.setId(idGenerator.getAndIncrement());
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EcommerceProduct> updateProduct(@PathVariable Long id, @Valid @RequestBody EcommerceProduct productDetails) {
        EcommerceProduct product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCategory(productDetails.getCategory());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean removed = products.removeIf(p -> p.getId().equals(id));
        if (!removed) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}