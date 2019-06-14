package com.coll.DAO;

import com.coll.Model.UserInfo;

public interface UserInfoDAO 
{
	public boolean registerUser(UserInfo user);
	public boolean updateUser(UserInfo user);
	public UserInfo getUser(String username);
	public UserInfo checkUser(UserInfo user);
}
