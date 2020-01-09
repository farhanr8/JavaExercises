
public class LinearProbingHT extends HashTable {
	
	public LinearProbingHT(int cap) {
		super(cap);
	}

	@Override
	public void insert(String key) {
		// Load factor check
		if (currentSize >= maxSize/2) 
			resize(2*maxSize);
		
		int temp = hash(key);
		int idx = temp;
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
			
			// Collision occurred, do linear probing
			collision++;
			idx = (idx + 1) % maxSize;
		} while (idx != temp);

	}
	
	private void resize(int capacity) {
        HashTable temp = new LinearProbingHT(capacity);
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
		while (keys[idx] != null) {
			if (keys[idx].equals(key)) {
				return Integer.toString(idx);
			}
			// Linear probe
			idx = (idx + 1) % maxSize;
		}
		return null;
	}

}
