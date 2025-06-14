package com.nisum.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriceValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPrice {
    String message() default "Price must be greater than 0 and less than 1000000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}