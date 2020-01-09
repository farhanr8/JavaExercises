package hw2;

public class BST {
	
	static class Node {
		int val;
		Node left, right;
		
		public Node(int v) {
			this.val = v;
			this.left = null;
			this.right = null;
		}
		
	}
	
	Node root;
	
	public BST() {
		this.root = null;
	}
	
	/* ********** Insert ********** */

	public void Insert(int val) {
		System.out.println("Inserting Node " + val);
		root = InsertRec(root, val);
	}
	
	Node InsertRec(Node root, int val) { 
		  
		// Base case: No tree/subtree at the node
        if (root == null) { 
            root = new Node(val); 
            return root; 
        } 
  
        // Create left subtree
        if (val < root.val) 
            root.left = InsertRec(root.left, val); 
        
        // Create right subtree
        else if (val > root.val) 
            root.right = InsertRec(root.right, val); 
        
        // Return root of the tree/subtree
        return root; 
    } 
	
	/* ********** Traversal ********** */

    public void PreOrder()  { 
    	PreOrderRec(root); 
 	} 
    void PreOrderRec(Node root) { 
    	// Root -> Left -> Right
    	if (root != null) { 
			System.out.println(root.val); 
			InOrderRec(root.left); 
		    InOrderRec(root.right); 
	    } 
     } 
	
	public void InOrder()  { 
       InOrderRec(root); 
	} 
	  
    void InOrderRec(Node root) { 
    	// Left -> Root -> Right
        if (root != null) { 
        	InOrderRec(root.left); 
            System.out.println(root.val); 
            InOrderRec(root.right); 
        } 
    } 
    
    
     public void PostOrder()  { 
     	PostOrderRec(root); 
     } 
  	  
     void PostOrderRec(Node root) { 
    	// Left -> Right -> Root
    	 if (root != null) { 
    		 PostOrderRec(root.left); 
    		 PostOrderRec(root.right);
    		 System.out.println(root.val);
    	 } 
      } 
    
	/* ********** Delete ********** */
    
    public void Delete(int val) {
		System.out.println("\nDeleting Node " + val);
		root = DeleteRec(root, val);
	}
	
	Node DeleteRec(Node root, int val) { 
		
		// Base case 1: There's no tree
		if(root == null) {
			return null;
		}
		
		// Base case 2: Found the node to delete
        if(root.val == val) {
        	
        	// Node only has a right child, so replace it with that
        	if(root.left == null) {
        		return root.right;
        	}
        	
        	// Node only has a left child, so replace it with that
        	if(root.right == null) {
        		return root.left;
        	}
        	
        	// Node has two children, so 
        	// 1) Get predecessor to replace the node
        	// 2) Delete the predecessor (replace the predecessor with its predecessor)
        	Node temp = root.left;
        	int pred = temp.val;
        	while(temp.right != null) {
        		pred = temp.right.val;
        		temp = temp.right;
        	}
        	root.val = pred;
        	root.left = DeleteRec(root.left, root.val);

        }
        
        // Search for node in the left subtree
        else if (val < root.val) {
            root.left = DeleteRec(root.left, val); 
        }
        
        // Search for node in the right subtree
        else if (val > root.val) {
            root.right = DeleteRec(root.right, val);
        }
        
        // Return the new root of the tree/subtree
        return root;
    } 

}
