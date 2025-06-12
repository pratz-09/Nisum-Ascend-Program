package com.nisum;

// Sealed class for payment exceptions (Java 17+)
public sealed class PaymentException extends RuntimeException
    permits InvalidPaymentMethodException, InvalidOfferAppliedException {
    
    public PaymentException(String message) {
        super(message);
    }
}

final class InvalidPaymentMethodException extends PaymentException {
    public InvalidPaymentMethodException(String message) {
        super(message);
    }
}

final class InvalidOfferAppliedException extends PaymentException {
    public InvalidOfferAppliedException(String message) {
        super(message);
    }
}

enum PaymentType {
    CREDIT_CARD, DEBIT_CARD, PAYPAL, CRYPTO, INVALID
}

enum OfferType {
    DISCOUNT_10, DISCOUNT_20, CASHBACK, INVALID_OFFER, NONE
}

