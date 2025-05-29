// File: demo/src/main/java/com/nisum/demo/ConstructorInjectedController.java
package com.nisum.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructorInjectedController {
    private final GreetingService greetingService;

    // Constructor Injection
    public ConstructorInjectedController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/constructor")
    public String sayHello() {
        return greetingService.greet() + " (Constructor Injection)";
    }
}