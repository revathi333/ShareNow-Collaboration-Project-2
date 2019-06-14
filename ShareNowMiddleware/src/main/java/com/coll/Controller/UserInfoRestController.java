package com.coll.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.UserInfoDAO;
import com.coll.Model.UserInfo;

@RestController
public class UserInfoRestController
{
	@Autowired
	UserInfoDAO userInfoDAO;

	@PostMapping("/addUser")
	public ResponseEntity<UserInfo> registerUser(@RequestBody UserInfo user)
	{
		user.setRole("ROLE_USER");
		user.setStatus("A");
		user.setIsOnline("Y");
		if(userInfoDAO.registerUser(user))
		   return new ResponseEntity<UserInfo>(user,HttpStatus.OK);
		else
		   return new ResponseEntity<UserInfo>(user,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@GetMapping("/getUser/{username}")
	public ResponseEntity<UserInfo> getUser(@PathVariable("username")String username)
	{
		UserInfo user = userInfoDAO.getUser(username);
		
		if(user!=null)
		   return new ResponseEntity<UserInfo>(user,HttpStatus.OK);
		else
		   return new ResponseEntity<UserInfo>(user,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user)
	{
		if(userInfoDAO.updateUser(user))
		   return new ResponseEntity<UserInfo>(user,HttpStatus.OK);
		else
		   return new ResponseEntity<UserInfo>(user,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@PostMapping("/checkUser")
	public ResponseEntity<UserInfo> checkUser(@RequestBody UserInfo user,HttpSession session)
	{
		UserInfo user1 = userInfoDAO.checkUser(user);
		session.setAttribute("user", user1);
						
		if(user1!=null)
		   return new ResponseEntity<UserInfo>(user1,HttpStatus.OK);
		else
		   return new ResponseEntity<UserInfo>(user1,HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
