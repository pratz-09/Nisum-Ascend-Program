package com.nisum.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/custom-login")
    public String login() {
        return "custom-login";
    }
}
