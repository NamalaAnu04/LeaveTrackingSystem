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
        User user = new User();
        user.setEmail("anunamala04@gmail.com");
        when(RegistrationService.fetchUserByEmail("anunamala04@gmail.com")).thenReturn(null);
        when(RegistrationService.saveUser(user)).thenReturn(user);
        User registeredUser = registrationController.registerUser(user);
        verify(RegistrationService, times(1)).fetchUserByEmail("anunamala04@gmail.com");
        verify(RegistrationService, times(1)).saveUser(user);
        assertNotNull(registeredUser);
        assertEquals("anunamala04@gmail.com"), registeredUser.getEmail());
    }

    @Test
    public void testRegisterUser_UserExists() {
        User user = new User();
        user.setEmail("anunamala04@gmail.com");
        when(RegistrationService.fetchUserByEmail("anunamala04@gmail.com")).thenReturn(user);
        Exception exception = assertThrows(Exception.class, () -> {
        	registrationController.registerUser(user);
        });
        verify(RegistrationService, times(1)).fetchUserByEmail("anunamala04@gmail.com");
        assertEquals("User with test@example.com is already Exists", exception.getMessage());
    }
    
    @Test
    public void testLoginUser_Success() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        when(RegistrationService.fetchUserByEmailAndPassword("test@example.com", "password123")).thenReturn(user);
        User loggedInUser = registrationController.loginUser(user);
        verify(RegistrationService, times(1)).fetchUserByEmailAndPassword("test@example.com", "password123");
        assertNotNull(loggedInUser);
        assertEquals("test@example.com", loggedInUser.getEmail());
        assertEquals("password123", loggedInUser.getPassword());
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        when(RegistrationService.fetchUserByEmailAndPassword("test@example.com", "password123")).thenReturn(null);
        Exception exception = assertThrows(Exception.class, () -> {
            registrationController.loginUser(user);
        });
        verify(RegistrationService, times(1)).fetchUserByEmailAndPassword("test@example.com", "password123");
        assertEquals("Invalid Credentials", exception.getMessage());
    }
}

