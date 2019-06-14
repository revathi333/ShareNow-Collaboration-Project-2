package com.coll.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.UserLikedBlogDAO;
import com.coll.Model.BlogLikes;
import com.coll.Model.UserInfo;
import com.coll.Model.UserLikedBlog;
import com.google.gson.Gson;

@RestController
public class UserLikedBlogRestController 
{
	
	@Autowired
	UserLikedBlogDAO userLikedBlogDAO;
	
	@GetMapping("/getUserLikedBlog/{blogId}")
	public ResponseEntity<UserLikedBlog> getUserLikedBlog(@PathVariable("blogId")int blogId,HttpSession session)
	{
		UserInfo loggedUser = (UserInfo) session.getAttribute("user");
		
		String username = loggedUser.getUsername();
		
		BlogLikes blogLikes = new BlogLikes();
		blogLikes.setBlogId(blogId);
		blogLikes.setUsername(username);
		
		UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
		
		if(userLikedBlog!=null)
			return new ResponseEntity<UserLikedBlog>(userLikedBlog,HttpStatus.OK);
		
		else	
		    return new ResponseEntity<UserLikedBlog>(userLikedBlog,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/updateLikeValue/{blogId}")
	public ResponseEntity<String> updateLikeValue(@PathVariable("blogId")int blogId,HttpSession session)
	{
		UserInfo loggedUser = (UserInfo) session.getAttribute("user");
		String username = loggedUser.getUsername();
		
		BlogLikes blogLikes = new BlogLikes();
		blogLikes.setBlogId(blogId);
		blogLikes.setUsername(username);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson("Like value updated");
		String jsonString2 = gson.toJson("Problem updating");
		
		UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
		
		if(userLikedBlogDAO.updateLikeValue(userLikedBlog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);
		
		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/updateDislikedValue/{blogId}")
	public ResponseEntity<String> updateDislikedValue(@PathVariable("blogId")int blogId,HttpSession session)
	{
		UserInfo loggedUser = (UserInfo) session.getAttribute("user");
		String username = loggedUser.getUsername();
		
		BlogLikes blogLikes = new BlogLikes();
		blogLikes.setBlogId(blogId);
		blogLikes.setUsername(username);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson("DisLike value updated");
		String jsonString2 = gson.toJson("Problem updating");
		
		UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
		
		if(userLikedBlogDAO.updateDislikedValue(userLikedBlog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);
		
		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/updateLike_DislikeValue/{blogId}")
	public ResponseEntity<String> updateLike_DislikeValue(@PathVariable("blogId")int blogId,HttpSession session)
	{
		UserInfo loggedUser = (UserInfo) session.getAttribute("user");
		String username = loggedUser.getUsername();
		
		BlogLikes blogLikes = new BlogLikes();
		blogLikes.setBlogId(blogId);
		blogLikes.setUsername(username);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson("DisLike Like values updated");
		String jsonString2 = gson.toJson("Problem updating");
		
		UserLikedBlog userLikedBlog = userLikedBlogDAO.getUserLikedBlog(blogLikes);
		
		if(userLikedBlogDAO.updateLike_DislikeValue(userLikedBlog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);
		
		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
