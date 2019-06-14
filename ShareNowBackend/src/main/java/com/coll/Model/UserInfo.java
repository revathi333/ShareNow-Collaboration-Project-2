package com.coll.Model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints= {@UniqueConstraint(columnNames = "username"),@UniqueConstraint(columnNames="emailId")})
public class UserInfo
{
	@Id 
	@Column(nullable=false)
	String username;
	@Column(nullable=false)
	String password;
	@Column(nullable=false)
	String memberName;
	@Column(nullable=false)
	String emailId;
	String role;
	String status;
	String isOnline;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	
}
