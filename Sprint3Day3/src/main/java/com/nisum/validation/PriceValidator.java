package com.nisum.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PriceValidator implements ConstraintValidator<com.nisum.validation.ValidPrice, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal price, ConstraintValidatorContext context) {
        if (price == null) {
            return false;
        }
        return price.compareTo(BigDecimal.ZERO) > 0 && 
               price.compareTo(new BigDecimal("1000000")) < 0;
    }
}