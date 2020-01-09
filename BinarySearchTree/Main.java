/*
 * @author 	S M Farhanur Rahman
 * @Date	09/19/2019
 * @Description: HW2
 * 1) Create a BST using the following values , inserted in the order given. 
 * 2) Do an inorder traversal to print the values.
 * 3) Delete 100, use predecessor.
 * 4) Do an  inorder traversal again.
 * 
 */

package hw2;

public class Main {

	public static void main(String[] args) {
		
		int[] node_vals = {100, 50, 200, 150, 300, 25, 75, 12, 37, 125, 175, 250, 320, 
							67, 87, 94, 89,92,88};
		BST mytree = new BST();
		
		for(int i = 0; i < node_vals.length; i++) {
			mytree.Insert(node_vals[i]);
		}

		System.out.println("\nPrinting BST In-Order after creation with given values:");
		mytree.InOrder();
		
		int NODE_TO_DELETE = 100; 	// ADJUST AS NEEDED
		
		mytree.Delete(NODE_TO_DELETE);
		
		System.out.println("\nPrinting BST In-Order after deletion of node:");
		mytree.InOrder();

	}

}
