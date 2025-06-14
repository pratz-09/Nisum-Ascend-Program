package com.nisum.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = com.nisum.validation.YearValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYear {
    String message() default "Published year must be between 1000 and current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}