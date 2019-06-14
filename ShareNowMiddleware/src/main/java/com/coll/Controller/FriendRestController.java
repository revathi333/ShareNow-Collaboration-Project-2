package com.coll.Controller;

import java.util.HashSet;
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

import com.coll.DAO.FriendDAO;
import com.coll.Model.Friend;
import com.coll.Model.UserInfo;
import com.google.gson.Gson;

@RestController
public class FriendRestController 
{
	@Autowired
	FriendDAO friendDAO;
	
	@GetMapping("/showFriends/{username}")
	public ResponseEntity<List<Friend>> showFriends(@PathVariable("username")String username)
	{
		List<Friend> showFriends = (List<Friend>)friendDAO.showFriends(username);
				
		if(showFriends.size()>0)
			return new ResponseEntity<List<Friend>>(showFriends,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<Friend>>(showFriends,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/sentPendingFriendRequests/{username}")
	public ResponseEntity<List<Friend>> showPendingFriendRequests(@PathVariable("username")String username)
	{
		List<Friend> sentPendingFriendRequests = (List<Friend>)friendDAO.sentPendingFriendRequests(username);
		
		if(sentPendingFriendRequests.size()>0)
			return new ResponseEntity<List<Friend>>(sentPendingFriendRequests,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<Friend>>(sentPendingFriendRequests,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/showReceivedPendingRequests/{username}")
	public ResponseEntity<List<Friend>> showReceivedPendingRequests(@PathVariable("username")String username)
	{
		List<Friend> showReceivedPendingRequests = (List<Friend>)friendDAO.showReceivedPendingRequests(username);
		
		if(showReceivedPendingRequests.size()>0)
			return new ResponseEntity<List<Friend>>(showReceivedPendingRequests,HttpStatus.OK);

		else	
		    return new ResponseEntity<List<Friend>>(showReceivedPendingRequests,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/showSuggestedFriends")
	public ResponseEntity<HashSet<UserInfo>> showSuggestedFriends(HttpSession session)
	{
		UserInfo loggedUser = (UserInfo) session.getAttribute("user");
		String username = loggedUser.getUsername();
		
		HashSet<UserInfo> suggestedFriends = (HashSet<UserInfo>)friendDAO.showSuggestedFriends(username);
		
		if(suggestedFriends.size()>0)
			return new ResponseEntity<HashSet<UserInfo>>(suggestedFriends,HttpStatus.OK);

		else	
		    return new ResponseEntity<HashSet<UserInfo>>(suggestedFriends,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/sendFriendRequest")
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		Gson gson = new Gson();
		String jsonString = gson.toJson("Request sent");
        String jsonString2 = gson.toJson("Request not sent");
        
		if(friendDAO.sendFriendRequest(friend))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/approveFriendRequest/{friendId}")
	public ResponseEntity<String> approveFriendRequest(@PathVariable("friendId")int friendId)
	{
		Gson gson = new Gson();
		String jsonString = gson.toJson("Request Approved");
        String jsonString2 = gson.toJson("Problem Approving");
        
		if(friendDAO.approveFriendRequest(friendId))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/rejectFriendRequest/{friendId}")
	public ResponseEntity<String> rejectFriendRequest(@PathVariable("friendId")int friendId)
	{		
		Gson gson = new Gson();
		String jsonString = gson.toJson("Request Rejected");
        String jsonString2 = gson.toJson("Problem Rejecting");
        
		if(friendDAO.rejectFriendRequest(friendId))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/deleteFriend/{friendId}")
	public ResponseEntity<String> deleteFriend(@PathVariable("friendId")int friendId)
	{	
		Gson gson = new Gson();
		String jsonString = gson.toJson("Friend Deleted");
        String jsonString2 = gson.toJson("Problem Deleting");
        
		if(friendDAO.deleteFriend(friendId))
			return new ResponseEntity<String>(jsonString,HttpStatus.OK);

		else	
		    return new ResponseEntity<String>(jsonString2,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
