package com.coll.DAO;

import com.coll.Model.BlogLikes;
import com.coll.Model.UserLikedBlog;

public interface UserLikedBlogDAO 
{	
	public UserLikedBlog getUserLikedBlog(BlogLikes id);
	
	public boolean updateLikeValue(UserLikedBlog userLikedBlog);
	
	public boolean updateDislikedValue(UserLikedBlog userLikedBlog);
	
	public boolean updateLike_DislikeValue(UserLikedBlog userLikedBlog);

}
