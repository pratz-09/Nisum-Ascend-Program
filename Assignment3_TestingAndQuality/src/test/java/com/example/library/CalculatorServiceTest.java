package com.example.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testAdd() {
        assertEquals(5, calculatorService.add(2, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calculatorService.subtract(3, 2));
    }
}
