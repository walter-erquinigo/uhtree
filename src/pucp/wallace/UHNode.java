package pucp.wallace;

public class UHNode<T> {
	// Precondition: 	if maxHash != minHash => minHash is not sent to children, whilst every other has is sent.
	//					else => nothing is sent to children.
	
	private final int halfBits;
	private final int mask;
	private int size;
	
	private UHElement<T> bestElement;
	private UHNode<T>[] children;
	private UHList<T> elements;
	
	@SuppressWarnings("unchecked")
	public UHNode(int bits, int initLevels) {
		this.halfBits = bits >>> 1;
		this.mask = (1 << halfBits) - 1;
		this.size = 1 << ((bits + 1) >>> 1);
		children = new UHNode[size];
		if (initLevels > 0) {
			for (int i = 0; i < size; i++)
				children[i] = new UHNode<T>(halfBits, initLevels - 1);
		}
	}
	
	public void insert(int hash, T value) {
		if (size > 1) {
			int index = hash >>> halfBits;
			if (children[index] == null) children[index] = new UHNode<>(halfBits, 0);
			children[index].insert(hash & mask, value);
		} else {
			if (elements == null) elements = new UHList<T>(value);
			else elements.addNewElement(value);
		}
	}
	
	public boolean search(int hash, T value) {
		if (size == 1) return elements == null ? false : elements.search(value);
		else {
			int index = hash >>> halfBits;
			if (children[index] == null) return false;
			else return children[index].search(hash & mask, value);
		}
	}

	public boolean delete(int hash, T value) {
		return false;
	}
	
}
