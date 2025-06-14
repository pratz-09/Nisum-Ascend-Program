package com.nisum.SpringDemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = com.nisum.SpringDemo.validation.ValidPriceRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPriceRange {
    String message() default "Price must be within valid range for the category";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}