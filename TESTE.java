package com.tutorialspoint.lucene;
import java.util.ArrayList;
import java.util.List;

import twitter4j.PagableResponseList;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;
import twitter4j.User;
public class TESTE {
	
	
	
	 public static int recup(int id) throws TwitterException, InterruptedException
	 {
		Twitter twitter = new TwitterFactory().getInstance();
		 TwitterResponse response = twitter.showUser(id);
		    RateLimitStatus status = response.getRateLimitStatus();
		
		    int user = twitter.showUser(id).getStatusesCount();///nbr de tweets d'un user
		      
			  // int abonnement = twitter.showUser(id).getFriendsCount();
			   if(status.getRemaining() == 0) {
				   try {
			            Thread.sleep(status.getSecondsUntilReset() * 1000);
			        }catch(InterruptedException e) {
			             }  }
			        	 return (user );}
			  
	public static int recupf(int id) throws TwitterException, InterruptedException
	 {
		Twitter twitter = new TwitterFactory().getInstance();
		 TwitterResponse response = twitter.showUser(id);
		    RateLimitStatus status = response.getRateLimitStatus();
		      int followers = twitter.showUser(id).getFollowersCount();
			   if(status.getRemaining() == 0) {
				   try {
			            Thread.sleep(status.getSecondsUntilReset() * 1000);
			        }
			        catch(InterruptedException e) {
			            // ...
			        } }
			return followers;
		}

}
