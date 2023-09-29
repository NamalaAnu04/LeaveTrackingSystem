package com.register.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.model.Leave;
import com.register.model.User;
import com.register.repository.LeaveRepo;
import com.register.repository.UserRepo;
@Service
public class ManagerService {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private LeaveRepo lrepo;
	
	public List<Leave> getLeavesByStatus(String status){
        return lrepo.getLeavesByStatus(status);
    }

  
  public Leave updateLeaveStatus(int leaveId, String newStatus) {
        Optional<Leave> leaveOptional = lrepo.findById(leaveId);
            Leave leave = leaveOptional.get();
            leave.setStatus(newStatus);
            return lrepo.save(leave);
    }
  
  public Leave updateLeaveComment(int leaveId, String comment) {
        Optional<Leave> leaveOptional = lrepo.findById(leaveId);
            Leave leave = leaveOptional.get();
            leave.setComment(comment);
            return lrepo.save(leave);
    }
  public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUserByEmail(String email) {
		return repo.findByEmail(email);
	}
	public Leave getLeaveById(int leave_id) {
		  return lrepo.findById(leave_id).orElseThrow(()->new RuntimeException("No Leave Available with ID:"+leave_id));
	  }
	  
	 
	  
	  public void incrementLeaveCount(int incrementValue) {
		    List<User> users = repo.findAll(); // Assuming you have a method to retrieve all users

		    for (User user : users) {
		        String userEmail = user.getEmail();
		        int acceptedLeaveCount = lrepo.countByEmailAndStatus(userEmail, "Accepted");

		        int newLeaveCount = incrementValue;
		        int remainingLeaves = newLeaveCount - acceptedLeaveCount;
		        user.setLeave_count(newLeaveCount);
		        user.setRemaining_leaves(remainingLeaves);
		    }

		    repo.saveAll(users);
		}
	  
	  public List<Leave> getLeaves(){
		 List<Leave> leaves=lrepo.findAll();
		 return leaves;
	  }

	 
}
