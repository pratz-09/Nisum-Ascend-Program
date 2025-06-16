package com.nisum.product.repository;

import com.nisum.customer.entity.Customer;
import com.nisum.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    
    @BeforeEach
    void setUp() {
        customer1 = new Customer("John Doe", "john.doe@example.com", LocalDate.of(2023, 1, 15));
        customer2 = new Customer("Jane Smith", "jane.smith@gmail.com", LocalDate.of(2023, 6, 20));
        customer3 = new Customer("Bob Johnson", "bob@company.com", LocalDate.of(2022, 12, 10));
        
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }
    
    @Test
    void testFindByEmailContaining() {
        List<Customer> customers = customerRepository.findByEmailContaining("gmail");
        
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getEmail()).contains("gmail");
    }
    
    @Test
    void testFindByRegisteredDateAfter() {
        LocalDate cutoffDate = LocalDate.of(2023, 1, 1);
        List<Customer> customers = customerRepository.findByRegisteredDateAfter(cutoffDate);
        
        assertThat(customers).hasSize(2);
        assertThat(customers).extracting(Customer::getName)
                .containsExactlyInAnyOrder("John Doe", "Jane Smith");
    }
    
    @Test
    void testFindByNameUsingJPQL() {
        List<Customer> customers = customerRepository.findByNameUsingJPQL("John Doe");
        
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getName()).isEqualTo("John Doe");
    }
    
    @Test
    void testFindByNameUsingNativeQuery() {
        List<Customer> customers = customerRepository.findByNameUsingNativeQuery("Jane Smith");
        
        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getName()).isEqualTo("Jane Smith");
    }
    
    @Test
    void testFindByNameContainingIgnoreCase() {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase("john");
        
        assertThat(customers).hasSize(2);
        assertThat(customers).extracting(Customer::getName)
                .containsExactlyInAnyOrder("John Doe", "Bob Johnson");
    }
}