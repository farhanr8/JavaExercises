/**
 * Student name: Farhan Rahman
 * @description:
 * Produce a stanford tagger output
 * https://nlp.stanford.edu/software/tagger.shtml
 */

package assignment1;

import java.util.Scanner;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Problem3 {

	public static void main(String[] args) {
		
		String s;
		
		/* Get input */
		Scanner console = new Scanner(System.in);
		s = console.nextLine();
		
		// Initialize the tagger
		MaxentTagger tagger = new MaxentTagger(
		"taggers/english-bidirectional-distsim.tagger");
		
		// The tagged string
        String tagged = tagger.tagString(s);
 
        // Output the result
        System.out.println(tagged);
        
        console.close();
	}
}

//I was slowly walking to the park with my over enthusiastic dog when he bit me, and I shouted, Ouch!
