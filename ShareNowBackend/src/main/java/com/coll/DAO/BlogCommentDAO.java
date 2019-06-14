package com.coll.DAO;

import java.util.List;

import com.coll.Model.BlogComment;

public interface BlogCommentDAO 
{
	public boolean addBlogComment(BlogComment blogComment);
	
	public boolean deleteBlogComment(BlogComment blogComment);
	
	public boolean deleteBlogIdComments(int id);	
	
	public boolean updateBlogComment(BlogComment blogComment);
	
	public BlogComment getBlogComment(int blogCommentId);
	
	public List<BlogComment> listBlogComments(int blogId);
	
	public boolean incCommentLikes(BlogComment blogComment);

}
