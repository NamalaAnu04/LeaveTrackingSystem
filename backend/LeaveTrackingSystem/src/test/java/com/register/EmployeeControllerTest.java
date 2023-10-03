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
        String email = "anunamala04@gmail.com";
        when(leaveService.getLeaveCountByEmail(email)).thenReturn(9);
        ResponseEntity<Integer> response = leaveController.getLeaveCount(email);
        verify(leaveService, times(1)).getLeaveCountByEmail(email);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(9, response.getBody());
    }

    @Test
    public void testGetLeaveCount_NotFound() {
        String email = "anu04@gmail.com";
        when(leaveService.getLeaveCountByEmail(email));
        ResponseEntity<Integer> response = leaveController.getLeaveCount(email);
        verify(leaveService, times(1)).getLeaveCountByEmail(email);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    public void testGetLeavesByEmail() {
        String email = "anunamala04@gmail.com";
        List<Leave> sampleLeaveList = new ArrayList<>();
        when(leaveService.getLeavesByEmail(email)).thenReturn(sampleLeaveList);
        List<Leave> result = leaveController.getLeavesByEmail(email);
        verify(leaveService, times(1)).getLeavesByEmail(email);
        assertEquals(sampleLeaveList, result);
    }
}
