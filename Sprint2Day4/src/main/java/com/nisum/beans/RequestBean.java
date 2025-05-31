package com.nisum.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import java.util.UUID;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RequestBean {
    private final String id = UUID.randomUUID().toString();
    public String getId() { return id; }
}