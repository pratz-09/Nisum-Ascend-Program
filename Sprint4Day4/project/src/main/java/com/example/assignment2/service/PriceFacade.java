package com.example.assignment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceFacade {
    
    @Autowired
    private PriceCalculator priceCalculator;
    
    private static final double DEFAULT_PRICE = 100.0;
    
    public double getPrice(double basePrice, String location) {
        try {
            return calculateTax(basePrice, location);
        } catch (ArithmeticException e) {
            System.out.println("Calculation failed, returning default price: " + e.getMessage());
            return DEFAULT_PRICE;
        }
    }
    
    // This method wraps the heavy calculation
    public double calculateTax(double basePrice, String location) {
        return priceCalculator.calculatePrice(basePrice, location);
    }
}