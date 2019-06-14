package com.coll.DAO;

import java.util.HashSet;
import java.util.List;

import com.coll.Model.Friend;
import com.coll.Model.UserInfo;

public interface FriendDAO
{
	public boolean sendFriendRequest(Friend friend);
	public boolean approveFriendRequest(int friendId);
	public boolean rejectFriendRequest(int friendId);
	
	public boolean deleteFriend(int friendId);
	
	public List<Friend> showFriends(String username);
	public List<Friend> showReceivedPendingRequests(String username);
	public List<Friend> sentPendingFriendRequests(String username);
	public HashSet<UserInfo> showSuggestedFriends(String username);

}
