package com.coll.Model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BlogLikes implements Serializable
{
	int blogId;
	String username;
	
	public BlogLikes(){}

	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
