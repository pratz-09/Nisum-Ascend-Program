
package com.nisum;

import org.springframework.stereotype.Component;

@Component
public class GreeterComponent extends Greeter {
    public GreeterComponent() {
        super("Hello from @Component!");
    }
}