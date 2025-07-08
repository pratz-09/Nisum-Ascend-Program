// src/main/java/com/example/demo/controller/HelloController.java
package com.nisum.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello, admin!";
    }
}