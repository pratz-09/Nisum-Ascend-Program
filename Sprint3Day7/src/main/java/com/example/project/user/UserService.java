package com.example.project.user;

public class UserService {
    
    public void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Underage");
        }
    }
}