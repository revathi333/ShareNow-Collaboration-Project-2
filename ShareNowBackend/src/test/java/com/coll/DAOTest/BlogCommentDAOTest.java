package com.coll.DAOTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.DAO.BlogCommentDAO;
import com.coll.Model.BlogComment;

public class BlogCommentDAOTest 
{
static BlogCommentDAO blogCommentDAO;
	
	@BeforeClass
	public static void initailize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.coll");
		
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
	}
	
	@Ignore
	@Test
	public void addBlogCommentTest()
	{
		BlogComment blogComment = new BlogComment();
		blogComment.setCommentText("Great to hear that");
		blogComment.setBlogId(761);
		blogComment.setCommentedDate(new java.util.Date());
		blogComment.setCommentLikes(0);
		blogComment.setUsername("raghu");
		
		assertTrue("Adding Issue",blogCommentDAO.addBlogComment(blogComment));
		
	}
	
	@Ignore
	@Test
	public void updateBlogCommentTest()
	{
		BlogComment blogComment = blogCommentDAO.getBlogComment(584);
		blogComment.setCommentText("Great Job Baby!!");;
		
		assertTrue("Updating Issue",blogCommentDAO.updateBlogComment(blogComment));
	}
	
	@Ignore
	@Test
	public void deleteBlogCommentTest()
	{
		BlogComment blogComment = blogCommentDAO.getBlogComment(604);
		
		assertTrue("Deleting Issue",blogCommentDAO.deleteBlogComment(blogComment));
	}
	
	@Ignore
	@Test
	public void listBlogsTest()
	{
		List<BlogComment> listBlogComments = blogCommentDAO.listBlogComments(581);
		
		assertTrue("Retriving Issue",listBlogComments.size()>0);
		
		for(BlogComment blogComment : listBlogComments)
		{
			blogCommentDAO.updateBlogComment(blogComment);
			System.out.println(blogComment.getCommentText());
		}
	}
	
	@Ignore
	@Test
	public void incCommentLikesTest()
	{
		BlogComment blogComment = blogCommentDAO.getBlogComment(592);
		assertTrue("Updating Issue",blogCommentDAO.incCommentLikes(blogComment));
	}
	
}
