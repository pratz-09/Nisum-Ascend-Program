package com.nisum.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

public class YearValidator implements ConstraintValidator<ValidYear, Integer> {
    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if (year == null) {
            return false;
        }
        return year >= 1000 && year <= Year.now().getValue();
    }
}