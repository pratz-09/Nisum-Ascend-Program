package com.example.assignment4.service;

import com.example.assignment4.model.Order;
import com.example.assignment4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentGatewayClient paymentGatewayClient;

    public Order createOrder(Order order) {
        // Process payment
        PaymentGatewayClient.PaymentResponse paymentResponse = 
            paymentGatewayClient.processPayment(order.getCustomerName(), order.getAmount());
        
        if (paymentResponse.isSuccess()) {
            order.setPaymentId(paymentResponse.getPaymentId());
            order.setStatus(Order.OrderStatus.COMPLETED);
        } else {
            order.setStatus(Order.OrderStatus.CANCELLED);
        }
        
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    public List<Order> findByCustomerName(String customerName) {
        return orderRepository.findByCustomerName(customerName);
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }
}