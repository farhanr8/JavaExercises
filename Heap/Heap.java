/*
 * @author 	S M Farhanur Rahman
 * @Date	10/08/2019
 * @Description: HW3
 * 1) Generate an array of 20 random positive numbers
 * 2) Make the array into a heap
 * 3) Sort the heap using heap sort
 * 
 */

import java.util.Arrays;
import java.util.Random;

public class Heap {
	
	public static void heapify(int[] array) {
		int idx;
		int par_idx;
		int[] heapArray = new int[array.length+1];
		boolean flag;
		
		/*
		 * Generate a minHeap array that contains the heap size in index 0.
		 * Index 1 till heap size contains the array values according to the 
		 * minHeap value property.
		 */
		
		for(int elem : array) {
			heapArray[heapArray[0]+1] = elem;
			heapArray[0]++;
			idx = heapArray[0];
			flag = true;
			while(idx > 1 && flag == true) {
				par_idx = idx / 2;
				if(heapArray[idx] < heapArray[par_idx]) {
					int temp = heapArray[idx];
					heapArray[idx] = heapArray[par_idx];
					heapArray[par_idx] = temp;
					idx = par_idx;
				}
				else {
					flag = false;
				}
			} // End of while loop
			
		} // End of for loop
		
		/*
		 * Copy the heap array into the original array
		 * excluding the heap size (index 0)
		 */
		
		for(int i = 0; i < array.length; i++) {
			array[i] = heapArray[i+1];
		}
		
	} // End of for heapify()
	
	public static void heapSort(int[] array) {
		
		int temp;
		int size_idx = array.length-1;
		int ROOT = 0;
		boolean flag;
		
		while (size_idx >= 0){
			int idx = 1;
			flag = true;
			
			// Swap root with the last index 
			if(array[ROOT] < array[size_idx]) {
				temp = array[ROOT];
				array[ROOT] = array[size_idx];
				array[size_idx] = temp;
				size_idx--;
				
			}
			else {
				flag = false;
				size_idx--;
			}
		    
			// Percolate down the new root so min heap is maintained
			while((idx*2 <= size_idx) && flag == true) {
				int child_idx;
				int lchild = idx * 2;
				int rchild = lchild + 1;
				
				child_idx = lchild;
				if(rchild < size_idx) {
					child_idx = array[lchild-1]< array[rchild-1] ? lchild : rchild;
				}
				
				if(array[child_idx-1] < array[idx-1]) {
					temp = array[idx-1];
					array[idx-1] = array[child_idx-1];
					array[child_idx-1] = temp;
					idx = child_idx;
				}
				else {
					flag = false;
				}
				
			}	// End inner while loop
			
		} // End outer while loop
		
	} // End of for heapSort()


	public static void main(String[] args) {
		
		/* Hard-coded array */	
		int[] array = new int[]{99, 28, 57, 49, 82, 37, 74, 96, 48, 45, 
								 53, 41, 24, 88, 55, 88, 6, 51, 53, 51};
		
		/* Randomly generated array */
//		int NUM_OF_ELEMENTS = 20; 	// Adjust as needed
//		int RANDOM_RANGE = 100; 	// Adjust as needed
//		int[] array = new int[NUM_OF_ELEMENTS];
//		Random random = new Random();
//		for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
//			int elem = random.nextInt(RANDOM_RANGE);
//			array[i] = elem;
//		}

		System.out.println("Array generated with " + array.length + " nodes:");
		System.out.println(Arrays.toString(array));

		heapify(array);
		System.out.println("\n\nArray converted to heap:");
		System.out.println(Arrays.toString(array));

		heapSort(array);
		System.out.println("\n\nSorted array with heap sort:");
		System.out.println(Arrays.toString(array));

	}

}
