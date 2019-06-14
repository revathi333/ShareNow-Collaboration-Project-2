package com.coll.Model;

import javax.persistence.*;

@Entity
@Table
public class ProfilePicture 
{
	@Id 
	@Column(nullable=false)
	private String username;
	private byte[] image;
		
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
 }
