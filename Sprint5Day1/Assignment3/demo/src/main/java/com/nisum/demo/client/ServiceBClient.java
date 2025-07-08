package com.nisum.demo.client;

import com.nisum.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceBClient {

    @Autowired
    private JwtUtil jwtUtil;

    private final RestTemplate restTemplate = new RestTemplate();

    public String callProtectedEndpoint() {
        String jwt = jwtUtil.generateToken("serviceA", "MICROSERVICE");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://service-b-host:port/api/protected/data",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
}