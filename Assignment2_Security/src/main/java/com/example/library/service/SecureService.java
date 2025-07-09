package com.example.library.service;

public class SecureService {
    public boolean isValidInput(String input) {
        return input != null && input.matches("[a-zA-Z0-9]+");
    }

    public String safeQuery(String userInput) {
        return "SELECT * FROM users WHERE username = ?"; // Use with PreparedStatement
    }
}
