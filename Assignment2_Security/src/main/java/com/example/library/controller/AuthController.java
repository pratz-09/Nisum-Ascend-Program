package com.example.library.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Placeholder - Normally you'd validate and return a JWT
        return "fake-jwt-token-for-" + username;
    }
}
