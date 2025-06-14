package com.nisum.SpringDemo.validation;

import com.nisum.SpringDemo.entity.Product;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ValidPriceRangeValidator implements ConstraintValidator<com.nisum.SpringDemo.validation.ValidPriceRange, Product> {
    
    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {
        if (product == null || product.getPrice() == null) {
            return true; // Let @NotNull handle null validation
        }
        
        // Custom business logic: Price should be between 1 and 10000
        BigDecimal price = product.getPrice();
        return price.compareTo(BigDecimal.ONE) >= 0 && 
               price.compareTo(new BigDecimal("10000")) <= 0;
    }
}