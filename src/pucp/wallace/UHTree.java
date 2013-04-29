package pucp.wallace;

public class UHTree {
	private UHNode root;
		
	public UHTree(int order) {
		root = new UHNode(order);
	}

	public boolean contains(int x, Object o) {
		return root.contains(x, o, null);
	}
	
	public boolean remove(int x, Object o) {
		return root.remove(x, o);
	}
	
	public void add(int x, Object o) {
		root.add(x, o);
	}
	
	public boolean isEmpty() {
		return root.isEmpty();
	}
	
	public int getMinHash() {
		return root.getMinHash();
	}
}
