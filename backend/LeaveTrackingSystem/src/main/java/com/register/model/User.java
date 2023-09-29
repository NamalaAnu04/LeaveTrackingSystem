package com.register.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String fname;
    private String lname;
    private String password;
    private int leave_count=9;
    private int remaining_leaves = leave_count;
    private String role;
    
    
    
    public int getRemaining_leaves() {
		return remaining_leaves;
	}

	public void setRemaining_leaves(int remaining_leaves) {
		this.remaining_leaves = remaining_leaves;
	}

	public User(long id, String email, String fname, String lname, String password,String role) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.role=role;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) { // Change the parameter type to long
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLeave_count() {
        return leave_count;
    }

    public void setLeave_count(int leave_count) {
        this.leave_count = leave_count;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
