
package com.example.project.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndGetBook() throws Exception {
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPublishedYear(2008);

        String json = objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }
}
