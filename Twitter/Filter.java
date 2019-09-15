package assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweets> writtenBy(List<Tweets> tweets, String username) {
        List<Tweets> filteredList = new ArrayList<Tweets>();
        
        for(Tweets var : tweets) {
						
			String nm = var.getName();
			nm = nm.toLowerCase();
			username = username.toLowerCase();
						
			if(nm.equals(username)) {
				filteredList.add(var);
			}
		}
        
        return filteredList;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweets> inTimespan(List<Tweets> tweets, Timespan timespan) {
        List<Tweets> filteredList = new ArrayList<Tweets>();
        
        for(Tweets var : tweets) {
			
			String time = var.getDate();
			Instant parsedTime = Instant.parse(time);
			
			// Inclusive
			if(parsedTime.equals(timespan.getStart())){
				filteredList.add(var);
				continue;
			}
			if(parsedTime.equals(timespan.getEnd())){
				filteredList.add(var);
				continue;
			}
			
			int greater = parsedTime.compareTo(timespan.getStart());
			int smaller = parsedTime.compareTo(timespan.getEnd());			
						
			if((greater > 0) && (smaller < 0)) {
				filteredList.add(var);
			}
		}
        
        return filteredList;
    }

    /**
     * Find tweets that contain certain words.
     *
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets.
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when
     *         represented as a sequence of nonempty words bounded by space characters
     *         and the ends of the string) includes *at least one* of the words
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweets> containing(List<Tweets> tweets, List<String> words) {
        List<Tweets> filteredList = new ArrayList<Tweets>();
        int len = words.size();
        int count;
        
        // Check *every word* in the list is present in a tweet and then display that tweet <-- WRONG
        for(Tweets var : tweets) {
			count = 0;
			String txt = var.getText();
			txt = txt.toLowerCase();
			
			for(String str : words) {
				str = str.toLowerCase();
				if(txt.contains(str)) {
					count++;
				}
			}
						
			if(count == len) {
				filteredList.add(var);
			}
		}
        
        return filteredList;
    }
}