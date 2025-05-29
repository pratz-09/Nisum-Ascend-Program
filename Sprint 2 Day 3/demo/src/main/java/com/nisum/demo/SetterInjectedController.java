// File: demo/src/main/java/com/nisum/demo/SetterInjectedController.java
package com.nisum.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetterInjectedController {
    private GreetingService greetingService;

    // Setter Injection
    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/setter")
    public String sayHello() {
        return greetingService.greet() + " (Setter Injection)";
    }
}