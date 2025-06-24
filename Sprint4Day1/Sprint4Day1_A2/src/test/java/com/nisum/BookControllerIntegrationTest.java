package com.nisum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.security.Key;

private String generateJwt() {
    String secret = "demo-demo-demo-demo-demo-demo-demo-demo"; // same as in JwtFilter
    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder()
            .setSubject("user")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
}

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateBook() throws Exception {
        BookDto dto = new BookDto();
        dto.setTitle("Test");
        dto.setAuthor("Author");
        dto.setPages(100);

        mockMvc.perform(post("/api/books")
                .header("Authorization", "Bearer <valid-jwt>")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}