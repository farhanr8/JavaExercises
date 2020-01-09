/*
 * @author 	S M Farhanur Rahman
 * @Date	11/23/2019
 * @Description: HW6 - Create a hash table to implement spell checker.
 * 1) Create a file of 100 lower case words of varying length (max 8 characters).
 * 2) Design a hash table and enter each word.
 * 		- Use the linear probing first. Count the number of collisions and print it.
 * 		- Then use quadratic probing. Count the number of collisions and print it.
 * 		- Start with a table size that is about 53.  
 * 		- The program should automatically determine that table size needs to increase.
 * 		- Print out: increasing table size to <size>
 * 		- Rehash all the old words also and the new words to the new hash table.
 * 		- Print the collisions in each case for the new table size.
 * 3) Once the hash table is created, run it as a spell checker.  
 * 		- You enter a word (interactive), find the word in your hash table. 
 * 		- If not found enter an error message.  
 * 		- Then prompt for next word.  
 * 		- End the session by some control character.
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class SpellChecker {

	public static void main(String[] args) {
		HashTable hashTable = null;
		Scanner sc = new Scanner(System.in);
		
		/*  Choose probing method */
		System.out.println("Please choose hash probing method: linear (l) or quadratic (q)");
		String probingMethod = sc.next();
		do {
			if(probingMethod.equals("l"))
				hashTable = new LinearProbingHT(53);
			else if(probingMethod.equals("q"))
				hashTable = new QuadraticProbingHT(53); 
			else {
				System.out.println("Invalid input! Please try again.");
				probingMethod = sc.next();
			}
		}while(!probingMethod.equals("l") && !probingMethod.equals("q"));
		
		/*  Read words from file and enter into hash table */
		System.out.println("\nInitializing SpellChecker...\n");
		System.out.println("Table Size = 53");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"/Users/farhanr8/eclipse-workspace/CS5343_HW6/src/Words.txt"));
			String line = reader.readLine();
			int i = 0;
			while (line != null) {
				hashTable.insert(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Hashing complete");
		if(probingMethod.equals("l"))
			System.out.println("Total collision with linear probing: " + HashTable.collision);
		else
			System.out.println("Total collision with quadratic probing: " + HashTable.collision);
		
		/*  Scan word input and check if contained in hash table */
		System.out.println("\n*** Running SpellChecker! ***\n");
		System.out.println("Welcome! Do you think you can guess the top 100 most commonly used English words?\n"
							+ "Try typing your guess in all lower case and see if you are correct.\n");
		System.out.println("To exit application, enter (;)");
		System.out.println("Enter a word:");
		String wordIn = sc.next();
		System.out.println();
		while(!wordIn.equals(";")) {
			if(hashTable.contains(wordIn))
				System.out.println("Success: Word found in hash table at index " + hashTable.get(wordIn));
			else
				System.out.println("Incorrect: Word not found in hash table");
			System.out.println("\nTo exit application, enter (;)");
			System.out.println("Enter Word:");
			wordIn = sc.next();
			System.out.println();
		}
		System.out.println("Exiting - Thanks for playing!");

	}

}
