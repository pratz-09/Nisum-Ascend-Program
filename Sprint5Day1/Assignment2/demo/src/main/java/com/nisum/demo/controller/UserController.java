package com.nisum.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/me")
    public UserDetails me(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
}