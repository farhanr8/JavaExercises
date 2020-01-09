
public abstract class HashTable {
	int currentSize, maxSize;
	static int collision = 0;
	String[] keys;
	
	public HashTable(int cap) {
		currentSize = 0;
		maxSize = cap;
		keys = new String[maxSize];
	}

	protected int hash(String key) {
		return (key.hashCode() & 0x7fffffff) % maxSize;
	}

	public boolean contains(String key) {
		return (get(key) != null);
	}

	public abstract String get(String key);
	
	public abstract void insert(String key);
	
}
