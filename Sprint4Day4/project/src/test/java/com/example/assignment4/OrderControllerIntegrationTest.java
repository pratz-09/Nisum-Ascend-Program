package com.example.assignment4;

import com.example.assignment4.model.Order;
import com.example.assignment4.service.PaymentGatewayClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisplayName("Assignment 4: Context Isolation Integration Test")
class OrderControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PaymentGatewayClient paymentGatewayClient;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    @DisplayName("Should create order with mocked payment gateway and real beans")
    void shouldCreateOrderWithMockedPaymentGateway() {
        // Given
        Order order = new Order("John Doe", "MacBook Pro", new BigDecimal("1999.99"));
        
        // Mock the payment gateway response
        PaymentGatewayClient.PaymentResponse mockResponse = 
            new PaymentGatewayClient.PaymentResponse("PAY-123456", true, "Payment successful");
        
        when(paymentGatewayClient.processPayment(eq("John Doe"), eq(new BigDecimal("1999.99"))))
            .thenReturn(mockResponse);

        // When
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Order> request = new HttpEntity<>(order, headers);
        
        ResponseEntity<Order> response = restTemplate.postForEntity(
            baseUrl + "/orders", request, Order.class);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getCustomerName());
        assertEquals("MacBook Pro", response.getBody().getProductName());
        assertEquals(new BigDecimal("1999.99"), response.getBody().getAmount());
        assertEquals(Order.OrderStatus.COMPLETED, response.getBody().getStatus());
        assertEquals("PAY-123456", response.getBody().getPaymentId());
        
        // Verify that the payment gateway mock was called exactly once
        verify(paymentGatewayClient, times(1))
            .processPayment("John Doe", new BigDecimal("1999.99"));
    }

    @Test
    @DisplayName("Should handle payment failure scenario")
    void shouldHandlePaymentFailureScenario() {
        // Given
        Order order = new Order("Jane Smith", "iPhone 15", new BigDecimal("899.99"));
        
        // Mock payment failure
        PaymentGatewayClient.PaymentResponse mockResponse = 
            new PaymentGatewayClient.PaymentResponse(null, false, "Payment failed");
        
        when(paymentGatewayClient.processPayment(eq("Jane Smith"), eq(new BigDecimal("899.99"))))
            .thenReturn(mockResponse);

        // When
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Order> request = new HttpEntity<>(order, headers);
        
        ResponseEntity<Order> response = restTemplate.postForEntity(
            baseUrl + "/orders", request, Order.class);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Smith", response.getBody().getCustomerName());
        assertEquals(Order.OrderStatus.CANCELLED, response.getBody().getStatus());
        assertNull(response.getBody().getPaymentId());
        
        // Verify payment gateway was called
        verify(paymentGatewayClient, times(1))
            .processPayment("Jane Smith", new BigDecimal("899.99"));
    }

    @Test
    @DisplayName("Should retrieve all orders")
    void shouldRetrieveAllOrders() {
        // Given - Create an order first
        Order order = new Order("Alice Johnson", "iPad", new BigDecimal("599.99"));
        
        PaymentGatewayClient.PaymentResponse mockResponse = 
            new PaymentGatewayClient.PaymentResponse("PAY-789012", true, "Payment successful");
        
        when(paymentGatewayClient.processPayment(any(), any())).thenReturn(mockResponse);

        // Create the order
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Order> createRequest = new HttpEntity<>(order, headers);
        
        restTemplate.postForEntity(baseUrl + "/orders", createRequest, Order.class);

        // When - Retrieve all orders
        ResponseEntity<Order[]> response = restTemplate.getForEntity(
            baseUrl + "/orders", Order[].class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        
        // Verify at least one order exists
        Order retrievedOrder = response.getBody()[0];
        assertEquals("Alice Johnson", retrievedOrder.getCustomerName());
        assertEquals("iPad", retrievedOrder.getProductName());
        assertEquals(Order.OrderStatus.COMPLETED, retrievedOrder.getStatus());
    }

    @Test
    @DisplayName("Should verify real beans are wired correctly")
    void shouldVerifyRealBeansAreWired() {
        // Given
        Order order = new Order("Bob Wilson", "Apple Watch", new BigDecimal("399.99"));
        
        // Mock payment gateway
        PaymentGatewayClient.PaymentResponse mockResponse = 
            new PaymentGatewayClient.PaymentResponse("PAY-345678", true, "Payment successful");
        
        when(paymentGatewayClient.processPayment(any(), any())).thenReturn(mockResponse);

        // When
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Order> request = new HttpEntity<>(order, headers);
        
        ResponseEntity<Order> response = restTemplate.postForEntity(
            baseUrl + "/orders", request, Order.class);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        
        // Verify the order was persisted (indicating real OrderRepository is working)
        Long orderId = response.getBody().getId();
        assertNotNull(orderId);
        
        // Get the order by ID to verify it was actually saved
        ResponseEntity<Order> getResponse = restTemplate.getForEntity(
            baseUrl + "/orders/" + orderId, Order.class);
        
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Bob Wilson", getResponse.getBody().getCustomerName());
        
        // Verify payment gateway was called exactly once
        verify(paymentGatewayClient, times(1))
            .processPayment("Bob Wilson", new BigDecimal("399.99"));
    }

    @Test
    @DisplayName("Should demonstrate context isolation - only payment gateway is mocked")
    void shouldDemonstrateContextIsolation() {
        // Given
        Order order = new Order("Charlie Brown", "AirPods", new BigDecimal("179.99"));
        
        // Mock only the payment gateway
        PaymentGatewayClient.PaymentResponse mockResponse = 
            new PaymentGatewayClient.PaymentResponse("PAY-999888", true, "Payment successful");
        
        when(paymentGatewayClient.processPayment(any(), any())).thenReturn(mockResponse);

        // When
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Order> request = new HttpEntity<>(order, headers);
        
        ResponseEntity<Order> response = restTemplate.postForEntity(
            baseUrl + "/orders", request, Order.class);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        
        // Verify only the payment gateway mock was used
        verify(paymentGatewayClient, times(1)).processPayment(any(), any());
        
        // Verify real beans are working by checking that:
        // 1. Order has an ID (database save worked)
        // 2. Order has created timestamp (entity logic worked)
        // 3. Order status is set correctly (service logic worked)
        Order createdOrder = response.getBody();
        assertNotNull(createdOrder.getId(), "Order ID should be generated by real database");
        assertNotNull(createdOrder.getCreatedAt(), "Created timestamp should be set by real entity");
        assertEquals(Order.OrderStatus.COMPLETED, createdOrder.getStatus(), 
                    "Status should be set by real service logic");
    }
}