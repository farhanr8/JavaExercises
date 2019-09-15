package assignment3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSVertex {
	Vertex v;
	int level;

	public BFSVertex(Vertex v, int level){
		this.v = v;
		this.level = level;
	}



	public boolean BFS(String data, Vertex v, Map<String, Vertex> vertices) {

		Set<Vertex> visited = new HashSet<Vertex>();	
		Queue<BFSVertex> nodes = new LinkedList<BFSVertex>();
		String str;
		Vertex var;
		nodes.add(new BFSVertex(v,0));
		visited.add(v);

		// While queue not empty
		while (!nodes.isEmpty()){
			
			// Dequeue the first vertex
			BFSVertex cur = nodes.remove();
			
			// Return true only if im on the second level and the strings are equal
			if(cur.level == 2) {
				if(cur.v.name.equals(data)){
					return true;
				}
			}
			
			// Skip all if level greater than 2
			if(cur.level > 2){
				continue;
			}
	
			// Loop through the edges of the vertex to see if
			// visited Set contains neighbor
			for(Map.Entry<String, Integer> neighbor : cur.v.edges.entrySet()){
			
				str = neighbor.getKey();
				var = vertices.get(str);
				
				// If visited does not contain neighbor, 
				// add new vertex to queue incrementing level
				if(!visited.contains(var)){
					nodes.add(new BFSVertex(var, cur.level + 1));
				}
			}

			
		}
		return false;

	}
}
