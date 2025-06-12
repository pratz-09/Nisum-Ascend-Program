package com.nisum;

public class PaymentService {
    
    public void paymentMethod(PaymentType paymentType, OfferType offerType, double amount) {
        // Validate payment method
        switch (paymentType) {
            case INVALID -> throw new InvalidPaymentMethodException("Invalid payment method: " + paymentType);
            case CRYPTO -> {
                if (amount < 10) {
                    throw new InvalidPaymentMethodException("Crypto payments require minimum $10");
                }
            }
        }
        
        // Validate offer
        switch (offerType) {
            case INVALID_OFFER -> throw new InvalidOfferAppliedException("Invalid offer type: " + offerType);
            case DISCOUNT_20 -> {
                if (amount < 100) {
                    throw new InvalidOfferAppliedException("20% discount requires minimum $100 purchase");
                }
            }
        }
        
        System.out.println("Payment processed: " + paymentType + " with offer " + offerType + " for $" + amount);
    }
    
    public void processPaymentMethod(PaymentType paymentType, OfferType offerType, double amount) {
        try {
            paymentMethod(paymentType, offerType, amount);
        } catch (PaymentException e) {
            // Pattern matching with sealed classes
            String errorType = switch (e) {
                case InvalidPaymentMethodException ipme -> "Payment Method Error";
                case InvalidOfferAppliedException ioae -> "Offer Application Error";
                default -> throw new IllegalStateException("Unexpected value: " + e);
            };
            
            System.err.println(errorType + ": " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        
        // Test cases
        service.processPaymentMethod(PaymentType.CREDIT_CARD, OfferType.DISCOUNT_10, 50);
        service.processPaymentMethod(PaymentType.INVALID, OfferType.NONE, 30);
        service.processPaymentMethod(PaymentType.CRYPTO, OfferType.NONE, 5);
        service.processPaymentMethod(PaymentType.PAYPAL, OfferType.DISCOUNT_20, 50);
    }
}
