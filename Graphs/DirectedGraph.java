
/*
 * @author 	S M Farhanur Rahman
 * @Date	11/14/2019
 * @Description: HW5
 * 1) Create TWO directed graphs that have at least 10 nodes and 15 edges.
 * 	  Graph 1: Does not have a cycle
 * 	  Graph 2: Has a cycle
 * 2) Implement and run the two graphs on DFS topological ordering algorithm.
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DirectedGraph {
	int TotalNodes;
	ArrayList<LinkedList<Integer>> adj;

	public DirectedGraph(int nodes) {
		TotalNodes = nodes;
		adj = new ArrayList<LinkedList<Integer>>();
		for (int i = 0; i < nodes; i++) {
			adj.add(new LinkedList<Integer>());
		}
	}

	public void addEdge(int v, int w) {
		adj.get(v).add(w);
	}

	public void printGraph() {
		System.out.println("\nList of Nodes: ");
		for (int i = 0; i < adj.size(); i++) {
			if (i == adj.size() - 1)
				System.out.print(i + "\n");
			else
				System.out.print(i + ", ");
		}

		System.out.println("\nList of Edges: ");
		boolean newLine = false;
		for (int i = 0; i < adj.size(); i++) {
			Iterator<Integer> it = adj.get(i).iterator();
			while (it.hasNext()) {
				int nextNode = it.next();
				System.out.print("(" + i + "," + nextNode + ") ");
				newLine = true;
			}
			if(newLine)
				System.out.print("\n");
			newLine = false;

		}
	}

	public static boolean topologicalDFS(DirectedGraph G, int curNode, boolean visited[], Stack<Integer> stack) {

		visited[curNode] = true;
		
		Iterator<Integer> it = G.adj.get(curNode).iterator();
		while (it.hasNext()) {
			int nextNode = it.next();
			if (!visited[nextNode]) {
				boolean cycle = topologicalDFS(G, nextNode, visited, stack);
				if(cycle) {
					stack.push(curNode);
					return true;
				}	
			}
			
			else if(!stack.contains(nextNode)) {
				System.out.println("\nEXITING: Cycle found on Node "+curNode+" to "+nextNode);
				return true;
			}
		}

		stack.push(curNode);
		return false;
//		System.out.println("---STACK---");
//		System.out.println(Arrays.toString(stack.toArray()));
//		System.out.println("-----------");


	}

	public static void topologicalDFS(DirectedGraph G) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean visited[] = new boolean[G.TotalNodes];
		for (int i = 0; i < G.TotalNodes; i++) {
			visited[i] = false;
		}

		for (int i = 0; i < G.TotalNodes; i++) {
			if (!visited[i]) {
				boolean cycle = topologicalDFS(G, i, visited, stack);
				if(cycle)
					break;
			}
		}

		System.out.println("\nDFS Topological Ordering: ");
		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println("\n");
	}

	public static DirectedGraph CreateCyclicGraph() {

		DirectedGraph g = new DirectedGraph(10);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		g.addEdge(1, 8);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(3, 6);
		g.addEdge(4, 6);
		g.addEdge(5, 6);
		g.addEdge(5, 9);
		g.addEdge(6, 7);
		g.addEdge(7, 5);
		g.addEdge(9, 2);
		g.addEdge(9, 8);
		return g;
	}

	public static DirectedGraph CreateAcyclicGraph() {

		DirectedGraph g = new DirectedGraph(10);
		g.addEdge(1, 4);
		g.addEdge(1, 5);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 4);
		g.addEdge(3, 6);
		g.addEdge(3, 8);
		g.addEdge(4, 5);
		g.addEdge(5, 7);
		g.addEdge(5, 9);
		g.addEdge(6, 7);
		g.addEdge(7, 9);
		g.addEdge(7, 0);
		g.addEdge(8, 0);
		g.addEdge(9, 0);
		return g;

	}

	public static void main(String[] args) {

		System.out.println("------ Generating Acyclic Graph ------");
		DirectedGraph acyclicGraph = CreateAcyclicGraph();
		acyclicGraph.printGraph();
		topologicalDFS(acyclicGraph);

		System.out.println("------ Generating Cyclic Graph ------");
		DirectedGraph cyclicGraph = CreateCyclicGraph();
		cyclicGraph.printGraph();
		topologicalDFS(cyclicGraph);

	}

}
