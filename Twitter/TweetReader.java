package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.net.ssl.HttpsURLConnection;
import com.fasterxml.jackson.databind.*;

/**
 * TweetReader contains method used to return tweets from method
 * Do not change the method header
 */
public class TweetReader {

    /**
     * Find tweets written by a particular user.
     *
     * @param url
     *            url used to query a GET Request from the server
     * @return return list of tweets from the server
     *
     */
    public static List<Tweets> readTweetsFromWeb(String url) throws Exception
    {
        List<Tweets> tweetList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        
        int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print response list
		System.out.println(response.toString());
		
		/* Creating my arraylist */
		
		Tweets[] listclass = mapper.readValue(response.toString(), Tweets[].class);
		
		for(Tweets var : listclass) {
			int num = var.getId();
			
			String nm = var.getName();
			String txt = var.getText();
			String date = var.getDate();
			//int len = txt.length();
			
			if((nm != null) && (txt != null) && (date != null)) {
				
				// Check ID
				if (!(num == (int)num)) {continue;}				
				
				// Check Name
				if(!nm.matches("[a-zA-Z0-9_]+")) {continue;}		
			
				// Check date/time
				SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				try {
			        parser.parse(date);
			    } 
				
				catch (ParseException e) {
			        continue;
			    }
				
				if (txt.length() > 140) {continue;}
			}
			else {continue;}
			
			tweetList.add(var);
		}
        return tweetList;
    }
}
