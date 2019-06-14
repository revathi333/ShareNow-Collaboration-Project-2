package com.coll.DAO;

import java.util.List;

import com.coll.Model.Job;

public interface JobDAO
{

	public boolean addJob(Job job);
	public List<Job> listJob();
	public Job getJob(int jobId);
	public boolean deleteJob(Job job);

}
