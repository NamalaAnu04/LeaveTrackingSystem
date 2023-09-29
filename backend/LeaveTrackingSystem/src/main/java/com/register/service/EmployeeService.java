package com.register.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.model.Leave;
import com.register.model.User;
import com.register.repository.LeaveRepo;
import com.register.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private LeaveRepo lrepo;
	
	public Leave saveLeave(Leave leave) {
		return lrepo.save(leave);
	}

	  public int getLeaveCountByEmail(String email) {
	        User user = repo.findByEmail(email);
	        
	        if (user != null) {
	            return user.getLeave_count();
	        } else {
	            throw new EntityNotFoundException("User not found with email: " + email);
	        }
	    }
	  public int getRemainingLeavesByEmail(String email) {
		  User user = repo.findByEmail(email);
	        
	        if (user != null) {
	            return user.getRemaining_leaves();
	        } else {
	            throw new EntityNotFoundException("User not found with email: " + email);
	        }
	    }
	  public List<Leave> getLeavesByEmail(String email) {
	        return lrepo.findByEmail(email);
	    }
	  
	  public String deleteLeave(int leave_id) {
		    Leave leave = lrepo.findById(leave_id).orElseThrow(() -> new RuntimeException("No Leave Available with ID:" + leave_id));
		    
		    if (leave.getStatus().equals("pending")) {
		        lrepo.deleteById(leave_id);
		        return "Leave Deleted";
		    } else {
		        return "Leave cannot be deleted because its status is not 'pending'.";
		    }
		}
	  
	  public Leave getLeaveById(int leave_id) {
		  return lrepo.findById(leave_id).orElseThrow(()->new RuntimeException("No Leave Available with ID:"+leave_id));
	  }

	  public Leave updateLeave(Leave leave,int leave_id) {
			 Leave existing = lrepo.findById(leave_id).orElseThrow(()->new RuntimeException("No Leave Available with ID:"+leave_id));
			 if(leave.getLeave_type()!=null) {
				 existing.setLeave_type(leave.getLeave_type());
			 }
			 if(leave.getStart_date()!=null) {
				 existing.setStart_date(leave.getStart_date());
			 }
			 if(leave.getEnd_date()!=null) {
				 existing.setEnd_date(leave.getEnd_date());
			 }
			 if(leave.getReason()!=null) {
				 existing.setReason(leave.getReason());
			 }
			 
			 return lrepo.save(existing);
			 
		 }
	  
	  public int getCountOfAcceptedLeavesForEmail(String status, String emailAddress) {
	        return lrepo.countByStatusAndEmail(status, emailAddress);
	    }
	  
	  public int getCountOfRejectedLeavesForEmail(String status, String emailAddress) {
	        return lrepo.countByStatusAndEmail(status, emailAddress);
	    }
	  public int getCountOfPendingLeavesForEmail(String status, String emailAddress) {
	        return lrepo.countByStatusAndEmail(status, emailAddress);
	    }
	  
	  
}
