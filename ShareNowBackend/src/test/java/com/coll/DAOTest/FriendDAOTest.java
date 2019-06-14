package com.coll.DAOTest;


import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.DAO.FriendDAO;
import com.coll.Model.Friend;
import com.coll.Model.UserInfo;

public class FriendDAOTest 
{
static FriendDAO friendDAO;
	
	@BeforeClass
	public static void initailize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.coll");
		
		friendDAO = (FriendDAO) context.getBean("friendDAO");
	}

	@Ignore
	@Test
	public void sendFriendRequestTest()
	{
		Friend friend = new Friend();
		friend.setUsername("kohli");
		friend.setFriendusername("Nolan");
		
		assertTrue("Problem sending Friend Request",friendDAO.sendFriendRequest(friend));	
	}
	
	@Ignore
	@Test
	public void approveFriendRequestTest() 
	{
		assertTrue("Problem Approving Friend Request",friendDAO.approveFriendRequest(652));
		
	}
	
	@Ignore
	@Test
	public void rejectFriendRequestTest()
	{
		assertTrue("Problem Approving Friend Request",friendDAO.rejectFriendRequest(652));

	}

	@Ignore
	@Test
	public void deleteFriendTest()
	{
		assertTrue("Problem Approving Friend Request",friendDAO.deleteFriend(654));

	}
	
	@Ignore
	@Test
	public void showFriendsTest() 
	{
		List<Friend> friendsList = friendDAO.showFriends("latha");
		assertTrue("Problem Retriving Friends List",friendsList.size()>0);
		
		for(Friend friend : friendsList)
		{
			System.out.print(friend.getUsername()+"===");
			System.out.println(friend.getStatus()+"===");
			System.out.println(friend.getFriendusername());		 
		}
		
	}

	@Ignore
	@Test
	public void showPendingsentRequestsTest() 
	{
		List<Friend> pendingfriendsRequests = friendDAO.sentPendingFriendRequests("latha");
		assertTrue("Problem Retriving Pending Friend Requests",pendingfriendsRequests.size()>0);
		
		for(Friend friend : pendingfriendsRequests)
		{
			System.out.print(friend.getUsername()+"===");
			System.out.print(friend.getStatus()+"===");
			System.out.println(friend.getFriendusername());	
		}
		
	}
	
	@Ignore
	@Test
	public void showReceivedPendingRequestsTest() 
	{
		List<Friend> pendingfriendsRequests = friendDAO.showReceivedPendingRequests("latha");
		assertTrue("Problem Retriving Pending Friend Requests",pendingfriendsRequests.size()>0);
		
		for(Friend friend : pendingfriendsRequests)
		{
			System.out.print(friend.getUsername()+"===");
			System.out.print(friend.getStatus()+"===");
			System.out.println(friend.getFriendusername());	
		}
		
	}
	
	@Ignore
	@Test
	public void showSuggestedFriends() 
	{
		HashSet<UserInfo> suggestedFriends = friendDAO.showSuggestedFriends("latha");
		
		assertTrue("Problem Retriving Friends List",suggestedFriends.size()>0);
		
		for(UserInfo friend : suggestedFriends)
		{
			System.out.println(friend.getUsername());
		}

	}

}
