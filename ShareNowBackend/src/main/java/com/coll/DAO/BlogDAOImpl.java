package com.coll.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.Model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO
{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addBlog(Blog blog) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}	

	@Override
	public boolean deleteBlog(Blog blog)
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public boolean updateBlog(Blog blog)
	{
		try
		{
			blog.setBlogUpdatedDate(new java.util.Date());
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
	    	return false;
		}
	}

	@Override
	public Blog getBlog(int blogId)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Blog blog = session.get(Blog.class, blogId);
			session.close();
			return blog;
		}		
		catch(Exception e)
		{
		    return null;
		}
	}

	@Override
	public List<Blog> listBlogs()
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Blog where status='A'");
			List<Blog> listBlogs = (List<Blog>) query.list();
 			session.close();
			return listBlogs;
		}		
		catch(Exception e)
		{
		    return null;
		}
	}
	
	@Override
	public List<Blog> listBlogsforAdmin()
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Blog");
			List<Blog> listBlogs = (List<Blog>) query.list();
 			session.close();
			return listBlogs;
		}		
		catch(Exception e)
		{
	     	return null;
		}
	}

	@Override
	public boolean approveBlog(Blog blog)
	{
		try
		{
			blog.setStatus("A");
			blog.setBlogUpdatedDate(new java.util.Date());
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public boolean rejectBlog(Blog blog) 
	{
		try
		{
			blog.setStatus("NA");
			blog.setBlogUpdatedDate(new java.util.Date());
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public boolean incLikes(Blog blog) 
	{
		try
		{
			blog.setLikes(blog.getLikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public boolean incDisLikes(Blog blog)
	{
		try
		{
			blog.setDislikes(blog.getDislikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public List<Blog> listBlogsbyUser(String username)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Blog where username=:username");
			query.setParameter("username", username);
			List<Blog> listBlogsbyUser = (List<Blog>) query.list();
 			session.close();
			return listBlogsbyUser;
		}		
		catch(Exception e)
		{
		    return null;
		}
	}

	@Override
	public boolean decLikes(Blog blog) 
	{
		try
		{
			blog.setLikes(blog.getLikes()-1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public boolean decDisLikes(Blog blog) 
	{
		try
		{
			blog.setDislikes(blog.getDislikes()-1);			
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	
	@Override
	public boolean decDisLikesincLikes(Blog blog) 
	{
		try
		{
			blog.setDislikes(blog.getDislikes()-1);	
			blog.setLikes(blog.getLikes()+1);
			
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}

	@Override
	public boolean decLikesincDislikes(Blog blog) 
	{
		try
		{
			blog.setLikes(blog.getLikes()-1);
			blog.setDislikes(blog.getDislikes()+1);
			
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}		
		catch(Exception e)
		{
		    return false;
		}
	}
}
