package com.nisum.customer.controller;

import com.nisum.customer.entity.Customer;
import com.nisum.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    // Add a new customer
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
    
    // Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    // Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerService.getCustomerById(id).isPresent()) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Search customers by email containing keyword
    @GetMapping("/search/email")
    public ResponseEntity<List<Customer>> searchByEmail(@RequestParam String keyword) {
        List<Customer> customers = customerService.findByEmailContaining(keyword);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    // Find customers registered after a specific date
    @GetMapping("/search/date")
    public ResponseEntity<List<Customer>> findByRegisteredDateAfter(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Customer> customers = customerService.findByRegisteredDateAfter(localDate);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    
    // Find customers by name using JPQL
    @GetMapping("/search/name")
    public ResponseEntity<List<Customer>> findByName(@RequestParam String name) {
        List<Customer> customers = customerService.findByNameUsingJPQL(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}