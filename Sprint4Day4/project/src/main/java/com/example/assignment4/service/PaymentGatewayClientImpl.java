package com.example.assignment4.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentGatewayClientImpl implements PaymentGatewayClient {
    
    @Override
    public PaymentResponse processPayment(String customerName, BigDecimal amount) {
        // Simulate payment processing
        String paymentId = UUID.randomUUID().toString();
        
        // Simulate success/failure based on amount
        boolean success = amount.compareTo(BigDecimal.valueOf(1000)) <= 0;
        String message = success ? "Payment processed successfully" : "Payment failed - amount too high";
        
        return new PaymentResponse(paymentId, success, message);
    }
}