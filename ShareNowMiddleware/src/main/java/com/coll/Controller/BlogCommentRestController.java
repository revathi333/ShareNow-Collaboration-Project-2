package com.coll.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.BlogCommentDAO;
import com.coll.Model.BlogComment;
import com.coll.Model.UserInfo;
import com.google.gson.Gson;

@RestController
public class BlogCommentRestController
{
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	
	@GetMapping("/showAllBlogComments/{blogId}")
	public ResponseEntity<List<BlogComment>> showAllBlogComments(@PathVariable("blogId")int blogId)
	{
		List<BlogComment> listBlogComments = (List<BlogComment>)blogCommentDAO.listBlogComments(blogId);
				
		if(listBlogComments.size()>0)
			return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@PostMapping("/addBlogComment")
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session)
	{
		Gson gson = new Gson();
		String jsonString = gson.toJson("BlogComment Added");
		String jsonString2 = gson.toJson("Problem Adding");
		
		blogComment.setCommentedDate(new java.util.Date());
		blogComment.setCommentLikes(0);
		
		UserInfo loggedUser = (UserInfo) session.getAttribute("user");
		String username = loggedUser.getUsername();

		blogComment.setUsername(username);
		
		if(blogCommentDAO.addBlogComment(blogComment))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@GetMapping("/deleteBlogComment/{blogCommentId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogCommentId")int blogCommentId)
	{	
		Gson gson = new Gson();
		String jsonString = gson.toJson("BlogComment Deleted");
		String jsonString2 = gson.toJson("Problem Deleting");
		
		BlogComment blogComment = blogCommentDAO.getBlogComment(blogCommentId);
		
		if(blogCommentDAO.deleteBlogComment(blogComment))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/incCommentLikes/{blogCommentId}")
	public ResponseEntity<String> incDisLikesBlog(@PathVariable("blogCommentId")int blogCommentId)
	{	
		Gson gson = new Gson();
		String jsonString = gson.toJson("BlogComment Likes incremented");
		String jsonString2 = gson.toJson("Problem incrementing");
		
		BlogComment blogComment = blogCommentDAO.getBlogComment(blogCommentId);
		
		if(blogCommentDAO.incCommentLikes(blogComment))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/getBlogComment/{blogCommentId}")
	public ResponseEntity<BlogComment> getBlogComment(@PathVariable("blogCommentId")int blogCommentId)
	{	
		BlogComment blogComment = blogCommentDAO.getBlogComment(blogCommentId);
		
		if(blogComment!=null)
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);

		else	
		    return new ResponseEntity<BlogComment>(blogComment,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping("/updateBlogComment")
	public ResponseEntity<BlogComment> updateBlogComment(@RequestBody BlogComment blogComment)
	{
		
		if(blogCommentDAO.updateBlogComment(blogComment))
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);

		else	
		    return new ResponseEntity<BlogComment>(blogComment,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
