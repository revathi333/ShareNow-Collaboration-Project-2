package com.coll.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.Model.BlogLikes;
import com.coll.Model.UserLikedBlog;

@Repository("userLikedBlogDAO")
@Transactional
public class UserLikedBlogDAOImpl implements UserLikedBlogDAO 
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public UserLikedBlog getUserLikedBlog(BlogLikes id)
	{
		try
		{
			Session session = sessionFactory.getCurrentSession();
			UserLikedBlog userLikedBlog = session.get(UserLikedBlog.class, id);
			
			if(userLikedBlog!=null)
			{  
			   return userLikedBlog;
			}
			
			else
			{		
				UserLikedBlog obj = new UserLikedBlog();
				obj.setBlogLikes(id);
				session.save(obj);
				UserLikedBlog savedobj = session.get(UserLikedBlog.class, id);
			    return savedobj;
			}
		}
		
		catch(Exception e)
		{		
			return null;
		}
	}

	@Override
	public boolean updateLikeValue(UserLikedBlog userLikedBlog)
	{
		try
		{
			if(userLikedBlog.getLikedValue()==1)
				userLikedBlog.setLikedValue(0);
			else
				userLikedBlog.setLikedValue(1);
			
			sessionFactory.getCurrentSession().update(userLikedBlog);
			return true;
		}
		
		catch(Exception e)
		{
		     return false;
		}
	}

	@Override
	public boolean updateDislikedValue(UserLikedBlog userLikedBlog) 
	{
		try
		{
			if(userLikedBlog.getDislikedValue()==1)
			    userLikedBlog.setDislikedValue(0);	
			else 
				userLikedBlog.setDislikedValue(1);
			
			sessionFactory.getCurrentSession().update(userLikedBlog);
			return true;
		}
		
		catch(Exception e)
		{
	     	return false;
		}
	}

	@Override
	public boolean updateLike_DislikeValue(UserLikedBlog userLikedBlog) 
	{
		try
		{
			
			if((userLikedBlog.getLikedValue()==0)&&(userLikedBlog.getDislikedValue()==1))
			{
				userLikedBlog.setLikedValue(1);
			    userLikedBlog.setDislikedValue(0);	
			}    
			
			else if((userLikedBlog.getLikedValue()==1)&&(userLikedBlog.getDislikedValue()==0))
			{	
				userLikedBlog.setLikedValue(0);
		        userLikedBlog.setDislikedValue(1);				
			}
			
			sessionFactory.getCurrentSession().update(userLikedBlog);
			return true;
		}
		
		catch(Exception e)
		{
		    return false;
		}
	}

}
