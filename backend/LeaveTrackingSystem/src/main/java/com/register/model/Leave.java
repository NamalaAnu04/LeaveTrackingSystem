package com.register.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_request")
public class Leave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leave_id;
	private String leave_type;
	private Date start_date;
	private Date end_date;
	private String reason;
	private String status="pending";
	private String email;
	private String comment="No Comments";
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Leave(int leave_id, String leave_type, Date start_date, Date end_date, String reason, String status,String email) {
		super();
		this.leave_id = leave_id;
		this.leave_type = leave_type;
		this.start_date = start_date;
		this.end_date = end_date;
		this.reason = reason;
		this.status =status;
		this.email=email;
	}
	public Leave() {
		
	}
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int l) {
		this.leave_id = l;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date date) {
		this.start_date = (Date) date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
	

}
