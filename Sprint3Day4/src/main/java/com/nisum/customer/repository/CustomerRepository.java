package com.nisum.customer.repository;

import com.nisum.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Query method to find customers by email containing keyword
    List<Customer> findByEmailContaining(String keyword);
    
    // Query method to find customers registered after a specific date
    List<Customer> findByRegisteredDateAfter(LocalDate date);
    
    // Custom JPQL query to find customers by name
    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findByNameUsingJPQL(@Param("name") String name);
    
    // Native SQL query to find customers by name
    @Query(value = "SELECT * FROM customers WHERE name = :name", nativeQuery = true)
    List<Customer> findByNameUsingNativeQuery(@Param("name") String name);
    
    // Additional query method for name containing (case insensitive)
    List<Customer> findByNameContainingIgnoreCase(String name);
}