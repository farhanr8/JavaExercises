package assignment2;

import java.util.ArrayList;

public class Pegs {
	
	private int b_num = 0; 
	private int w_num = 0;
	private char letter;
	/*
	public Pegs() {
	        V
Code:		B B B A
Guess:		B C A A
		          ^
	}*/
	
	public int bp_Num(String guess, String code, ArrayList<Integer> wp_index) {
		char letter;
		b_num = 0;
		wp_index.clear();
		
		for(int index = 0 ;index < guess.length(); index++) {
			
			//Gets letter from user input and checks it with the secret code
			letter = guess.charAt(index);
			
			//Black pegs condition
			if(letter == code.charAt(index)) {
				b_num++;
			}
			else {
				wp_index.add(index);
			}
		}
		return b_num;
		
	}
	
	public int wp_Num(String guess, String code, ArrayList<Integer> wp_index, ArrayList<Integer> checked_index) {
		w_num = 0;
		checked_index.clear();
		
		for(int guess_ptr = 0 ;guess_ptr < guess.length(); guess_ptr++) {
			
			int code_ptr = 0;
			
			if(wp_index.contains(guess_ptr)){
				letter = guess.charAt(guess_ptr);
				
				for(code_ptr = 0 ;code_ptr < guess.length(); code_ptr++){   // Get code ptr
					if((!checked_index.contains(code_ptr))&&  				// not in the checked list 
					   (wp_index.contains(code_ptr))&&						// in the potential wp list
					   (!(code_ptr == guess_ptr))){							// not pointing to the same index
					
						if (letter == code.charAt(code_ptr)) {
							w_num++;
							checked_index.add(code_ptr);
							break;
						}
					}
					
				}
			}
			
		}
		return w_num;
	}
}
