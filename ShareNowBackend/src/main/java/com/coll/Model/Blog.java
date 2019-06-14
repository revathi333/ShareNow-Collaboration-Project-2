package com.coll.Model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Blog 
{

	@Id
	@GeneratedValue
	int blogId;
	@Size(min=1)
	String blogName;
	@Size(min=1)
	String blogContent;
	String username;
	@JsonFormat(shape=JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
	Date createDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
	Date blogUpdatedDate;
	String status;
	int likes;
	int dislikes;
	
	//@OneToMany(targetEntity=BlogComment.class,mappedBy="blog",cascade=CascadeType.REMOVE)
	//List<BlogComment> blogComments;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getBlogUpdatedDate() {
		return blogUpdatedDate;
	}
	public void setBlogUpdatedDate(Date blogUpdatedDate) {
		this.blogUpdatedDate = blogUpdatedDate;
	}
	
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
}
