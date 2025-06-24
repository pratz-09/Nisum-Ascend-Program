package com.nisum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreeterConfig {
    @Bean
    public Greeter greeter() {
        return new Greeter("Hello from Java Config!");
    }
}
