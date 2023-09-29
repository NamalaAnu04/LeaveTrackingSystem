package com.register.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.model.Leave;
import com.register.model.User;

public interface LeaveRepo extends JpaRepository<Leave, Integer>{
	public User findByStatus(String status);
	List<Leave> findByEmail(String email);
	int countByStatusAndEmail(String status, String emailAddress);
	List<Leave> getLeavesByStatus(String status);
	int countByEmailAndStatus(String email, String status);
}
