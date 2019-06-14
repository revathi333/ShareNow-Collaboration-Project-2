package com.coll.Model;

import javax.persistence.*;

@Entity
@Table
public class Friend 
{
	@Id
	@GeneratedValue
	int friendId;
	String username;
	String friendusername;
	String status;
	
	
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriendusername() {
		return friendusername;
	}
	public void setFriendusername(String friendusername) {
		this.friendusername = friendusername;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
