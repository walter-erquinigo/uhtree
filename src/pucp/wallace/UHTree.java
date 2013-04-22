package pucp.wallace;

public class UHTree<T> {
	private UHNode<T> root;
	
	public UHTree(int bits, int initLevels) {
		root = new UHNode<T>(bits, initLevels);
	}
	public UHTree(int bits) {
		this(bits, 2);
	}
	
	public void insert(int hash, T value) {
		UHElement<T> newNode = new UHElement<T>(hash, value);
		root.insert(newNode);
	}
	
	public boolean delete(int hash, T value) {
		return root.delete(hash, value);
	}
	
	public boolean search(int hash, T value) {
		return root.search(hash, value);
	}
}
