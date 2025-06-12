package com.nisum;

// Custom exception class
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
    
    public InvalidAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class CustomExceptionHandling {
    
    public static void validateAge(int age) {
        try {
            if (age <= 0) {
                throw new InvalidAgeException("Age must be positive. Provided age: " + age);
            }
            System.out.println("Valid age: " + age);
        } catch (InvalidAgeException e) {
            System.err.println("Age validation failed: " + e.getMessage());
            throw e; // Re-throw to be handled by caller
        }
    }
    
    public static void main(String[] args) {
        int[] ages = {25, -5, 0, 30, 150};
        
        for (int age : ages) {
            try {
                validateAge(age);
            } catch (InvalidAgeException e) {
                System.err.println("Caught InvalidAgeException: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Caught unexpected exception: " + e.getMessage());
            }
        }
    }
}