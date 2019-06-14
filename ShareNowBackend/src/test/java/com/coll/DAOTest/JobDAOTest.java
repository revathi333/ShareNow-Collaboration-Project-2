package com.coll.DAOTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.DAO.JobDAO;
import com.coll.Model.Job;


public class JobDAOTest 
{
static JobDAO jobDAO;
	
	@BeforeClass
	public static void initailize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.coll");
		
		jobDAO = (JobDAO) context.getBean("jobDAO");
	}

	@Ignore
	@Test
	public void addJobTest()
	{
		Job job = new Job();
		job.setCompanyName("ACT");
		job.setCtc(200000);
		job.setDesignation("Developer");
		job.setJobDesc("Frontend App Developer");
		job.setJobLocation("Bangalore");
		job.setRequiredSkills("Degree in Computer Science Engineering");
		job.setLastDatetoApply(new java.util.Date(2019,06,15));
		
		assertTrue("Adding Issue",jobDAO.addJob(job));
	}
	
	@Ignore
	@Test
	public void listJobTest()
	{
		List<Job> listJob = jobDAO.listJob();
		
		assertTrue("Retriving Issue",listJob.size()>0);
		
		for(Job job : listJob)
		{
			System.out.println(job.getCompanyName());
		}
	}
	
	@Ignore
	@Test
	public void deleteJobTest()
	{
		Job job = jobDAO.getJob(741);
		
		assertTrue("Retriving Issue",jobDAO.deleteJob(job));
		
	}

}
