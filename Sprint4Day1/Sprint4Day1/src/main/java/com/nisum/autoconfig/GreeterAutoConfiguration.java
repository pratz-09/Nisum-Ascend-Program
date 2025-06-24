
package com.nisum.autoconfig;

import com.nisum.Greeter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreeterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public Greeter greeter() {
        return new Greeter("Hello from Auto-Config!");
    }
}