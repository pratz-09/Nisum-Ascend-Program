package com.nisum.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is required")
    private String name;
    
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @Column(name = "registered_date", nullable = false)
    @NotNull(message = "Registered date is required")
    private LocalDate registeredDate;
    
    // Default constructor
    public Customer() {}
    
    // Constructor with parameters
    public Customer(String name, String email, LocalDate registeredDate) {
        this.name = name;
        this.email = email;
        this.registeredDate = registeredDate;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getRegisteredDate() {
        return registeredDate;
    }
    
    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
    }
}