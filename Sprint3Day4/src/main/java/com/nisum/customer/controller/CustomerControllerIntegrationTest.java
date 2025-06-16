package com.nisum.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.customer.entity.Customer;
import com.nisum.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureTestMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestMvc
public class CustomerControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }
    
    @Test
    void testAddCustomer() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", LocalDate.now());
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"));
    }
    
    @Test
    void testGetAllCustomers() throws Exception {
        Customer customer1 = new Customer("John Doe", "john@example.com", LocalDate.now());
        Customer customer2 = new Customer("Jane Smith", "jane@example.com", LocalDate.now());
        
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpected(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name", Matchers.containsInAnyOrder("John Doe", "Jane Smith")));
    }
    
    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", LocalDate.now());
        Customer savedCustomer = customerRepository.save(customer);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", savedCustomer.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"));
    }
    
    @Test
    void testDeleteCustomer() throws Exception {
        Customer customer = new Customer("John Doe", "john@example.com", LocalDate.now());
        Customer savedCustomer = customerRepository.save(customer);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}", savedCustomer.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}