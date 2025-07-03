package com.example.assignment4;

import com.example.assignment4.model.Order;
import com.example.assignment4.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Assignment 4: Sliced Test Comparison - @DataJpaTest")
class OrderControllerSlicedTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Should persist and retrieve order using sliced test")
    void shouldPersistAndRetrieveOrderUsingSlicedTest() {
        // Given
        Order order = new Order("Test Customer", "Test Product", new BigDecimal("99.99"));
        order.setStatus(Order.OrderStatus.PENDING);

        // When
        Order savedOrder = entityManager.persistAndFlush(order);

        // Then
        assertNotNull(savedOrder.getId());
        assertEquals("Test Customer", savedOrder.getCustomerName());
        assertEquals("Test Product", savedOrder.getProductName());
        assertEquals(new BigDecimal("99.99"), savedOrder.getAmount());
        assertEquals(Order.OrderStatus.PENDING, savedOrder.getStatus());
        assertNotNull(savedOrder.getCreatedAt());
    }

    @Test
    @DisplayName("Should find orders by customer name")
    void shouldFindOrdersByCustomerName() {
        // Given
        Order order1 = new Order("John Doe", "Product A", new BigDecimal("50.00"));
        Order order2 = new Order("John Doe", "Product B", new BigDecimal("75.00"));
        Order order3 = new Order("Jane Smith", "Product C", new BigDecimal("100.00"));

        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.persist(order3);
        entityManager.flush();

        // When
        List<Order> johnDoeOrders = orderRepository.findByCustomerName("John Doe");
        List<Order> janeSmithOrders = orderRepository.findByCustomerName("Jane Smith");

        // Then
        assertEquals(2, johnDoeOrders.size());
        assertEquals(1, janeSmithOrders.size());
        
        assertTrue(johnDoeOrders.stream().allMatch(o -> "John Doe".equals(o.getCustomerName())));
        assertTrue(janeSmithOrders.stream().allMatch(o -> "Jane Smith".equals(o.getCustomerName())));
    }

    @Test
    @DisplayName("Should find orders by status")
    void shouldFindOrdersByStatus() {
        // Given
        Order pendingOrder = new Order("Customer 1", "Product 1", new BigDecimal("100.00"));
        pendingOrder.setStatus(Order.OrderStatus.PENDING);
        
        Order completedOrder = new Order("Customer 2", "Product 2", new BigDecimal("200.00"));
        completedOrder.setStatus(Order.OrderStatus.COMPLETED);

        entityManager.persist(pendingOrder);
        entityManager.persist(completedOrder);
        entityManager.flush();

        // When
        List<Order> pendingOrders = orderRepository.findByStatus(Order.OrderStatus.PENDING);
        List<Order> completedOrders = orderRepository.findByStatus(Order.OrderStatus.COMPLETED);

        // Then
        assertEquals(1, pendingOrders.size());
        assertEquals(1, completedOrders.size());
        
        assertEquals(Order.OrderStatus.PENDING, pendingOrders.get(0).getStatus());
        assertEquals(Order.OrderStatus.COMPLETED, completedOrders.get(0).getStatus());
    }
}