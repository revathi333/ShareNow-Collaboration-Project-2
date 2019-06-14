package com.coll.DAO;

import com.coll.Model.ProfilePicture;

public interface ProfilePictureDAO 
{
	public void saveProfilePicture(ProfilePicture profilePicture); 
	public ProfilePicture getProfilePicture(String username); 
}
