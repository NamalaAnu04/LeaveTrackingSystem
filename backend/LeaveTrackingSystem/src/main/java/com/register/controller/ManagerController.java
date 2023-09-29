package com.register.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.register.model.Leave;
import com.register.model.User;
import com.register.service.ManagerService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerController {
	@Autowired
	private ManagerService service;
	
	@GetMapping("/getLeavesByStatus")
    public List<Leave> getLeavesByStatus(@RequestParam String status) {
        return service.getLeavesByStatus(status);
    }
	
	@PutMapping("/accept/{leaveId}")
	public ResponseEntity<Map<String, String>> acceptLeave(@PathVariable int leaveId) {
	    Leave updatedLeave = service.updateLeaveStatus(leaveId, "Accepted");
	    Leave leaveToaccept = service.getLeaveById(leaveId); 
	    if (leaveToaccept == null) {
	        return ResponseEntity.notFound().build();
	    }
	    String userEmail = leaveToaccept.getEmail();

	    User user = service.fetchUserByEmail(userEmail);

	    if (user == null) {
	        return ResponseEntity.notFound().build();
	    }

	    int updatedLeaveCount = user.getLeave_count() - 1;
	    user.setLeave_count(updatedLeaveCount);

	    service.saveUser(user);
	    if (updatedLeave != null) {
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Leave has been accepted.");
	        
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping("/reject/{leaveId}")
	public ResponseEntity<Map<String, String>> rejectLeave(@PathVariable int leaveId) {
	    Leave updatedLeave = service.updateLeaveStatus(leaveId, "Rejected");
	    if (updatedLeave != null) {
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Leave has been rejected.");
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.notFound().build();
	 }
	    }
	 
	 @PutMapping("/updateComment/{leaveId}")
	 public ResponseEntity<Map<String, String>> updateComment(@PathVariable int leaveId, @RequestBody String comment)
	 {
		    Leave updatedLeave = service.updateLeaveComment(leaveId, comment);
		    if (updatedLeave != null) {
		        Map<String, String> response = new HashMap<>();
		        response.put("message", "Leave has been updated.");
		        return ResponseEntity.ok(response);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
	 @CrossOrigin(origins = "http://localhost:4200")
	 @PutMapping("/incrementLeaveCount")
	 public ResponseEntity<Map<String, String>> incrementLeaveCount(@RequestParam int incrementValue) {
	     service.incrementLeaveCount(incrementValue);

	     Map<String, String> response = new HashMap<>();
	     response.put("message", "Leave count incremented for all users.");

	     return ResponseEntity.ok(response);
	 }


	    @GetMapping("/getLeaves") 
	    public List<Leave> getLeaves() {
	        return service.getLeaves();
	    }
}
