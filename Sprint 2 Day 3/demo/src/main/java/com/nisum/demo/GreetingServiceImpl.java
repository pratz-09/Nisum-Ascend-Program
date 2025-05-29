// File: demo/src/main/java/com/nisum/demo/GreetingServiceImpl.java
package com.nisum.demo;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    public String greet() {
        return "Hello from GreetingService!";
    }
}