package com.nisum.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Scope("singleton")
public class SingletonBean {
    private final String id = UUID.randomUUID().toString();
    public String getId() { return id; }
}