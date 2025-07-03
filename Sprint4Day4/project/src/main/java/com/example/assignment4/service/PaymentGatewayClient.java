package com.example.assignment4.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PaymentGatewayClient {
    PaymentResponse processPayment(String customerName, BigDecimal amount);
    
    class PaymentResponse {
        private String paymentId;
        private boolean success;
        private String message;
        
        public PaymentResponse(String paymentId, boolean success, String message) {
            this.paymentId = paymentId;
            this.success = success;
            this.message = message;
        }
        
        // Getters
        public String getPaymentId() {
            return paymentId;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
    }
}