package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class SocialNetworkTest {

	/*@Test
	void testFindKMostFollower() {
		//fail("Not yet implemented");
		testingInformation foo = new testingInformation();
		SocialNetwork test = new SocialNetwork();
		
		List<Tweets> tweets = foo.listOfTweets();
		System.out.println(test.findKMostFollower(tweets, 1));
		
	}*/

	@Test
	void testFindCliques() {
		testingInformation foo = new testingInformation();
		SocialNetwork test = new SocialNetwork();
		
		List<Tweets> tweets = foo.listOfTweets();
		System.out.println(test.findCliques(tweets));
		//fail("Not yet implemented");
	}

}
