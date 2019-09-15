package assignment4;

import java.util.ArrayList;
import java.util.List;

public class testingInformation {

	//summary of tests
	//Everyone who turned anything in started with a 32
	//Everyone who passed at least one test received full credit for TweetReader - equivalent of 8 tests, so you essentially start at a 64.
	
	//Filter Tests
	//Containing - 4 tests
		//for containing all the words tested were the first or last words of the tweet and were space deliminated
		
	//InTime - 4 tests
		//for InTime all the ranges were distinct and did not start or end at the same time as a tweet
	//WrittenBy - 2 tests
		//We tested if you picked up tweets written by a single user
	
	//SocialNetwork Tests
	
	//Follower - 4 tests
	//Clique - 3 tests
		//for clique we were fine if you listed single people as cliques or did not
		//in general the only edge case tested was a group of people who did not form a clique
	//for follower and clique we accepted usernames with or without @
	
	//the following tweets were used for all the tests
	//if you think that it is just formatting/outputting please be sure to let us know
	
	//There is a comment with every grade that summarizes your grade and where you may have missed points
	//If you think that there is an error with your grade run a few tests on your own with the following tweets to be sure
	
	
	
	public List<Tweets> listOfTweets() {
		List<Tweets> tweets = new ArrayList<Tweets>();
		
		Tweets t1 = new Tweets();
		t1.setDate("1989-03-23T06:04:00Z");
		t1.setId(1);
		t1.setName("kevin");
		t1.setText("How Strictly should we grade this assignment @shrikar @ben @Mr_Dr_Thomaz ?");
		tweets.add(t1);
		
	Tweets t2 = new Tweets();
		t2.setDate("2014-03-23T12:46:00Z");
		t2.setId(14);
		t2.setName("ben");
		t2.setText("What assignment ? @kevin");
		tweets.add(t2);
		
	Tweets t3 = new Tweets();
		t3.setDate("2015-01-10T04:00:00Z");
		t3.setId(34);
		t3.setName("shrikar");
		t3.setText("Was this the assignment with the part of speech thing? @kevin");
		tweets.add(t3);
		
	Tweets t4 = new Tweets();
		t4.setDate("2016-01-01T00:00:00Z");
		t4.setId(57);
		t4.setName("ben");
		t4.setText("@shrikar That was the third assignment I think");
		tweets.add(t4);
		/*
	Tweets t5 = new Tweets();
		t5.setDate("2017-01-01T00:00:00Z");
		t5.setId(68);
		t5.setName("Kevin");
		t5.setText("I quit @Edison");
		tweets.add(t5);
		
	Tweets t6 = new Tweets();
		t6.setDate("2017-03-01T00:00:00Z");
		t6.setId(70);
		t6.setName("Shrikar");
		t6.setText("What should the next assignment be? @Mr_Dr_Thomaz");
		tweets.add(t6);
		
	Tweets t7 = new Tweets();
		t7.setDate("2017-03-02T00:00:00Z");
		t7.setId(102);
		t7.setName("Shrikar");
		t7.setText("We should make it really hard");
		tweets.add(t7);
		
	Tweets t8 = new Tweets();
		t8.setDate("2017-03-04T00:00:00Z");
		t8.setId(136);
		t8.setName("Ben");
		t8.setText("But that means it`s hard to grade @shrikar");
		tweets.add(t7);
		
	Tweets t9 = new Tweets();
		t9.setDate("2017-03-05T00:00:00Z");
		t9.setId(512);
		t9.setName("Kevin");
		t9.setText("We could do something easy like rewriting Facebook in assembly! @shrikar @ben @mr_Dr_Thomaz");
		tweets.add(t9);
		
	Tweets t10 = new Tweets();
		t10.setDate("2017-03-06T00:00:00Z");
		t10.setId(1024);
		t10.setName("Mr_Dr_Thomaz");
		t10.setText("That sounds good @kevin");
		tweets.add(t10);
		
	Tweets t11 = new Tweets();
		t11.setDate("2017-03-07T00:00:00Z");
		t11.setId(2048);
		t11.setName("Shrikar");
		t11.setText("Oooh! We should do it in Rust! @kevin @ben @Mr_Dr_Thomaz");
		tweets.add(t11);
	Tweets t12 = new Tweets();
		t12.setDate("2017-03-08T00:00:00Z");
		t12.setId(4096);
		t12.setName("genericStudentName");
		t12.setText("Isn`t this a course based on Java @shrikar ??");
		tweets.add(t12);
	Tweets t13 = new Tweets();
		t13.setDate("2017-03-09T00:00:00Z");
		t13.setId(8192);
		t13.setName("Ben");
		t13.setText("How are you even seeing this?? @genericStudentName");
		tweets.add(t13);
	Tweets t14 = new Tweets();
		t14.setDate("2017-04-01T00:00:00Z");
		t14.setId(9999);
		t14.setName("Edison");
		t14.setText("Why do I have two names? @Mr_Dr_Thomaz");
		tweets.add(t14);
	Tweets t15 = new Tweets();
		t15.setDate("2017-04-02T00:00:00Z");
		t15.setId(101010);
		t15.setName("Ben");
		t15.setText("Who knows I do too @ben2" );
		tweets.add(t15);
	Tweets t16 = new Tweets();
		t16.setDate("2017-04-03T00:00:00Z");
		t16.setId(101011);
		t16.setName("Kevin");
		t16.setText("Oh I think @shrikar just got lazy");
		tweets.add(t16);
	Tweets t17 = new Tweets();
		t17.setDate("2017-04-04T00:00:00Z");
		t17.setId(123456);
		t17.setName("gregFenves");
		t17.setText("THE EYES OF TEXAS ARE UPON YOU @shrikar");
		tweets.add(t17);
	Tweets t18 = new Tweets();
		t18.setDate("2017-04-04T01:00:00Z");
		t18.setId(234567);
		t18.setName("Shrikar");
		t18.setText("confusedkar @kevin @ben ");
		tweets.add(t18);
	Tweets t19 = new Tweets();
		t19.setDate("2017-05-01T00:00:00Z");
		t19.setId(2738255);
		t19.setName("genericStudent");
		t19.setText("Is it bad if I haven`t started yet @kevin @ben @shrikar");
		tweets.add(t19);
	Tweets t20 = new Tweets();
		t20.setDate("2017-06-01T00:00:00Z");
		t20.setId(8615309);
		t20.setName("Kevin");
		t20.setText("Yes but you can probably still finish @genericStudent");
		tweets.add(t20);
	Tweets t21 = new Tweets();
		t21.setDate("2017-08-01T00:00:00Z");
		t21.setId(8615309);
		t21.setName("Ben2");
		t21.setText("I`m the real ben");
		tweets.add(t21);
	Tweets t22 = new Tweets();
		t22.setDate("2017-09-01T00:00:00Z");
		t22.setId(8615309);
		t22.setName("Shrikar");
		t22.setText("@genericStudent chill it was only due like a 2 days ago");
		tweets.add(t22);
	Tweets t23 = new Tweets();
		t23.setDate("2017-11-23T11:11:11Z");
		t23.setId(8615309);
		t23.setName("gregFenves");
		t23.setText("@genericStudent THE EYES OF TEXAS ARE UPON YOU ");
		tweets.add(t23);
		*/
		return tweets;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
