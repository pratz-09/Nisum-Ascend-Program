package com.example.assignment1;

import com.example.assignment1.model.User;
import com.example.assignment1.repository.UserRepository;
import com.example.assignment1.service.EmailSender;
import com.example.assignment1.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Assignment 1: UserService JUnit 5 Tests")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Should throw exception on first call when user not found")
    void shouldThrowExceptionOnFirstCall() {
        // Given
        String username = "nonexistent";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When & Then
        UserService.UserNotFoundException exception = assertThrows(
            UserService.UserNotFoundException.class,
            () -> userService.findUserByUsername(username)
        );
        
        assertEquals("User not found with username: " + username, exception.getMessage());
        verify(userRepository).findByUsername(username);
        verifyNoInteractions(emailSender);
    }

    @Test
    @DisplayName("Should invoke fallback method when user not found")
    void shouldInvokeFallbackMethodWhenUserNotFound() {
        // Given
        String username = "nonexistent";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Create a spy to verify the fallback method is called
        UserService spyUserService = spy(userService);
        doNothing().when(spyUserService).handleMissingUser(any());

        // When
        assertThrows(UserService.UserNotFoundException.class, 
                    () -> spyUserService.findUserByUsername(username));

        // Then
        verify(spyUserService).handleMissingUser(username);
    }

    @Test
    @DisplayName("Should return user and send email on second call")
    void shouldReturnUserAndSendEmailOnSecondCall() {
        // Given
        String username = "testuser";
        User user = new User(username, "test@example.com", "Test User");
        user.setId(1L);

        // First call returns empty, second call returns user
        when(userRepository.findByUsername(username))
            .thenReturn(Optional.empty())
            .thenReturn(Optional.of(user));

        // When - First call should throw exception
        assertThrows(UserService.UserNotFoundException.class, 
                    () -> userService.findUserByUsername(username));

        // When - Second call should return user
        User result = userService.findUserByUsername(username);

        // Then
        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        
        // Verify email was sent
        verify(emailSender).send(eq(user.getEmail()), any(), any());
    }

    @Test
    @DisplayName("Should capture and assert email subject line")
    void shouldCaptureAndAssertEmailSubject() {
        // Given
        String username = "testuser";
        User user = new User(username, "test@example.com", "Test User");
        user.setId(1L);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // When
        userService.findUserByUsername(username);

        // Then - Capture the arguments passed to emailSender.send()
        ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailSender).send(toCaptor.capture(), subjectCaptor.capture(), bodyCaptor.capture());

        // Assert the captured values
        assertEquals(user.getEmail(), toCaptor.getValue());
        assertEquals("Welcome Back!", subjectCaptor.getValue());
        assertTrue(bodyCaptor.getValue().contains(user.getFullName()));
    }

    @Test
    @DisplayName("Should handle user creation with email sending")
    void shouldHandleUserCreationWithEmailSending() {
        // Given
        User newUser = new User("newuser", "new@example.com", "New User");
        User savedUser = new User("newuser", "new@example.com", "New User");
        savedUser.setId(1L);

        when(userRepository.existsByUsername(newUser.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(newUser.getEmail())).thenReturn(false);
        when(userRepository.save(newUser)).thenReturn(savedUser);

        // When
        User result = userService.createUser(newUser);

        // Then
        assertNotNull(result);
        assertEquals(savedUser.getId(), result.getId());
        
        // Verify email was sent with correct subject
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailSender).send(eq(savedUser.getEmail()), subjectCaptor.capture(), any());
        assertEquals("Welcome to Our Application!", subjectCaptor.getValue());
    }
}