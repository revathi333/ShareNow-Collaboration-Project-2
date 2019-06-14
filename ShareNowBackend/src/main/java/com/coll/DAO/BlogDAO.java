package com.coll.DAO;

import java.util.List;

import com.coll.Model.Blog;

public interface BlogDAO 
{
	public boolean addBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public Blog getBlog(int blogId);
	public List<Blog> listBlogs();
	public List<Blog> listBlogsbyUser(String username);
	public List<Blog> listBlogsforAdmin();

	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);

	public boolean incLikes(Blog blog);
	public boolean incDisLikes(Blog blog);
	
	public boolean decLikes(Blog blog);
	public boolean decDisLikes(Blog blog);
	
	public boolean decDisLikesincLikes(Blog blog);
	public boolean decLikesincDislikes(Blog blog) ;

}
