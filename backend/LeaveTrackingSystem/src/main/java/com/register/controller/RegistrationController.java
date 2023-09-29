package com.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.register.model.User;
import com.register.service.RegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	User userObj=null;
	
	@Autowired
	private RegistrationService service;
	
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempemail= user.getEmail();
		if(tempemail!=null && !"".equals(tempemail)) {
			User tempuser= service.fetchUserByEmail(tempemail);
			if(tempuser!=null) {
				throw new Exception("User with "+tempemail+" is already Exists");
			}
		}
		return service.saveUser(user);
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception{
		String tempemail=user.getEmail();
		String temppass=user.getPassword();
		if(tempemail!=null && temppass!=null) {
			userObj=service.fetchUserByEmailAndPassword(tempemail,temppass);
		}
		if(userObj==null) {
			throw new Exception("Invalid Credentials");
		}
		return userObj;
	}
	
	}
