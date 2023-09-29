package com.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.register.controller.RegistrationController;
import com.register.model.User;
import com.register.service.RegistrationService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private RegistrationService RegistrationService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser_Success() throws Exception {
        // Create a sample user
        User user = new User();
        user.setEmail("test@example.com");

        // Mock the service method to return null (user does not exist)
        when(RegistrationService.fetchUserByEmail("test@example.com")).thenReturn(null);

        // Mock the service method to save the user
        when(RegistrationService.saveUser(user)).thenReturn(user);

        // Call the controller method
        User registeredUser = registrationController.registerUser(user);

        // Verify that the service methods were called
        verify(RegistrationService, times(1)).fetchUserByEmail("test@example.com");
        verify(RegistrationService, times(1)).saveUser(user);

        // Assert the result
        assertNotNull(registeredUser);
        assertEquals("test@example.com", registeredUser.getEmail());
    }

    @Test
    public void testRegisterUser_UserExists() {
        // Create a sample user
        User user = new User();
        user.setEmail("test@example.com");

        // Mock the service method to return an existing user
        when(RegistrationService.fetchUserByEmail("test@example.com")).thenReturn(user);

        // Call the controller method and expect an exception
        Exception exception = assertThrows(Exception.class, () -> {
        	registrationController.registerUser(user);
        });

        // Verify that the service method was called
        verify(RegistrationService, times(1)).fetchUserByEmail("test@example.com");

        // Assert the exception message
        assertEquals("User with test@example.com is already Exists", exception.getMessage());
    }
    
    @Test
    public void testLoginUser_Success() throws Exception {
        // Create a sample user
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");

        // Mock the service method to return a valid user
        when(RegistrationService.fetchUserByEmailAndPassword("test@example.com", "password123")).thenReturn(user);

        // Call the controller method
        User loggedInUser = registrationController.loginUser(user);

        // Verify that the service method was called
        verify(RegistrationService, times(1)).fetchUserByEmailAndPassword("test@example.com", "password123");

        // Assert the result
        assertNotNull(loggedInUser);
        assertEquals("test@example.com", loggedInUser.getEmail());
        assertEquals("password123", loggedInUser.getPassword());
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        // Create a sample user
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");

        // Mock the service method to return null (invalid credentials)
        when(RegistrationService.fetchUserByEmailAndPassword("test@example.com", "password123")).thenReturn(null);

        // Call the controller method and expect an exception
        Exception exception = assertThrows(Exception.class, () -> {
            registrationController.loginUser(user);
        });

        // Verify that the service method was called
        verify(RegistrationService, times(1)).fetchUserByEmailAndPassword("test@example.com", "password123");

        // Assert the exception message
        assertEquals("Invalid Credentials", exception.getMessage());
    }
}

