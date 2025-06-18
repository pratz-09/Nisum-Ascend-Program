
package com.example.project.product;

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
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndGetProduct() throws Exception {
        Product product = new Product();
        product.setName("Phone");
        product.setDescription("Smartphone");
        product.setPrice(299.99);
        product.setStockQuantity(100);
        product.setCategory("Electronics");

        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/products")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }
}
