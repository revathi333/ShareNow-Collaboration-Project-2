package com.coll.DAOTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.DAO.UserInfoDAO;
import com.coll.Model.UserInfo;


public class UserInfoDAOTest 
{
static UserInfoDAO userInfoDAO;
	
	@BeforeClass
	public static void initailize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.coll");
		
		userInfoDAO = (UserInfoDAO) context.getBean("userInfoDAO");
	}
	
	
	@Test
	public void registerUser()
	{
		UserInfo userr = new UserInfo();
		userr.setUsername("sadsadda");
		userr.setEmailId("sdsarada.M@gmail.com");
		userr.setIsOnline("Y");
		userr.setMemberName("Sarada Devi");
		userr.setPassword("99999");
		userr.setRole("ROLE_USER");
		userr.setStatus("A");
		
		assertTrue("Adding Issue",userInfoDAO.registerUser(userr));

	}
	
	@Ignore
	@Test
	public void updateUser()
	{
		UserInfo user = userInfoDAO.getUser("geetha");
		user.setMemberName("Geetha Govind");
		
		assertTrue("Updating Issue",userInfoDAO.updateUser(user));
		
	}

	@Ignore
	@Test
	public void checkUser()
	{
		UserInfo user = new UserInfo();
		user.setUsername("areeb");
		user.setPassword("great123");	
		
		assertNotNull("User Not Found",userInfoDAO.checkUser(user));

		System.out.println(userInfoDAO.checkUser(user).getMemberName());
	}

}
