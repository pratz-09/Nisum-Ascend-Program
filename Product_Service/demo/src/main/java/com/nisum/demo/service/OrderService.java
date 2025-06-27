package com.nisum.demo.service;



import com.nisum.demo.dto.User;
import com.nisum.demo.entity.Order;
import com.nisum.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public User getUserByOrderId(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            Long userId = order.get().getUserId();
            return restTemplate.getForObject("http://localhost:8080/users/" + userId, User.class);
        }
        return null;
    }
}
