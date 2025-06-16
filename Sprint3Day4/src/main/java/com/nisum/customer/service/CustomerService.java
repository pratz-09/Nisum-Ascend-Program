package com.nisum.customer.service;

import com.nisum.customer.entity.Customer;
import com.nisum.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public Customer saveCustomer(Customer customer) {
        if (customer.getRegisteredDate() == null) {
            customer.setRegisteredDate(LocalDate.now());
        }
        return customerRepository.save(customer);
    }
    
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    public List<Customer> findByEmailContaining(String keyword) {
        return customerRepository.findByEmailContaining(keyword);
    }
    
    public List<Customer> findByRegisteredDateAfter(LocalDate date) {
        return customerRepository.findByRegisteredDateAfter(date);
    }
    
    public List<Customer> findByNameUsingJPQL(String name) {
        return customerRepository.findByNameUsingJPQL(name);
    }
}