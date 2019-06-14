package com.coll.DAO;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.Model.Friend;
import com.coll.Model.UserInfo;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean sendFriendRequest(Friend friend)
	{
		try
		{
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);	
			return true;		 	
		}
		catch(Exception e)
		{
			return false;
	    }
	}

	@Override
	public boolean rejectFriendRequest(int friendId)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Friend friend  = session.get(Friend.class, friendId);
			session.close();
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		}
		catch(Exception e)
		{
			return false;
	    }	
	}

	@Override
	public boolean approveFriendRequest(int friendId) 
	{
		try
		{
			Session session = sessionFactory.openSession();
			Friend friend  = session.get(Friend.class, friendId);
			session.close();
			friend.setStatus("A");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		 	
		}
		catch(Exception e)
		{
			return false;
	    }	
	}

	@Override
	public boolean deleteFriend(int friendId) 
	{
		try
		{
			Session session = sessionFactory.openSession();
			Friend friend  = session.get(Friend.class, friendId);
			session.close();
			sessionFactory.getCurrentSession().delete(friend);
			return true; 	
		}
		catch(Exception e)
		{
			return false;
	    }
	}

	@Override
	public List<Friend> showFriends(String username) 
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Friend where (username=:uname or friendusername=:uname) and status='A' ");
			query.setParameter("uname", username);			
			List<Friend> friendsList = (List<Friend>) query.list();          			
			session.close();			
			return friendsList; 
			
		}		
		catch(Exception e)
		{
			return null;
	    }
	}

	@Override  //To see friend requests sent by me to other users
	public List<Friend> sentPendingFriendRequests(String username)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Friend where username=:uname and status='P' ");
			query.setParameter("uname", username);
			List<Friend> pendingfriendsRequests = (List<Friend>) query.list();
			session.close();
			return pendingfriendsRequests; 	
		}
		catch(Exception e)
		{
			return null;
	    }
	}

	@Override  //To see friend requests sent to me by other users
	public List<Friend> showReceivedPendingRequests(String username)
	{
		try
		{
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("from Friend where friendusername=:uname and status='P' ");
			query.setParameter("uname", username);
			List<Friend> showReceivedPendingRequests = (List<Friend>) query.list();
			session.close();
			return showReceivedPendingRequests; 	
		}
		catch(Exception e)
		{
			return null;
	    }
	}

	@Override      //Friend suggestions from common friends
	public HashSet<UserInfo> showSuggestedFriends(String username)
	{
		try
		{
			Session session = sessionFactory.openSession();
			
			//with logged_username as username get-->{friendusernames}--> as username=={friendusernames}
			Query query1 = session.createQuery("from Friend where username in(Select friendusername from Friend where username='"+username+"' and status='A') and status='A'and friendusername not in(select friendusername from Friend where username='"+username+"' and status='A')and friendusername not in(select username from Friend where friendusername='"+username+"' and status='A')");			
			List<Friend> list1 = (List<Friend>) query1.list();

			//with logged_username as friendusername get-->{username}--> as username=={friendusernames}
			Query query2 = session.createQuery("from Friend where username in(Select username from Friend where friendusername='"+username+"' and status='A') and friendusername!='"+username+"'and status='A' and friendusername not in(select friendusername from Friend where username='"+username+"' and status='A') and friendusername not in(select username from Friend where friendusername='"+username+"' and status='A')");
			List<Friend> list2 = (List<Friend>) query2.list();

			
			list1.addAll(list2);  //take friendusername

		
			//with logged_username as username get-->{friendusernames}--> as friendusername=={usernames}
			Query query3 = session.createQuery("from Friend where friendusername in(Select friendusername from Friend where username='"+username+"' and status='A') and status='A'and username not in(select friendusername from Friend where username='"+username+"' and status='A') and username!='"+username+"' and status='A' and username not in(select username from Friend where friendusername='"+username+"' and status='A')");
			List<Friend> list3 = (List<Friend>) query3.list();

			//with logged_username as friendusername get-->{username}--> as friendusername=={usernames}
			Query query4 = session.createQuery("from Friend where friendusername in(Select username from Friend where friendusername='"+username+"' and status='A') and status='A' and username not in(select friendusername from Friend where username='"+username+"' and status='A') and username not in(select username from Friend where friendusername='"+username+"' and status='A')");
			List<Friend> list4 = (List<Friend>) query4.list();

			
			list3.addAll(list4);  //take username

			
			//To retrieve pending requests of loggedUser
			Query query_P = session.createQuery("from Friend where(friendusername='"+username+"' or username='"+username+"') and status='P'");
			List<Friend> pendingStatus = (List<Friend>) query_P.list();			
						
			HashSet<String> names = new HashSet<String>();
			
			for(Friend name : pendingStatus)
			{
				names.add(name.getFriendusername());
				names.add(name.getUsername());
			}
			names.remove(username);
			
		    
			//suggestedFriendsList is combined with distinct Friends			
			HashSet<UserInfo> suggestedFriendsList = new HashSet<UserInfo>();
			
			for(Friend x : list1)
			{
				String chk = x.getFriendusername();
				boolean b = names.contains(chk);

				if(b==false)
				{
					UserInfo user = session.get(UserInfo.class,chk);
					suggestedFriendsList.add(user);
				}
			}

			for(Friend y : list3)
			{
				String chk = y.getUsername();
				boolean b = names.contains(chk);

				if(b==false)
				{
					UserInfo user = session.get(UserInfo.class,chk);
					suggestedFriendsList.add(user);
				}
			}

			return suggestedFriendsList; 				
		}
		
		catch(Exception e)
		{
			return null;
	    }
	}

}


