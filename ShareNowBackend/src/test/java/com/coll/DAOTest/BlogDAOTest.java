package com.coll.DAOTest;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.DAO.BlogCommentDAO;
import com.coll.DAO.BlogDAO;
import com.coll.Model.Blog;

public class BlogDAOTest 
{
	static BlogDAO blogDAO;
	static BlogCommentDAO blogCommentDAO;
	
	@BeforeClass
	public static void initailize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.coll");
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
	}

	@Ignore
	@Test
	public void addBlogTest()
	{
		Blog blog = new Blog();
		blog.setBlogContent("Wonderful world with many surprisesgfgdfgd");
		blog.setBlogName("World Tourindxfgdfgfdgg");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setUsername("revathi");
		blog.setStatus("NA");
		
		assertTrue("Adding Issue",blogDAO.addBlog(blog));
		
	}
	
	@Ignore
	@Test
	public void deleteBlogTest()
	{
		Blog blog = blogDAO.getBlog(756);
		System.out.println(blogCommentDAO.deleteBlogIdComments(756));
		
		assertTrue("Deleting Issue",blogDAO.deleteBlog(blog));
	}
	
	@Ignore
	@Test
	public void updateBlogTest()
	{
		Blog blog = blogDAO.getBlog(595);
		blog.setUsername("areeb");
		blog.setStatus("NA");

		blog.setCreateDate(new java.util.Date());
		assertTrue("Updating Issue",blogDAO.updateBlog(blog));
	}
	
	@Ignore
	@Test
	public void listBlogsTest()
	{
		List<Blog> listBlogs = blogDAO.listBlogs();
		
		assertTrue("Retriving Issue",listBlogs.size()>0);
		
		for(Blog blog : listBlogs)
		{
			blog.setDislikes(0);
			blogDAO.updateBlog(blog);
			System.out.println(blog.getUsername());
		}
	}
	
	@Ignore
	@Test
	public void listBlogsbyUserTest()
	{
		List<Blog> listBlogsbyUser = blogDAO.listBlogsbyUser("areeb");
		
		assertTrue("Retriving Issue",listBlogsbyUser.size()>0);
		
		for(Blog blog : listBlogsbyUser)
		{
			System.out.println(blog.getBlogContent());
		}
	}
	
	@Ignore
	@Test
	public void approveBlogTest() 
	{
		Blog blog = blogDAO.getBlog(577);
		assertTrue("Updating Issue",blogDAO.approveBlog(blog));
	}

	@Ignore
	@Test
	public void rejectBlogTest() 
	{
		Blog blog = blogDAO.getBlog(576);
		assertTrue("Updating Issue",blogDAO.rejectBlog(blog));
	}

	@Ignore
	@Test
	public void incLikesTest()
	{
		Blog blog = blogDAO.getBlog(576);
		assertTrue("Updating Issue",blogDAO.incLikes(blog));
	}

	@Ignore
	@Test
	public void incDisLikesTest()
	{
		Blog blog = blogDAO.getBlog(576);
		assertTrue("Updating Issue",blogDAO.incDisLikes(blog));
	}

}
