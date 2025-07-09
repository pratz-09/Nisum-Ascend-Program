package com.example.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // This tells Spring to inject a mock version of CalculatorService
    @MockBean
    private CalculatorService calculatorService;

    @Test
    public void testAddEndpoint() throws Exception {
        when(calculatorService.add(2, 3)).thenReturn(5);

        mockMvc.perform(get("/calc/add?a=2&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }
}
