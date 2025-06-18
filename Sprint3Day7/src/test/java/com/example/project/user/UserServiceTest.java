package com.example.project.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        userService = new UserService();
    }
    
    @Test
    void testValidateAgeValid() {
        assertDoesNotThrow(() -> userService.validateAge(18));
        assertDoesNotThrow(() -> userService.validateAge(25));
    }
    
    @Test
    void testValidateAgeUnderage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> userService.validateAge(17));
        assertEquals("Underage", exception.getMessage());
        
        exception = assertThrows(IllegalArgumentException.class,
            () -> userService.validateAge(10));
        assertEquals("Underage", exception.getMessage());
    }
}