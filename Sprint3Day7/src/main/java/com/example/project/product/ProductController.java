package com.example.project.product;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private Map<Long, Product> products = new HashMap<>();
    private long idCounter = 1;
    
    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.get(id);
    }
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId(idCounter++);
        products.put(product.getId(), product);
        return product;
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        updatedProduct.setId(id);
        products.put(id, updatedProduct);
        return updatedProduct;
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }
}