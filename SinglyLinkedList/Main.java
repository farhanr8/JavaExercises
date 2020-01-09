/*
 * @author 	S M Farhanur Rahman
 * @Date	09/13/2019
 * @Description: HW1
 * 1) Create a single linked list of at least 15 nodes (unsorted) 
 * 2) Traverse the list
 * 3) Sort the list in ascending order with selection sort
 * 4) Traverse the list 
 * 
 */
package hw1;

import java.util.Random;

public class Main {
     
	public static void main(String[] args) {
		
		int NUM_OF_NODES = 18; 	// Adjust as needed
		int RANDOM_RANGE = 50;	// Adjust as needed
		
		SinglyLinkedList myList = new SinglyLinkedList();
		Random random = new Random();
		
		// Add nodes to the mylist
		for(int i = 0; i < NUM_OF_NODES; i++) {
			int nodeData = random.nextInt(RANDOM_RANGE);
			myList.Create(nodeData);
		}
		
		System.out.println("List Generated with " + NUM_OF_NODES + " nodes:");
		myList.Traverse();
		
		myList.SelectionSort();
		System.out.println("\n\nSorted List:");
		myList.Traverse();
		
	}

}
