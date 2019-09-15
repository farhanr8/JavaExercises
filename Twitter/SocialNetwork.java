package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Iterator;

/**
 * Social Network consists of methods that filter users matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Get K most followed Users.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @param k
     *            integer of most popular followers to return
     * @return the set of usernames who are most mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getName()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like ethomaz@utexas.edu does NOT
     *         contain a mention of the username.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static List<String> findKMostFollower(List<Tweets> tweets, int k) {
        List<String> mostFollowers = new ArrayList<>();
        Map<String, Integer> users = new HashMap<String, Integer>(); 
        
        String name;
        
        // Build Map for the users with name and number of occurrences 
        for(Tweets var : tweets) {
        	
        	String txt = var.getText();
			txt = txt.toLowerCase();
			
			// Create a string of the username when @ is seen
			for (int i = 0; i < txt.length(); i++) {
			    if (txt.charAt(i) == '@') {
			    	
			    	StringBuffer username = new StringBuffer("");
			    	
			    	//MODIFIED SUBSTRING, ADDED IF i==0
			    	//String check1 = txt.substring(i-1,i); 
			    	
			    	//@ is in index 0
			    	if (i == 0) {
			    		i++;
			    				    		
			    		try {
			    			while(txt.substring(i,i+1).matches("[a-zA-Z0-9_]+")) {
				    			username.append(txt.charAt(i));
				    			i++;
				    		}
			    		}
			    		
			    		catch(StringIndexOutOfBoundsException exception) {
			    			i++;
			    		}
			    		
			    		i--;
			    		
			    		// Put username in map
					    name = username.toString();
					    if(users.containsKey(name)) {
					    	users.put(name, users.get(name) + 1);
					    }
					    else {
					    	if(!name.equals("")) {
					    		users.put(name, 1);
					    	}
					    }
			    	}
			    	
			    	// @ is preceded by non desired characters
			    	else if(txt.substring(i-1,i).matches("[a-zA-Z0-9_]+")) {
			    		continue;
			    	}
			    	
			    	// @ is anywhere else but first and is valid
			    	else {
			    		i++;
			    		
			    		try {
			    			while(txt.substring(i,i+1).matches("[a-zA-Z0-9_]+")) {
				    			username.append(txt.charAt(i));
				    			i++;
				    		}
			    		}
			    		
			    		catch(StringIndexOutOfBoundsException exception) {
			    			i++;
			    		}
			    		
			    		i--; 
			    		
			    		// Put username in map
					    name = username.toString();
					    if(users.containsKey(name)) {
					    	users.put(name, users.get(name) + 1);
					    }
					    else {
					    	if(!name.equals("")) {
					    		users.put(name, 1);
					    	}
					    }
			    	}
			    }
			    
			}
        }
        
        // Build List with users that have been mentioned more than k times
        Iterator<Entry<String, Integer>> entries = users.entrySet().iterator();
        while (entries.hasNext()) {
        	Map.Entry<String, Integer> entry = entries.next();
            if(entry.getValue() >= k) {
            	mostFollowers.add(entry.getKey());
            }
            
            /*
             
             while (k>0){
             
         
             k--;
             }
             
            */
            
        	System.out.println("Name = " + entry.getKey() + ", Mentions = " + entry.getValue());
        }
        
        return mostFollowers;
        
    }
    

    /**
     * Find all cliques in the social network.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     *
     * @return list of set of all cliques in the graph
     */
    public static List<Set<String>> findCliques(List<Tweets> tweets) {
        List<Set<String>> result = new ArrayList<Set<String>>(); 
        Set<String> checkedUsers = new HashSet<String>();
        
        String orig_name;
        String following_name;
        String follower_name;
        
        for(Tweets var : tweets) {
            
            Set<String> Orig_following = new HashSet<String>();
            
            // Get the original tweets name
			orig_name = var.getName();
			if(checkedUsers.contains(orig_name)) {
				continue;
			}
			
			// Clique for the orig_name
			Set<String> Cliques = new HashSet<String>();
			
			Cliques.add(orig_name);
			checkedUsers.add(orig_name);
			
			//All tweets of the orig person
			Filter filter = new Filter();
	        List<Tweets> filteredUser1 = filter.writtenBy(tweets,orig_name);
	        
	        // For all their tweets check who they have @-ed and create a list
	        for(Tweets var1 : filteredUser1) {
	        	String txt = var1.getText();
				txt = txt.toLowerCase();
				
				
				
				// Create a string of the username when @ is seen -- MODIFIED
				for (int i = 0; i < txt.length(); i++) {
				    if (txt.charAt(i) == '@') {
				    	
				    	StringBuffer username1 = new StringBuffer("");	// First @-ed username
				    	
				    	//@ is in index 0
				    	if (i == 0) {
				    		i++;
				    				    		
				    		try {
				    			while(txt.substring(i,i+1).matches("[a-zA-Z0-9_]+")) {
					    			username1.append(txt.charAt(i));
					    			i++;
					    		}
				    		}
				    		
				    		catch(StringIndexOutOfBoundsException exception) {
				    			i++;
				    		}
				    		
				    		i--;
				    		
				    		following_name = username1.toString();
				    		Orig_following.add(following_name);
				    	
				    	}
				    	
				    	// @ is preceded by non desired characters
				    	else if(txt.substring(i-1,i).matches("[a-zA-Z0-9_]+")) {
				    		continue;
				    	}
				    	
				    	// @ is anywhere else but first and is valid
				    	else {
				    		i++;
				    		
				    		try {
				    			while(txt.substring(i,i+1).matches("[a-zA-Z0-9_]+")) {
					    			username1.append(txt.charAt(i));
					    			i++;
					    		}
				    		}
				    		
				    		catch(StringIndexOutOfBoundsException exception) {
				    			i++;
				    		}
				    		
				    		i--; 
				    		
				    		following_name = username1.toString();
				    		Orig_following.add(following_name);
				    		
				    	}
				    }
				    
				}
		         
	        }
	        
	        // Check the @users tweets to see if orig ever got mentioned
	        for(String var2 : Orig_following) {
	        	List<Tweets> filteredUser2 = filter.writtenBy(tweets,var2);
	        	
	        	for(Tweets var3 : filteredUser2) {
		        	String txt1 = var3.getText();
					txt1 = txt1.toLowerCase();
					
					
					//  Create a string of tweeted @username - by someone orig is following -- MODIFIED
					for (int i = 0; i < txt1.length(); i++) {
					    if (txt1.charAt(i) == '@') {
					    	
					    	StringBuffer username2 = new StringBuffer("");	// Second @-ed username
					     
					    	
					    	//@ is in index 0
					    	if (i == 0) {
					    		i++;
					    				    		
					    		try {
					    			while(txt1.substring(i,i+1).matches("[a-zA-Z0-9_]+")) {
						    			username2.append(txt1.charAt(i));
						    			i++;
						    		}
					    		}
					    		
					    		catch(StringIndexOutOfBoundsException exception) {
					    			i++;
					    		}
					    		
					    		i--;
					    		
					    		// Check if this username is same as orig name - they follow each other
					    		follower_name = username2.toString();
							    if(follower_name.equals(orig_name)) {
							    	Cliques.add(follower_name);
							    }
					    	
					    	}
					    	
					    	// @ is preceded by non desired characters
					    	else if(txt1.substring(i-1,i).matches("[a-zA-Z0-9_]+")) {
					    		continue;
					    	}
					    	
					    	// @ is anywhere else but first and is valid
					    	else {
					    		i++;
					    		
					    		try {
					    			while(txt1.substring(i,i+1).matches("[a-zA-Z0-9_]+")) {
						    			username2.append(txt1.charAt(i));
						    			i++;
						    		}
					    		}
					    		
					    		catch(StringIndexOutOfBoundsException exception) {
					    			i++;
					    		}
					    		
					    		i--; 
					    		
					    		// Check if this username is same as orig name - they follow each other
					    		follower_name = username2.toString();
							    if(follower_name.equals(orig_name)) {
							    	Cliques.add(var2);
					    		
							    }
					    	}
					    
					    }
					}
					
	        	}
	        }
	        
	        if(!result.contains(Cliques)) {
	        	result.add(Cliques);
	        }
        }
        
        return result;
    }
}


