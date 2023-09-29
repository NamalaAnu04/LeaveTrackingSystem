package com.register.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.model.User;
import com.register.repository.UserRepo;


@Service
public class RegistrationService {
	@Autowired
	private UserRepo repo;
	
	public User fetchUserByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email,password);
	}
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUserByEmail(String email) {
		return repo.findByEmail(email);
	}
}
