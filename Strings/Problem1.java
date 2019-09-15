/**
 * @author Farhan Rahman
 * @description Find max product of a string from the console
 */

package assignment1;

import java.util.Scanner;

public class Problem1 {

	public static void  main(String[] args) {
		
		/* Initializations */
		int n;
		String s;
		int max_prod = 0, ptr = 0;
		int prod = 1; 
		
		// Get input
		Scanner console = new Scanner(System.in);
		n = console.nextInt();
		s = console.next();
		
		/* Main Process */
		
		// Length of the string to work with
		int i = s.length()-n;		
		
		while (i != -1) {		
			
			// Multiply the adjacent numbers of the pointer n times
			for (int j = 0; j<n; j++) {
				prod = prod * Character.getNumericValue(s.charAt(ptr+j));
			}
			
			// Update the max product if current product is higher
			if (prod > max_prod) {
				max_prod = prod;
			}
			
			i--;		// Reduce length
			ptr++;		// Point to next digit
			prod=1;		// Reset product
		}
		
		System.out.println(max_prod);
		console.close();
	}
}
