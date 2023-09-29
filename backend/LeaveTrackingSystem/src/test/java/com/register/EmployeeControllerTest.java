package com.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.register.controller.EmployeeController;
import com.register.model.Leave;
import com.register.service.EmployeeService;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController leaveController;

    @Mock
    private EmployeeService leaveService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLeaveCount_Success() {
        // Define a sample email
        String email = "user@example.com";

        // Mock the service method to return a leave count
        when(leaveService.getLeaveCountByEmail(email)).thenReturn(5); // Replace 5 with the expected count

        // Call the controller method
        ResponseEntity<Integer> response = leaveController.getLeaveCount(email);

        // Verify that the service method was called
        verify(leaveService, times(1)).getLeaveCountByEmail(email);

        // Assert the response status code and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody()); // Replace 5 with the expected count
    }

    @Test
    public void testGetLeaveCount_NotFound() {
        // Define a sample email
        String email = "nonexistent@example.com";

        // Mock the service method to throw an exception (simulating a not found scenario)
        when(leaveService.getLeaveCountByEmail(email));

        // Call the controller method
        ResponseEntity<Integer> response = leaveController.getLeaveCount(email);

        // Verify that the service method was called
        verify(leaveService, times(1)).getLeaveCountByEmail(email);

        // Assert the response status code (should be NOT_FOUND)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    public void testGetLeavesByEmail() {
        // Define a sample email
        String email = "user@example.com";

        // Create a sample list of Leave objects
        List<Leave> sampleLeaveList = new ArrayList<>();
        // Add some Leave objects to the list

        // Mock the service method to return the sample list of Leave objects
        when(leaveService.getLeavesByEmail(email)).thenReturn(sampleLeaveList);

        // Call the controller method
        List<Leave> result = leaveController.getLeavesByEmail(email);

        // Verify that the service method was called
        verify(leaveService, times(1)).getLeavesByEmail(email);

        // Assert the result
        assertEquals(sampleLeaveList, result);
    }
}
