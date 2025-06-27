package com.nisum.demo.controller;



import com.nisum.demo.dto.User;
import com.nisum.demo.entity.Order;
import com.nisum.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/{orderId}/user")
    public User getUserByOrder(@PathVariable Long orderId) {
        return orderService.getUserByOrderId(orderId);
    }
}

