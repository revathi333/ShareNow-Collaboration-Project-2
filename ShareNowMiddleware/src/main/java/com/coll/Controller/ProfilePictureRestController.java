package com.coll.Controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.coll.DAO.ProfilePictureDAO;
import com.coll.Model.ProfilePicture;
import com.coll.Model.UserInfo;

@RestController
public class ProfilePictureRestController 
{
	@Autowired
	ProfilePictureDAO profilePictureDAO;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestBody CommonsMultipartFile image,HttpSession session) 
	{
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		if(user==null)
		{
			return new ResponseEntity<String>("Not Logged In",HttpStatus.NOT_FOUND);
		}
		else
		{
			ProfilePicture picture = new ProfilePicture();
			picture.setUsername(user.getUsername());
			picture.setImage(image.getBytes());
			
			profilePictureDAO.saveProfilePicture(picture);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}				
	}	
		
	@RequestMapping(value="/getImage/{username}",method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@PathVariable("username")String username,HttpSession session)
	{
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		if(user==null)
		{
			return null;
		}		
		else
		{
			ProfilePicture picture = profilePictureDAO.getProfilePicture(user.getUsername());		

			if(picture!=null)
			    return picture.getImage();
			
			else 
				return null;
		}
	}

}
