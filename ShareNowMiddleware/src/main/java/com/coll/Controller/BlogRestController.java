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
import com.coll.DAO.BlogDAO;
import com.coll.Model.Blog;
import com.coll.Model.UserInfo;
import com.google.gson.Gson;

@RestController
public class BlogRestController 
{
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@GetMapping("/showAllBlogs")
	public ResponseEntity<List<Blog>> showAllBlogs()
	{
		List<Blog> listBlogs = (List<Blog>)blogDAO.listBlogs();
				
		if(listBlogs.size()>0)
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/showBlogsforAdmin")
	public ResponseEntity<List<Blog>> showBlogsforAdmin()
	{
		List<Blog> listBlogs = (List<Blog>)blogDAO.listBlogsforAdmin();
				
		if(listBlogs.size()>0)
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@GetMapping("/showBlogsByUser/{username}")
	public ResponseEntity<List<Blog>> showBlogsByUser(@PathVariable("username")String username)
	{
		List<Blog> listBlogsbyUser = (List<Blog>)blogDAO.listBlogsbyUser(username);
				
		if(listBlogsbyUser.size()>0)
			return new ResponseEntity<List<Blog>>(listBlogsbyUser,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<Blog>>(listBlogsbyUser,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog,HttpSession session)
	{
	    Gson gson = new Gson();
		String jsonString = gson.toJson("Blog Added");
        String jsonString2 = gson.toJson("Problem Adding");

		blog.setCreateDate(new java.util.Date());
		blog.setDislikes(0);
		blog.setLikes(0);
		blog.setStatus("NA");
			
		UserInfo loggedUser = (UserInfo) session.getAttribute("user");
		String username = loggedUser.getUsername();

		blog.setUsername(username);
		
		if(blogDAO.addBlog(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);
	

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
		    
	}


	@GetMapping("/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId")int blogId)
	{	
		    Gson gson = new Gson();
			String jsonString = gson.toJson("Blog Deleted");
	        String jsonString2 = gson.toJson("Problem Deleting");
	        
		Blog blog = blogDAO.getBlog(blogId);
		
		//Blog related comments should be deleted first to avoid Foreign Key constraint violation when blog gets deleted
		blogCommentDAO.deleteBlogIdComments(blogId); 
		
		if(blogDAO.deleteBlog(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
   @GetMapping("/incLikesBlog/{blogId}")
	public ResponseEntity<String> incLikesBlog(@PathVariable("blogId")int blogId)
	{	
	     Gson gson = new Gson();
	     //To convert Java Object even "String" to JSON object 
	     String jsonString = gson.toJson("Blog Likes incremented");
	     String jsonString2 = gson.toJson("Problem incrementing");

         
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.incLikes(blog))
     		return new ResponseEntity<String>(jsonString,HttpStatus.OK);
		

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@GetMapping("/incDisLikesBlog/{blogId}")
	public ResponseEntity<String> incDisLikesBlog(@PathVariable("blogId")int blogId)
	{	
	     Gson gson = new Gson();
		String jsonString = gson.toJson("Blog DisLikes incremented");
		String jsonString2 = gson.toJson("Problem incrementing");
		
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.incDisLikes(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@GetMapping("/decLikesBlog/{blogId}")
	public ResponseEntity<String> decLikesBlog(@PathVariable("blogId")int blogId)
	{	
	     Gson gson = new Gson();
		String jsonString = gson.toJson("Blog Likes decremented");
		String jsonString2 = gson.toJson("Problem decrementing");
		
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.decLikes(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	@GetMapping("/decDisLikesBlog/{blogId}")
	public ResponseEntity<String> decDisLikesBlog(@PathVariable("blogId")int blogId)
	{	
	     Gson gson = new Gson();
		String jsonString = gson.toJson("Blog DisLikes decremented");
		String jsonString2 = gson.toJson("Problem decrementing");
		
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.decDisLikes(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);

	}
		

	@GetMapping("/decDisLikesincLikes/{blogId}")
	public ResponseEntity<String> decDisLikesincLikesBlog(@PathVariable("blogId")int blogId)
	{	
	     Gson gson = new Gson();
		String jsonString = gson.toJson("Blog DisLikes decremented and Likes Incremented");
		String jsonString2 = gson.toJson("Problem decrementing");
		
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.decDisLikesincLikes(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	@GetMapping("/decLikesincDislikes/{blogId}")
	public ResponseEntity<String> decLikesincDislikesBlog(@PathVariable("blogId")int blogId)
	{	
	     Gson gson = new Gson();
		String jsonString = gson.toJson("Blog Likes decremented and DisLikes Incremented");
		String jsonString2 = gson.toJson("Problem decrementing");
		
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.decLikesincDislikes(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	

	@GetMapping("/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlogBlog(@PathVariable("blogId")int blogId)
	{	
	     Gson gson = new Gson();
		String jsonString = gson.toJson("Blog Approved");
		String jsonString2 = gson.toJson("Problem Approving");
		
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.approveBlog(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlogBlog(@PathVariable("blogId")int blogId)
	{	
		Gson gson = new Gson();
	    String jsonString = gson.toJson("Blog Rejected");
	    String jsonString2 = gson.toJson("Problem Rejecting");
	    
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.rejectBlog(blog))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId")int blogId)
	{	
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blog!=null)
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);

		else	
		    return new ResponseEntity<Blog>(blog,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping("/updateBlog")
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog)
	{
		
		if(blogDAO.updateBlog(blog))
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);

		else	
		    return new ResponseEntity<Blog>(blog,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
