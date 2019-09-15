
package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GraphPoet {
	
	public Map<String, Vertex> vertices = new HashMap<String, Vertex>();

    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */

    public GraphPoet(File corpus) throws IOException {

        /* Read in the File and place into graph */
    	
    	String word;
    	String nextword;
    	Vertex node;
    	
    	Scanner scan1 = new Scanner(corpus);
    	Scanner scan2 = new Scanner(corpus);
    	
    	// scan2 is a pointer to get the next-word
    	nextword = scan2.next();
    	nextword = nextword.toLowerCase();
    	
    	while(scan1.hasNext()) {
    		word = scan1.next();
    		word = word.toLowerCase();
    		
    		// Check for new word. If new create Vertex object
    		if(!vertices.containsKey(word)) {
    			node = new Vertex(word);
    			vertices.put(word, node);
    			
    			// Get the adjacent of the new word and add it to its edges.
    			if(scan2.hasNext()) {
    				nextword = scan2.next();
    				nextword = nextword.toLowerCase();
    				node.addAdj(nextword);
    			}   			
    		}
    		
    		// If it's not a new word
    		else {
    			
    			// Get the adjacent of the old word
    			if(scan2.hasNext()) {
    				nextword = scan2.next();
    				nextword = nextword.toLowerCase();
    			}
    			
    			// Check if adjacent is the same. 
    			// Get vertex from the string "word"
    			node = vertices.get(word);
    			
    			// If it is the same, add weight else add new adj.
    			if(node.checkAdj(nextword)) {
    				node.addWeight(nextword);
    			}
    			else {
    				node.addAdj(nextword);
    			}
    		}
    		 
    	}
    	
    	scan1.close();
    	scan2.close();
    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {

        /* Read in input and use graph to complete poem */
 
    	boolean insert;
    	 
        StringBuffer poem = new StringBuffer("");
        String ret;
        String word;
        String word_p;
        String nextword;
        String nextword_p;
        
        BFSVertex search;
        
        Vertex max = new Vertex();
        Set<Vertex> sharedAdj = new HashSet<Vertex>();
        
        Scanner scan1 = new Scanner(input);
        Scanner scan2 = new Scanner(input);
        nextword_p = scan2.next();
    	nextword_p = nextword_p.toLowerCase();
        nextword = nextword_p.replaceAll("\\W", "");
    	
        while(scan1.hasNext()) {
        	word_p = scan1.next();
        	word_p = word_p.toLowerCase();
        	word = word_p.replaceAll("\\W", "");
        	
        	// Add word scanned to the poem. Then, determine whether there's a bridge to add or not
        	poem.append(word_p + " ");
        	
        	// Check if the word from input is in corpus
        	if(vertices.containsKey(word)) {
            	
        		// Check if the word's neighbor is also in corpus
        		if (scan2.hasNext()) {
        			nextword_p = scan2.next();
            		nextword_p = nextword_p.toLowerCase();
            		nextword = nextword_p.replaceAll("\\W", "");
        		}
        		
        		else {
        			nextword = null;
        		}
            	
            	if(vertices.containsKey(nextword)) {
            		
            		/* Use BFS to check if corpus had 1 word in between the two input words (i.e. there's bridge) */
            		
            		//Create a BFS vertex to start looking
            		search = new BFSVertex(vertices.get(word), 0);
            		
            		// Get boolean if bridge is true or not        		
            		insert = search.BFS(nextword, vertices.get(word), vertices);
            		if(insert) {
            			
            			// Loop through the edges of my current word
            			// to find vertices that have the next-word as adjacent.
            			// Build a set of those vertices to figure out who has the max weight
            			for(Map.Entry<String, Integer> neighbor : vertices.get(word).edges.entrySet()) {
            				String str = neighbor.getKey();
            				Vertex var = vertices.get(str);
            				if(var.edges.containsKey(nextword)){
            					sharedAdj.add(var);
            				}
            			}
            			
            			// Find the vertex that has the next-word with the max weight
            			max = max.maxWeight(sharedAdj, vertices.get(word), nextword);
            			
            			// Add the bridge word to poem
            			word = max.name;
            			poem.append(word + " ");
            			

                    	// Clear set for the next word
                    	sharedAdj.clear();
            		}
            	}
            }
        	
        	// Word not in corpus so move on to next word
        	else {
        		if (scan2.hasNext()) {
        			nextword_p = scan2.next();
            		nextword_p = nextword_p.toLowerCase();
            		nextword = nextword_p.replaceAll("\\W", "");
        		}
        	}
        	
        }
       
        // Convert poem to string and return
        scan1.close();
        scan2.close();
        ret = poem.toString();
        return ret;
    }
    
}
