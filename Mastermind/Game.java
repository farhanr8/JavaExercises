package assignment2;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	private boolean testMode;
	private String[] hist_guesses = new String[GameConfiguration.guessNumber];
	private String[] hist_replies = new String[GameConfiguration.guessNumber];
	
	public Game(boolean val) {
		testMode = val;
	}
	
	public boolean runGame(Scanner console) {
		
		int guessNum = GameConfiguration.guessNumber; 
		int b_num; 
		int w_num;
		int colorValid = 0;
		int lengthValid = 0;
		int row = 0;
		int validColor;
		ArrayList<Integer> wp_index = new ArrayList<Integer>();
		ArrayList<Integer> checked_index = new ArrayList<Integer>();
		Pegs gamePeg = new Pegs();
		
		char letter;
		String start;
		String code;
		String guess;
		
		System.out.println("Welcome to Mastermind. Here are the rules.\r\n\n" + 
				"This is a text version of the classic board game Mastermind.\r\n\n" + 
				"The computer will think of a secret code. The code consists of 4\r\n" + 
				"colored pegs. The pegs MUST be one of six colors: blue, green,\r\n" + 
				"orange, purple, red, or yellow. A color may appear more than once in\r\n" + 
				"the code. You try to guess what colored pegs are in the code and\r\n" + 
				"what order they are in. After you make a valid guess the result\r\n" + 
				"(feedback) will be displayed.\r\n\n" + 
				"The result consists of a black peg for each peg you have guessed\r\n" + 
				"exactly correct (color and position) in your guess. For each peg in\r\n" + 
				"the guess that is the correct color, but is out of position, you get\r\n" + 
				"a white peg. For each peg, which is fully incorrect, you get no\r\n" + 
				"feedback.\r\n\n" + 
				"Only the first letter of the color is displayed. B for Blue, R for\r\n" + 
				"Red, and so forth. When entering guesses you only need to enter the\r\n" + 
				"first character of each color as a capital letter.\r\n\n" + 
				"You have " + guessNum + " guesses to figure out the secret code or you lose the\r\n" + 
				"game. Are you ready to play? (Y/N):"
				);
		
		start = console.next();
		
		// Don't really need it
		while((!start.equals("Y")) && (! start.equals("N"))) {
			System.out.println("Invalid input! Please try again.\r\n"+
								"Are you ready to play? (Y/N): "
							);
			start = console.next();
		}
		
		/*	GAME-PLAY	*/
		
		if(start.equals("Y")) {
			
			// Check if test-mode is on or off
			if(this.testMode == true) {
				code = SecretCodeGenerator.getInstance().getNewSecretCode();
				System.out.println("\nGenerating secret code ...(for this example the secret code is " 
						+ code + ")\n");
			}
			else {
				code = SecretCodeGenerator.getInstance().getNewSecretCode();
				System.out.println("\nGenerating secret code ...\n");
			}
			
			/* Check number of attempts at guessing. 
			 * Main loop that checks guesses and give feedback. */
			for(;guessNum != 0; guessNum--) {
				//b_num = 0;
				//w_num = 0;
				colorValid = 0;
				lengthValid = 0;
				wp_index.clear();
				checked_index.clear();
				
				
				if(guessNum > 1) {
					System.out.println("\nYou have " + guessNum + " guesses left.\r\n"+
							"What is your next guess?\r\n" + 
							"Type in the characters for your guess and press enter.\r\n" + 
							"Enter guess:"
							);
				}
				else {
					System.out.println("\nYou have " + guessNum + " guess left.\r\n"+
							"What is your next guess?\r\n" + 
							"Type in the characters for your guess and press enter.\r\n" + 
							"Enter guess:"
							);
				}
				
				// Get input
				guess = console.next();
				if(checkHistory(guess, row)) {
					guess = console.next();
				}
				
				// Input Validity
				while((colorValid == 0)||(lengthValid == 0)) {
					
					// Check for valid input length
					if(guess.length() != GameConfiguration.pegNumber) {
						lengthValid = 0;
						invalidGuess();
						guess = console.next();
						if(checkHistory(guess, row)) {
							guess = console.next();
						}
							
					}
					else {
						lengthValid = 1;
					}
					
					if (lengthValid == 1) {
						
						for(int length = 0 ;length < guess.length(); length++) {
							
							//Gets letter from user input and check the colors array to see if valid
							letter = guess.charAt(length);
							validColor = Arrays.asList(GameConfiguration.colors).indexOf(Character.toString(letter));
							
							// Color not found, meaning incorrect input
							if(validColor < 0) {
								length = 0;
								colorValid = 0;
								
								invalidGuess();
								guess = console.next();
								if(checkHistory(guess, row)) {
									guess = console.next();
								}
							}
							else {
								colorValid = 1;
							}
						}
						
					}
					
					/*
					// Check letters for valid colors
					for(int length = 0 ;length < guess.length(); length++) {
						
						//Gets letter from user input and check the colors array to see if valid
						letter = guess.charAt(length);
						validColor = Arrays.asList(GameConfiguration.colors).indexOf(Character.toString(letter));
						
						// Color not found, meaning incorrect input
						if(validColor < 0) {
							length = 0;
							colorValid = 0;
							
							invalidGuess();
							guess = console.next();
							if(checkHistory(guess, row)) {
								guess = console.next();
							}
						}
						else {
							colorValid = 1;
						}
					} */
				}
				
				
				// Input is valid
				if((colorValid == 1)&&(lengthValid == 1)) {
					
					// Get pegs
					b_num = gamePeg.bp_Num(guess, code, wp_index);
					w_num = gamePeg.wp_Num(guess, code, wp_index, checked_index);
					
					// Store guesses
					hist_guesses[row] = guess;
					hist_replies[row] = b_num + "B_" + w_num + "W";
					row++;
					
					// Correct guess - VICTORY
					if(guess.equals(code)) {
						System.out.println(guess + " -> Result: " + b_num + " black pegs - YOU WIN !!\n");
						return true;
					}
					
					// Incorrect guesses. Shows feedback
					else { 
						if((b_num > 1) && (w_num > 1)) {
							System.out.println(guess + " -> Result: " + b_num + " black pegs and " + w_num + " white pegs\n");
						}
						else if((b_num <= 1) && (w_num > 1)) {
							System.out.println(guess + " -> Result: " + b_num + " black peg and " + w_num + " white pegs\n");
						}
						else if((b_num > 1) && (w_num <= 1)) {
							System.out.println(guess + " -> Result: " + b_num + " black pegs and " + w_num + " white peg\n");
						}
						else if((b_num <= 1) && (w_num <= 1)) {
							System.out.println(guess + " -> Result: " + b_num + " black peg and " + w_num + " white peg\n");
						}
					}
					
				}
			}
			
			// Out of "guesses" 
			System.out.println("\nSorry, you are out of guesses. You lose, boo-hoo.\n");
			return true;
		}
		
		else {
			return false;
		}
		
		/*	END runGame	*/
	}
	
	
	
	public void invalidGuess(){
		System.out.println("INVALID GUESS\n"+
				"What is your next guess?\r\n" + 
				"Type in the characters for your guess and press enter.\r\n" + 
				"Enter guess:"
				);
	}
	
	public boolean checkHistory(String guess, int table_rows) {
		int hist_Row;
		//Check for HISTORY 
		if(guess.equals("HISTORY")) {
			for(hist_Row = 0; hist_Row < table_rows; hist_Row++) {
				System.out.println(hist_guesses[hist_Row] + "\t\t" + hist_replies[hist_Row] + "\n");
			}
			
			System.out.println("What is your next guess?\r\n" + 
					"Type in the characters for your guess and press enter.\r\n" + 
					"Enter guess:"
					);
			
			return true;
		}
		else {return false;}
		
	}
		
}
