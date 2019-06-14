package com.coll.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.Model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addBlogComment(BlogComment blogComment)
	{
		try
		{
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public boolean deleteBlogComment(BlogComment blogComment)
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}
	

	@Override
	public boolean deleteBlogIdComments(int id)
	{
		try
		{
			Query query = sessionFactory.getCurrentSession().createQuery("from BlogComment where blogId=:id");
			query.setParameter("id", id);
			List<BlogComment> listBlogComments = (List<BlogComment>) query.list();
			
			for(BlogComment comment : listBlogComments)
			{
				sessionFactory.getCurrentSession().delete(comment);
			}
			return true;
		}		
		catch(Exception e)
		{  
		    return false;
		}
	}
	
	@Override
	public boolean updateBlogComment(BlogComment blogComment)
	{
		try
		{
			blogComment.setCommentUpdatedDate(new java.util.Date());
			sessionFactory.getCurrentSession().update(blogComment);
			return true;
		}		
		catch(Exception e)
		{
		     return false;
		}
	}

	@Override
	public BlogComment getBlogComment(int blogCommentId)
	{
		try
		{
			Session session = sessionFactory.openSession();
			BlogComment BlogComment = session.get(BlogComment.class, blogCommentId);
			session.close();
			return BlogComment;
		}		
		catch(Exception e)
		{
	     	return null;
		}
	}

	@Override
	public List<BlogComment> listBlogComments(int blogId)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from BlogComment where blogId=:blogId");
			query.setParameter("blogId", blogId);
			List<BlogComment> listBlogComments = (List<BlogComment>) query.list();
 			session.close();
			return listBlogComments;
		}		
		catch(Exception e)
		{
		    return null;
		}
	}

	@Override
	public boolean incCommentLikes(BlogComment blogComment) 
	{
		try
		{
			blogComment.setCommentLikes(blogComment.getCommentLikes()+1);
			sessionFactory.getCurrentSession().update(blogComment);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

}
