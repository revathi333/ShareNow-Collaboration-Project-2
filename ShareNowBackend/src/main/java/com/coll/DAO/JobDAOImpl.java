package com.coll.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.Model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addJob(Job job) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}		
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Job> listJob()
	{
		try
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Job.class);
			List<Job> listJob = (List<Job>) criteria.list();
 			session.close();
			return listJob;
		}		
		catch(Exception e)
		{
		    return null;
		}
	}

	@Override
	public Job getJob(int jobId)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Job job = session.get(Job.class, jobId);
			session.close();
			return job;
		}		
		catch(Exception e)
		{
		    return null;
		}
	}

	@Override
	public boolean deleteJob(Job job) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}		
		catch(Exception e)
		{
			return false;
		}
	}
}
