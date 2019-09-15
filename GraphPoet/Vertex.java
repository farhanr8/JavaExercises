package assignment3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Vertex {
	public String name;
	public Map<String, Integer> edges = new HashMap<String, Integer>(); // String is a vertex, Integer is the weight
	
	public Vertex(String word) {
		this.name = word;
	}
	
	public Vertex() {
		// TODO Auto-generated constructor stub
	}

	public boolean checkAdj(String word) {
		if(edges.containsKey(word)) {
			return true;
		}
		
		return false;
	}
	
	public void addAdj(String word) {
		edges.put(word, 1);
	}
	
	public void addWeight(String word) {
		
		// Increment weight for current vertex
		Integer num = edges.get(word);
		num += 1;
		edges.put(word, num);
	}
	
	public Vertex maxWeight(Set<Vertex> sharedAdj, Vertex word, String nextword) {
    	Vertex max = null;
    	Integer weight = 0;
    	
    	// For all the vertices in set (which contains the in-between vertices),
    	// sum up weight of "current_word -> bridge" and "bridge -> next_word"
    	for(Vertex ver : sharedAdj) {
    		String ver_str = ver.name;
    		Integer w1 = word.edges.get(ver_str);
    		Integer w2 = ver.edges.get(nextword);
    		Integer w = w1 + w2;
    		
			if (w > weight) {
				max = ver;
				weight = w;
			}
		}
    	
    	return max;
    }
}
