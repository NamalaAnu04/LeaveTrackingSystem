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
        String status = "approved";
        List<Leave> sampleLeaves = new ArrayList<>();
        when(leaveService.getLeavesByStatus(status)).thenReturn(sampleLeaves);
        List<Leave> result = leaveController.getLeavesByStatus(status);
        verify(leaveService, times(1)).getLeavesByStatus(status);
        assertEquals(sampleLeaves, result);
    }
    
    @Test
    public void testGetLeaves() {
        List<Leave> sampleLeaves = new ArrayList<>();
        when(leaveService.getLeaves()).thenReturn(sampleLeaves);
        List<Leave> result = leaveController.getLeaves();
        verify(leaveService, times(1)).getLeaves();
        assertEquals(sampleLeaves, result);
    }

}
