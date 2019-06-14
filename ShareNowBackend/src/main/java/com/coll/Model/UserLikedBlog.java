package com.coll.Model;

import javax.persistence.*;

@Entity
@Table                              //A user can either like or dislike a blog but not both ,so each user contributes 1 int value to like/dislike.
public class UserLikedBlog          //This model is to track user liking activity of the blog 
{
	@EmbeddedId
	public BlogLikes blogLikes;
	
	int likedValue=0;
	int dislikedValue=0;
	
	
	public BlogLikes getBlogLikes() {
		return blogLikes;
	}
	public void setBlogLikes(BlogLikes blogLikes) {
		this.blogLikes = blogLikes;
	}
	public int getLikedValue() {
		return likedValue;
	}
	public void setLikedValue(int likedValue) {
		this.likedValue = likedValue;
	}
	public int getDislikedValue() {
		return dislikedValue;
	}
	public void setDislikedValue(int dislikedValue) {
		this.dislikedValue = dislikedValue;
	}
}

