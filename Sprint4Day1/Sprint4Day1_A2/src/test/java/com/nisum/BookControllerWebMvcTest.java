package com.nisum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.controller.BookController;
import com.nisum.dto.BookDto;
import com.nisum.service.BookService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Key;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Autowired
    private ObjectMapper objectMapper;

    private String generateJwt() {
        String secret = "demo-demo-demo-demo-demo-demo-demo-demo";
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void testCreateBook() throws Exception {
        BookDto dto = new BookDto();
        dto.setTitle("Test");
        dto.setAuthor("Author");
        dto.setPages(100);

        String jwt = generateJwt();

        mockMvc.perform(post("/api/books")
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testListBooks() throws Exception {
        String jwt = generateJwt();
        mockMvc.perform(get("/api/books")
                .header("Authorization", "Bearer " + jwt))
                .andExpect(status().isOk());
    }
}