package com.example.assignment2;

import com.example.assignment2.service.PriceFacade;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceFacadeTest {

    @Spy
    private PriceFacade priceFacade;

    @Test
    public void shouldReturnDefaultValueWhenCalculationThrowsException() {
        // Given
        double basePrice = 100.0;
        String location = "INVALID";
        
        // Stub the calculateTax method to throw ArithmeticException
        doThrow(new ArithmeticException("Tax calculation failed"))
            .when(priceFacade).calculateTax(basePrice, location);

        // When
        double result = priceFacade.getPrice(basePrice, location);

        // Then
        assertEquals("Should return default value when calculation fails", 
                     100.0, result, 0.01);
        verify(priceFacade).calculateTax(basePrice, location);
    }

    @Test
    public void shouldReturnCalculatedValueWhenNoException() {
        // Given
        double basePrice = 100.0;
        String location = "NY";
        double expectedResult = 108.0; // 100 + 8% tax
        
        // Stub the calculateTax method to return a specific value
        doReturn(expectedResult).when(priceFacade).calculateTax(basePrice, location);

        // When
        double result = priceFacade.getPrice(basePrice, location);

        // Then
        assertEquals("Should return calculated value when no exception", 
                     expectedResult, result, 0.01);
        verify(priceFacade).calculateTax(basePrice, location);
    }

    @Test
    public void shouldHandleMultipleCallsWithDifferentBehaviors() {
        // Given
        double basePrice = 100.0;
        String location1 = "INVALID";
        String location2 = "CA";
        
        // First call throws exception, second call returns value
        doThrow(new ArithmeticException("Tax calculation failed"))
            .doReturn(107.5)
            .when(priceFacade).calculateTax(basePrice, anyString());

        // When
        double result1 = priceFacade.getPrice(basePrice, location1);
        double result2 = priceFacade.getPrice(basePrice, location2);

        // Then
        assertEquals("First call should return default value", 
                     100.0, result1, 0.01);
        assertEquals("Second call should return calculated value", 
                     107.5, result2, 0.01);
        
        verify(priceFacade, times(2)).calculateTax(eq(basePrice), anyString());
    }

    @After
    public void tearDown() {
        // Demonstrate reset of the spy
        reset(priceFacade);
        
        // After reset, verify that real method is called
        double basePrice = 100.0;
        String location = "TX";
        
        // This will call the real method since spy is reset
        double result = priceFacade.getPrice(basePrice, location);
        
        // Real calculation: 100 + (100 * 0.0625) = 106.25
        assertTrue("After reset, real method should be invoked", 
                   result > 100.0 && result < 110.0);
    }

    @Test
    public void shouldDemonstrateRealMethodInvocation() {
        // Given - using real spy without stubbing
        double basePrice = 200.0;
        String location = "CA";

        // When - this will call the real method
        double result = priceFacade.getPrice(basePrice, location);

        // Then - real calculation should happen
        // Real PriceCalculator: 200 + (200 * 0.075) = 215.0
        assertEquals("Real method should calculate correctly", 
                     215.0, result, 0.01);
    }
}