package pucp.wallace;

public class UHNode<T> {
	// Precondition: 	if maxHash != minHash => minHash is not sent to children, whilst every other has is sent.
	//					else => nothing is sent to children.
	
	private final int halfBits;
	private final int mask;
	private int maxHash, minHash;
	private int size;
	
	private UHElement<T> bestElement;
	private UHElement<T> minElements, maxElements; // They are only used if the node has no children.
	private UHNode<T>[] children;
	
	@SuppressWarnings("unchecked")
	public UHNode(int bits, int initLevels) {
		this.halfBits = bits >>> 1;
		this.mask = (1 << halfBits) - 1;
		this.size = 1 << ((bits + 1) >>> 1);
		maxHash = 0; minHash = 1;
		if (initLevels > 0) {
			children = new UHNode[size];
			for (int i = 0; i < size; i++)
				children[i] = new UHNode<T>(halfBits, initLevels - 1);
		}
	}
	
	public void insert(UHElement<T> newNode) {
		int hash = newNode.getHash();
		if(minHash > maxHash) { 	// Node is empty, so we create a new node which is going to be min and max.
			minHash = maxHash = hash;
			minElements = maxElements = newNode;
			return; 	// Terminate.
		}
		if (minHash == maxHash) { 	// There is only one element. The tree doesn't have any children.
			if (hash < minHash) { 	// There is a new min element. Now there will be 2 different elements.
				minHash = hash;
				minElements = newNode;
			} else if (hash > maxHash) { 	// There is a new max element. Now there will be 2 different elements.
				maxHash = hash;
				maxElements = newNode;
			} else {
				newNode.setNext(maxElements);
				maxElements = minElements = newNode;
				return;
			}
		} 
		if (hash < minHash) { 	// There is a new min element and minHash != maxHash, thus swap former and new min elements for inserting former min.
			minHash = hash;
			// Swap nodes.
			UHElement<T> aux = minElements;
			minElements = newNode;
			newNode = aux; // 	newNode may contain forward pointers.
		} else if (hash > maxHash) { 	// There is a new max element and minHash != maxHashNew.
			maxElements = newNode;
			maxHash = hash;
		}
		int index = newNode.getHash() >>> halfBits;
		children[index].insert(newNode);
	}
	
	public boolean search(int hash, T value) {
		if (minHash > maxHash) { // Empty tree.
			return false;
		} else if (hash < minHash || hash > maxHash) { // Not in tree.
			return false;
		} 
		return false;
	}

	public boolean delete(int hash, T value) {
		return false;
	}
	
}
