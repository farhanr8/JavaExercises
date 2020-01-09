
public class QuadraticProbingHT extends HashTable {
	public QuadraticProbingHT(int cap) {
		super(cap);
	}

	@Override
	public void insert(String key) {
		// Load factor check
		if (currentSize >= maxSize/2) 
			resize(2*maxSize);
		
		int temp = hash(key);
		int idx = temp;
		int num = 1;
		do {
			// Found empty spot in table
			if (keys[idx] == null) {
				keys[idx] = key;
				currentSize++;
				return;
			}
			
			// Found the key already inserted in table
			if (keys[idx].equals(key)) {
				return;
			}
			
			// Collision occurred, do quadratic probing
			collision++;
			idx = (idx + num * num) % maxSize;
			num++;
		} while (idx != temp);

	}
	
	private void resize(int capacity) {
        HashTable temp = new QuadraticProbingHT(capacity);
        System.out.println("Increasing table size to " + capacity);
        
        // Re-hashing
        for (int i = 0; i < maxSize; i++) {
            if (keys[i] != null) {
                temp.insert(keys[i]);
            }
        }
        
        // Update 
        keys = temp.keys;
        maxSize = temp.maxSize;
	}
	
	@Override
	public String get(String key) {
		/* Returns index containing the key */
		int idx = hash(key);
		int num = 1;
		while (keys[idx] != null) {
			if (keys[idx].equals(key)) {
				return Integer.toString(idx);
			}
			// Quadratic probe
			idx = (idx + num * num) % maxSize;
			num++;
		}
		return null;
	}
        

}
