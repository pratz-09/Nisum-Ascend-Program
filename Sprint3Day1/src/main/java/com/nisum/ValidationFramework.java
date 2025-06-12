package com.nisum;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

// Custom validation exception
class ValidationException extends RuntimeException {
    private final List<String> errors;
    
    public ValidationException(List<String> errors) {
        super("Validation failed with " + errors.size() + " error(s): " + String.join(", ", errors));
        this.errors = new ArrayList<>(errors);
    }
    
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
}

// Validation rule interface
@FunctionalInterface
interface ValidationRule<T> {
    Optional<String> validate(T value);
    
    static <T> ValidationRule<T> of(Predicate<T> condition, String errorMessage) {
        return value -> condition.test(value) ? Optional.empty() : Optional.of(errorMessage);
    }
    
    static <T> ValidationRule<T> of(Predicate<T> condition, Function<T, String> errorMessageProvider) {
        return value -> condition.test(value) ? Optional.empty() : Optional.of(errorMessageProvider.apply(value));
    }
}

// Validator class
class Validator<T> {
    private final List<ValidationRule<T>> rules;
    
    public Validator() {
        this.rules = new ArrayList<>();
    }
    
    public Validator<T> addRule(ValidationRule<T> rule) {
        rules.add(rule);
        return this;
    }
    
    public void validate(T value) {
        List<String> errors = rules.stream()
            .map(rule -> rule.validate(value))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    
    public boolean isValid(T value) {
        try {
            validate(value);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }
}

// Product record
record Product(String name, double price, String category) {}

public class ValidationFramework {
    
    // User validation rules
    private static final Validator<User> USER_VALIDATOR = new Validator<User>()
        .addRule(ValidationRule.of(
            user -> user.name() != null && !user.name().trim().isEmpty(),
            "Name cannot be null or empty"
        ))
        .addRule(ValidationRule.of(
            user -> user.name() != null && user.name().length() >= 2,
            user -> "Name must be at least 2 characters long, got: " + 
                (user.name() != null ? user.name().length() : 0)
        ))
        .addRule(ValidationRule.of(
            user -> user.email() != null && user.email().contains("@"),
            "Email must contain @ symbol"
        ))
        .addRule(ValidationRule.of(
            user -> user.age() >= 0 && user.age() <= 150,
            user -> "Age must be between 0 and 150, got: " + user.age()
        ));
    
    // Product validation rules
    private static final Validator<Product> PRODUCT_VALIDATOR = new Validator<Product>()
        .addRule(ValidationRule.of(
            product -> product.name() != null && !product.name().trim().isEmpty(),
            "Product name cannot be null or empty"
        ))
        .addRule(ValidationRule.of(
            product -> product.price() > 0,
            product -> "Price must be positive, got: " + product.price()
        ))
        .addRule(ValidationRule.of(
            product -> product.category() != null && !product.category().trim().isEmpty(),
            "Category cannot be null or empty"
        ));
    
    public static void validateUser(User user) {
        USER_VALIDATOR.validate(user);
    }
    
    public static void validateProduct(Product product) {
        PRODUCT_VALIDATOR.validate(product);
    }
    
    public static void main(String[] args) {
        // Test users
        List<User> users = List.of(
            new User("John Doe", "john@example.com", 25),
            new User("", "invalid-email", -5),
            new User("A", "test@test.com", 200),
            new User(null, null, 30)
        );
        
        for (User user : users) {
            try {
                validateUser(user);
                System.out.println("✓ Valid user: " + user);
            } catch (ValidationException e) {
                System.err.println("✗ Invalid user " + user + ":");
                e.getErrors().forEach(error -> System.err.println("  - " + error));
            }
        }
        
        System.out.println();
        
        // Test products
        List<Product> products = List.of(
            new Product("Laptop", 999.99, "Electronics"),
            new Product("", -100, ""),
            new Product("Phone", 0, "Electronics")
        );
        
        for (Product product : products) {
            try {
                validateProduct(product);
                System.out.println("✓ Valid product: " + product);
            } catch (ValidationException e) {
                System.err.println("✗ Invalid product " + product + ":");
                e.getErrors().forEach(error -> System.err.println("  - " + error));
            }
        }
    }
}