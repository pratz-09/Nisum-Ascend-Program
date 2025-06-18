
package com.example.project.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserFields() {
        User user = new User("John", "john@example.com", 25);
        assertAll("User Properties",
            () -> assertEquals("John", user.getName()),
            () -> assertEquals("john@example.com", user.getEmail()),
            () -> assertEquals(25, user.getAge())
        );
    }

    @Test
    void testUserServiceValidateAge() {
        UserService service = new UserService();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> service.validateAge(16));
        assertEquals("Underage", thrown.getMessage());
    }
}
