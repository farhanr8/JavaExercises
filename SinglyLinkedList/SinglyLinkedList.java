package hw1;

public class SinglyLinkedList {
	
	class Node{    
        int data;    
        Node next;    
            
        public Node(int data) {    
            this.data = data;    
            this.next = null;    
        }    
    }
	
	public Node head;
	public Node tail;
	
	public SinglyLinkedList() {
		head = null;
		tail = null;
	}
	
	public void Create(int data) {    
        Node newNode = new Node(data);   
        if(head == null) {    
            head = newNode;    
            tail = newNode;    
        }    
        else {    
            tail.next = newNode;    
            tail = newNode;    
        }    
    }

	// Traverse list while printing the node data
	public void Traverse() {
		Node nodePtr = head;
		while(nodePtr != null) {
			if(nodePtr == head) {
				System.out.print("[" + nodePtr.data + "]");
			}
			else {
				System.out.print("->[" + nodePtr.data + "]");
			}
			nodePtr = nodePtr.next;
		}
		
	}
	
	// Sort list in ascending order
	public void SelectionSort() {
		
		// Initializations
		Node smallestPlace = head;
		Node beforeSmallestPlace = head;
		Node beforeSmallestNode = head;
		Node nodePtr = smallestPlace;
		
		int min;
		boolean DO_SWAP;
		
		System.out.println("\n\nRunning Selection Sort:");
		
		while(smallestPlace != tail) {
			
			Traverse();
			System.out.println("\n");
			
			min = smallestPlace.data;
			DO_SWAP = false;
			
			// Traverse list and identify node with min value
			while(nodePtr.next != null) {
				if(nodePtr.next.data < min) {
					beforeSmallestNode = nodePtr;
					min = nodePtr.next.data;
					DO_SWAP = true;
				}
				nodePtr = nodePtr.next;
			}
			
			// Swap the min value to the correct position
			if(DO_SWAP) {
				
				Node smallestNode = beforeSmallestNode.next;
				
				// Handles tail swap
				if(smallestNode == tail) {
					tail = smallestPlace;
				}
				
				// Handles head swap
				if(smallestPlace == head) {
					
					// Adjacent node handler
					if(smallestPlace.next == smallestNode) {	
						smallestPlace.next = smallestNode.next;
						smallestNode.next = smallestPlace;
						head = smallestNode;
						smallestPlace = head;
					}
					// Non-adjacent node handler
					else {
						beforeSmallestNode.next = smallestPlace;
						Node headNext = smallestPlace.next;
						smallestPlace.next = smallestNode.next;
						smallestNode.next = headNext;
						head = smallestNode;
						smallestPlace = head;
					}
					
				
				}
				
				// Handles regular node swap
				else {
					// Adjacent node handler
					if(smallestPlace.next == smallestNode) {
						beforeSmallestNode.next = smallestNode.next;
						smallestNode.next = beforeSmallestPlace.next;
						beforeSmallestPlace.next = smallestNode;
						smallestPlace = smallestNode;
					}
					// Non-adjacent node handler
					else {
						beforeSmallestNode.next = smallestPlace;
						Node nodeNext = smallestPlace.next;
						smallestPlace.next = smallestNode.next;
						beforeSmallestPlace.next = smallestNode;
						smallestNode.next = nodeNext;
						smallestPlace = smallestNode;
					}
					
				}
			}
			
			// Update pointers
			if(smallestPlace != head) {
				beforeSmallestPlace = beforeSmallestPlace.next;
			}
			else {
				beforeSmallestPlace = head;
			}
			smallestPlace = smallestPlace.next;
			nodePtr = smallestPlace;
		}
		
		
	}
	
}
