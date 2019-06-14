package com.coll.DAOTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.DAO.UserLikedBlogDAO;
import com.coll.Model.*;


public class UserLikedBlogDAOTest 
{
	static UserLikedBlogDAO userLikedBlogDAO;

    @BeforeClass
    public static void initailize()
    {
	  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.coll");
	
	  userLikedBlogDAO = (UserLikedBlogDAO) context.getBean("userLikedBlogDAO");
    }  
    
    @Ignore
    @Test
	public void getUserLikedBlog()
	{
    	BlogLikes blogLikes = new BlogLikes();
    	blogLikes.setBlogId(57790);
    	blogLikes.setUsername("revathi");
    	
    	UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
    	
    	assertNotNull("row doesn't exist",userLikedBlog);
    	
    	System.out.println(userLikedBlog.getBlogLikes().getUsername());
	}
    
    
    @Ignore
    @Test
  	public void updateLikeValue()
  	{
    	BlogLikes blogLikes = new BlogLikes();
    	blogLikes.setBlogId(577);
    	blogLikes.setUsername("revathi");
    	
    	UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
    	
    	assertTrue("Updating Like Issue",userLikedBlogDAO.updateLikeValue(userLikedBlog));
    	
    	System.out.println(userLikedBlog.getLikedValue());
  	}
    
    @Ignore
    @Test
  	public void updateDislikedValue()
  	{
    	BlogLikes blogLikes = new BlogLikes();
    	blogLikes.setBlogId(57);
    	blogLikes.setUsername("rethi");
    	
    	UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
    	
    	assertTrue("Updating Like Issue",userLikedBlogDAO.updateDislikedValue(userLikedBlog));
  	}

    
    @Test
  	public void updateLike_DislikeValue()
  	{
    	BlogLikes blogLikes = new BlogLikes();
    	blogLikes.setBlogId(57);
    	blogLikes.setUsername("rethi");
    	
    	UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
    	
    	assertTrue("Updating Like Issue",userLikedBlogDAO.updateLike_DislikeValue(userLikedBlog));
  	}
}
