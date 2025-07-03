package com.example.assignment1.service;

import com.example.assignment1.model.User;
import com.example.assignment1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        
        if (user == null) {
            handleMissingUser(username);
            throw new UserNotFoundException("User not found with username: " + username);
        }
        
        // Send welcome email when user is found
        emailSender.send(user.getEmail(), "Welcome Back!", 
                        "Hello " + user.getFullName() + ", welcome back to our application!");
        
        return user;
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + user.getUsername());
        }
        
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
        
        User savedUser = userRepository.save(user);
        
        // Send welcome email to new user
        emailSender.send(savedUser.getEmail(), "Welcome to Our Application!", 
                        "Hello " + savedUser.getFullName() + ", welcome to our application!");
        
        return savedUser;
    }

    public void handleMissingUser(String username) {
        // Log the missing user attempt
        System.out.println("Handling missing user: " + username);
        // In a real application, this might log to a file or send an alert
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}