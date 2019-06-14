package com.coll.DAO;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.Model.UserInfo;

@Repository("userInfoDAO")
@Transactional
public class UserInfoDAOImpl implements UserInfoDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean registerUser(UserInfo user) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(user);
			return true;
		}		
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateUser(UserInfo user)
	{
		try
		{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}		
		catch(Exception e)
		{
			return false;
		}
	}


	@Override
	public UserInfo getUser(String username) 
	{
		try
		{
			Session session = sessionFactory.openSession();
			UserInfo user = session.get(UserInfo.class, username);
			session.close();
			return user;
		}		
		catch(Exception e)
		{
	    	return null;
		}
	}

	@Override
	public UserInfo checkUser(UserInfo user)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from UserInfo where username=:uname and password=:pwd");
			query.setParameter("uname", user.getUsername());
			query.setParameter("pwd", user.getPassword());
			List<UserInfo> userPresent = (List<UserInfo>) query.list();
			session.close();
            			
			if(userPresent.size()>0)
			  return userPresent.get(0);
			
			else
			return null;
		}		
		catch(Exception e)
		{
		   return null;
		}
	}

}
