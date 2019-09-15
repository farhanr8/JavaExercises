package assignment2;

import java.util.Scanner;


public class Driver {

public static void  main(String[] args) {
		
		boolean playAgain = true;
		boolean testMode = false;
		String rematch;
		Scanner console = new Scanner(System.in);
		
		if (args[0] == "1") {
			testMode = true;
		}
		else {
			testMode = false;
		} 
		
		Game myGame = new Game(testMode);
		 
		while(playAgain) {
			boolean done = myGame.runGame(console);
			
			if(done) {
				System.out.println("Are you ready for another game? (Y/N): ");
				rematch = console.next();
				if(rematch.equals("N")) {
					playAgain = false;
				}
				else if (rematch.equals("Y")) {
					playAgain = true;
				}
			}	
			else {
				playAgain = false;
			}
		}
		
		console.close();	
	}

}
