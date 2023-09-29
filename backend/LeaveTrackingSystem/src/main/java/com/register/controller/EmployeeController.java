package com.register.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.register.model.Leave;
import com.register.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/applyleave")
    public ResponseEntity<String> applyLeave(@RequestBody Leave leave) {
            service.saveLeave(leave);
            return ResponseEntity.ok("{\"message\": \"Leave applied successfully\"}"); 
    }
	@GetMapping("/getLeaveCount")
    public ResponseEntity<Integer> getLeaveCount(@RequestParam String email) {
        try {
            int leaveCount = service.getLeaveCountByEmail(email);
            return ResponseEntity.ok(leaveCount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	@GetMapping("/getRemainingleaves")
    public ResponseEntity<Integer> getRemainingLeaves(@RequestParam String email) {
        try {
            int leaveCount = service.getRemainingLeavesByEmail(email);
            return ResponseEntity.ok(leaveCount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	@GetMapping("/getLeavesByEmail")
    public List<Leave> getLeavesByEmail(@RequestParam String email) {
        return service.getLeavesByEmail(email);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteLeave(@PathVariable int id) {
	    service.deleteLeave(id);
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Leave Deleted");

	    return ResponseEntity.ok(response);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Leave> updateLeave(@PathVariable int id,@RequestBody Leave leave){
		return new ResponseEntity<Leave>(service.updateLeave(leave, id),HttpStatus.CREATED);
	}

	@GetMapping("/getleave/{id}")
	public ResponseEntity<Leave> getLeaveById(@PathVariable int id){
		return new ResponseEntity<Leave>(service.getLeaveById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getAcceptedLeavesCount")
    public int getAcceptedLeavesCount(@RequestParam String status, @RequestParam String email) {
        int count = service.getCountOfAcceptedLeavesForEmail(status, email);
        return count;
    }
	@GetMapping("/getRejectedLeavesCount")
    public int getRejectedLeavesCount(@RequestParam String status, @RequestParam String email) {
        int count = service.getCountOfRejectedLeavesForEmail(status, email);
        return count;
    }
	@GetMapping("/getPendingLeavesCount")
    public int getPendingLeavesCount(@RequestParam String status, @RequestParam String email) {
        int count = service.getCountOfPendingLeavesForEmail(status, email);
        return count;
    }
	
	
}
