Overview– Build a toolbox that can help analyze tweets from a server. 

Problem 1: Extracting Tweets from a Server using HTTP Request

In TweetReader.java, Implement List<Tweets> readTweetsFromWeb(URL url). This method will return a list of Tweets.

Our GET url endpoint is: http://kevinstwitterclient2.azurewebsites.net/api/products 

Here is an html view: http://kevinstwitterclient2.azurewebsites.net/Views/Product/TwitterCrud.html

Each Tweet will contain:
		int Id: Id will be a number from 1 to 2^32
		String Name: name only contains a-zA-Z0-9 and _ everything else is invalid 
		String Date: represented in Java parseable format i.e YYYY-MM-DDT00:00:00Z
		String Text: <= 140 characters

Note: We CAN have dirty data. If the data does not match the specs, use either exception handling or string comparison to ignore the data.

Feel free to modify any of the classes, add any additional variables, etc.. Do not modify existing method Headers. We will use this to test your code.

Problem 2: Analyzing Tweets

Now that you’ve extracted the list of tweets, we want to implement common filtering techniques: 

Filter Tweets by UserName using the method writtenBy()
Filter Tweets by TimeSpan using the method inTimespan()
Filter Tweets by keywords using the method containing() 

If the Timespan’s end is before the start, throw an “InvalidTimespan Exception” with the message “End Date before Start Date” 

Also, the dates might likely be formatted incorrectly to where it can not be parsed. Use Exception Handling to prevent your code from failing.

Problem 3: Inferring Social Network

There are some interesting insights we can gather from twitter data. For example, we can infer social networks. 

To Do:

 Find a list of all cliques.
 Find the top k most followed users.

A clique is defined as a set of people that are mutually friends with each other.

