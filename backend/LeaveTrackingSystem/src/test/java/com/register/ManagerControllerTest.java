package com.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.register.controller.ManagerController;
import com.register.model.Leave;
import com.register.service.ManagerService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ManagerControllerTest {

    @InjectMocks
    private ManagerController leaveController;

    @Mock
    private ManagerService leaveService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLeavesByStatus() {
        // Define a sample status
        String status = "approved";

        // Create a sample list of Leave objects with the specified status
        List<Leave> sampleLeaves = new ArrayList<>();
        // Add some Leave objects to the list with the specified status

        // Mock the service method to return the sample list of Leave objects
        when(leaveService.getLeavesByStatus(status)).thenReturn(sampleLeaves);

        // Call the controller method
        List<Leave> result = leaveController.getLeavesByStatus(status);

        // Verify that the service method was called
        verify(leaveService, times(1)).getLeavesByStatus(status);

        // Assert the result
        assertEquals(sampleLeaves, result);
    }
    
    @Test
    public void testGetLeaves() {
        // Create a sample list of Leave objects
        List<Leave> sampleLeaves = new ArrayList<>();
        // Add some Leave objects to the list

        // Mock the service method to return the sample list of Leave objects
        when(leaveService.getLeaves()).thenReturn(sampleLeaves);

        // Call the controller method
        List<Leave> result = leaveController.getLeaves();

        // Verify that the service method was called
        verify(leaveService, times(1)).getLeaves();

        // Assert the result
        assertEquals(sampleLeaves, result);
    }

}
