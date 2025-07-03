package com.example.assignment2.service;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculator {
    
    private static final double DEFAULT_TAX_RATE = 0.10;
    
    public double calculatePrice(double basePrice, String location) {
        try {
            double tax = calculateTax(basePrice, location);
            return basePrice + tax;
        } catch (ArithmeticException e) {
            System.out.println("Error calculating tax, using default: " + e.getMessage());
            return basePrice * (1 + DEFAULT_TAX_RATE);
        }
    }
    
    private double calculateTax(double basePrice, String location) {
        // Heavy computation that might fail
        if (location == null || location.trim().isEmpty()) {
            throw new ArithmeticException("Location cannot be null or empty");
        }
        
        // Simulate different tax rates based on location
        switch (location.toLowerCase()) {
            case "ny":
                return basePrice * 0.08;
            case "ca":
                return basePrice * 0.075;
            case "tx":
                return basePrice * 0.0625;
            default:
                return basePrice * 0.05;
        }
    }
}