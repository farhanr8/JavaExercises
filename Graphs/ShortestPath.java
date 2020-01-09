/*
 * @author 	S M Farhanur Rahman
 * @Date	11/02/2019
 * @Description: HW4
 * 1) Generate a graph with at least 10 vertices and 20 edges.
 * 2) Print out the graph - list of vertices and edges (pair of vertices).
 * 3) Run dijkstra's algorithm.
 * 4) Print the resulting tree.
 * 
 */

import java.util.PriorityQueue; 
	 

public class Graph {
	int[][] graphMatrix;
	int[] dist;
	
	public Graph(int[][] mat) {
		graphMatrix = mat;
		dist = new int[graphMatrix.length];
	}
	
	public void printGraph() {
		boolean edgeExist = false;
		System.out.println("\nList of Nodes: ");
		for(int i = 0; i < graphMatrix.length; i++) {
			if(i == graphMatrix.length-1) 
				System.out.print(i + "\n");
			else
				System.out.print(i + ", ");
		}
		
		System.out.println("\nList of Edges: ");
		for(int i = 0; i < graphMatrix.length; i++) {
			for(int j = i + 1; j < graphMatrix.length; j++) {
				if(graphMatrix[i][j] != 0) {
					System.out.print("(" + i + "," + j + ") ");
					edgeExist = true;
				}
				if(j == graphMatrix.length-1 && edgeExist) {
					System.out.print("\n");
					edgeExist = false;
				}
					
			}
		}
	}
	
	public void shortestPath(int source) {
		boolean[] nodesDone = new boolean[graphMatrix.length]; 
		boolean allNodesDone = false;
		int[][] shortestPathTree = new int[graphMatrix.length][graphMatrix.length];
		PriorityQueue<Integer> minDistQueue = new PriorityQueue<Integer>();
		
		// Initialize distances
		for (int i = 0; i < graphMatrix.length; i++) { 
			nodesDone[i] = false;
			if(i == source)
				dist[i] = 0;
			else
				dist[i] = Integer.MAX_VALUE; 
        } 
		
		minDistQueue.add(dist[source]);
		
		// Shortest path from source to all nodes
		while(!allNodesDone) {
			
			// Check heap based priority queue for min distance node not yet done
			while(!minDistQueue.isEmpty()) {
				int node1 = 0;
				int d = 	minDistQueue.poll();
				
				// From the matrix get the node with the minimum distance
				for(int i = 0; i < graphMatrix.length;i++) {
					if(nodesDone[i] == false && dist[i] == d) {
						node1 = i;
						break;
					}
				}
				nodesDone[node1] = true;
				
				// Update dist value of the adjacent nodes
				for(int node2 = 0; node2 < graphMatrix.length; node2++) {
					
					if(graphMatrix[node1][node2] != 0 
							&& dist[node1] != Integer.MAX_VALUE 
							&& !nodesDone[node2]) {
						
						int cost = graphMatrix[node1][node2];
						int distanceThroughNode1 = dist[node1] + cost;
						if(distanceThroughNode1 < dist[node2]) {
							dist[node2] = distanceThroughNode1;
							minDistQueue.add(dist[node2]);
							
							// Update the shortest path tree with the new min distance
							shortestPathTree[node1][node2] = distanceThroughNode1;
							for(int j = 0; j < graphMatrix.length; j++) {
								if(j != node1 && shortestPathTree[j][node2] != 0) {
									shortestPathTree[j][node2] = 0;
								}
							}

						}
	
					}
					
				}	// End for loop
			} // End inner while loop
			
			// Update node status
			for (int i = 0; i < graphMatrix.length; i++) { 
				if(nodesDone[i] == true)
					allNodesDone = true;		
				else {
					allNodesDone = false;
					break;
				}
	        } 
			
		} // End outer while
		
		// Update shortest path tree to be symmetric matrix and print result
		for(int i = 0; i < graphMatrix.length; i++) {
			for(int j = 0; j < graphMatrix.length; j++) {
				if(shortestPathTree[i][j] != 0) {
					shortestPathTree[j][i] = shortestPathTree[i][j];
				}
			}
		}
		Graph tree = new Graph(shortestPathTree); 
		tree.printGraph();
		
	}

	
	public static void main(String[] args) {
		
		/* 
		 * Generated graph: 
		 * - Adjacency matrix
		 * - 10 nodes and 20 edges
		 */
		int graph[][] = new int[][] { { 0, 	4, 	0, 	0, 	0, 	0, 	0, 	10, 0, 	5 }, 
            				          { 4, 	0, 	3, 	0, 	0, 	0, 	0, 	0, 	9, 	2 }, 
						              { 0, 	3, 	0, 	1, 	0, 	0, 	3, 	0, 	12,	0 }, 
						              { 0, 	0, 	1, 	0, 	15, 2, 	8, 	0, 	5, 	0 }, 
						              { 0, 	0, 	0, 	15,	0, 	9,	1,	0,	0, 	0 }, 
						              { 0, 	0, 	0, 	2, 	9,	0, 	20, 13, 0, 	0 }, 
						              { 0, 	0, 	3, 	8, 	1, 	20, 0, 	11, 0, 	0 }, 
						              { 10, 0, 	0, 	0, 	0, 	13, 11, 0, 	0, 	3 }, 
						              { 0, 	9, 	12, 5, 	0, 	0, 	0, 	0, 	0, 	1 }, 
						              { 5, 	2, 	0, 	0, 	0, 	0, 	0, 	3, 	1, 	0 }}; 
            
		Graph g = new Graph(graph); 
		g.printGraph();
		g.shortestPath(0);	// Start Dijkstra's at Node 0
		
	}

}
