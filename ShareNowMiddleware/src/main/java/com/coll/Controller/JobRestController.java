package com.coll.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.JobDAO;
import com.coll.Model.Job;
import com.google.gson.Gson;

@RestController
public class JobRestController
{
	@Autowired
	JobDAO jobDAO;
	
	@GetMapping("/showAllJobs")
	public ResponseEntity<List<Job>> showAllJobs()
	{
		List<Job> listJobs = (List<Job>)jobDAO.listJob();
				
		if(listJobs.size()>0)
			return new ResponseEntity<List<Job>>(listJobs,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<Job>>(listJobs,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/addJob")
	public ResponseEntity<String> addJob(@RequestBody Job job)
	{	
    		Gson gson = new Gson();
			String jsonString = gson.toJson("Job Added");
	        String jsonString2 = gson.toJson("Problem Adding");

		if(jobDAO.addJob(job))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable("jobId")int jobId)
	{	
		Job job= jobDAO.getJob(jobId);
		
		if(job!=null)
			return new ResponseEntity<Job>(job,HttpStatus.OK);

		else	
		    return new ResponseEntity<Job>(job,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/deleteJob/{jobId}")
	public ResponseEntity<Job> deleteJob(@PathVariable("jobId")int jobId)
	{	
		Job job= jobDAO.getJob(jobId);
		
		if(jobDAO.deleteJob(job))
			return new ResponseEntity<Job>(job,HttpStatus.OK);

		else	
		    return new ResponseEntity<Job>(job,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
