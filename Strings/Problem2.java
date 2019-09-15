/**
 * @author Farhan Rahman
 * @description Assign values to string and print $1.00 words
 */

package assignment1;

import java.util.Scanner;

public class Problem2 {

	public static void main(String[] args) {
		
		/* Initializations */
		String s;
		char ch;
		int length, ptr, letter_value;
		int word_value = 0;
		int word_length = 0;
		
		// Get input 
		Scanner console = new Scanner(System.in);
		s = console.nextLine();
		
		length = s.length();		// Length of string
		ptr = 0;					// String pointer
		
				
		/* Main process */
		
		while(length != 0) {				// Duration of the program
			ch = s.charAt(ptr);				// Get character being pointed
			
			if(Character.isLetter(ch)) {	// Start of word
				while(ch != ' ') {			// Wait until the end of the word
					
					if(Character.isLetter(ch)) {
						
						// Get the letter value
						ch = Character.toUpperCase(ch);			
						letter_value = Character.getNumericValue(ch);
						letter_value = letter_value - 9;						
						
						// Update variables
						word_value = word_value + letter_value; 		
						word_length++;
						
						ptr++;
						ch = s.charAt(ptr);		//ERROR HERE. EXPECTING PERIOD OR END OF SENTENCE!
						length--;
					}
					
					// If there's special char in word skip to updating variables
					else {								
						if(length != 1) {				
							ptr++;
							ch = s.charAt(ptr);
							length--;
							word_length++;
						}
						// If special char is end of input
						else {							
							break;
						}
					}
					
				}							
			}
			
			//Print $1.00 word
			if (word_value == 100) {					
				for( ; word_length != 0; word_length--) {
					System.out.print(s.charAt(ptr-word_length));
				}
				System.out.println();
			}
			
			// Update variables
			ptr++;
			length--;
			word_value=0;
			word_length=0;
		}
		
		console.close();
	}
}
