package pucp.wallace;

public class UHNode extends IntegerHeap{
	private int min;
	private UHList minElements;
	private boolean isEmpty;
	private int order;
	private int shift;
	private int max;
	private IntegerHeap HQ;
	private UHNode[] LQ;
	private UHElement best = null;
	
	public UHNode(int order) {
		isEmpty = true;
		this.order = order;
		shift = 1 << (order - 1);
		max = (int)((1L << (1 << order)) - 1);
		minElements = new UHList();
		HQ = UHTreeCreator.getNewIntegerHeap(order - 1);
		LQ = new UHNode[max + 1];
	}
	
	public boolean isEmpty() {
		synchronized(this){
			if (isEmpty) {
				assert best ==  null;
			}
			return isEmpty;
		}
	}
	
	public int getMinHash() {
			assert isEmpty == false;
			return min;
	}
	
	public UHList getMinElements() {
		assert isEmpty == false;
		return minElements;
	}
	
	public static int compareUnsignedInt(int a, int b) {
		  long val = (a & 0xffffffffL) - (b & 0xffffffffL);
		  return val < 0 ? -1 : val > 0 ? 1 : 0;
	}
	
	public void add(int x, Object value) {
		UHNode node = this;
		int H, L;
		while (true) {
			synchronized(node){
				if(node.isEmpty() || node.min == x){
						node.min = x;
						node.minElements.addNewElement(value);
						node.isEmpty = false;
						return;
				} else {
						if (compareUnsignedInt(x, node.min) < 0) {
							int aux = node.min; node.min = x; x = aux;
							Object aux2 = node.minElements; node.minElements = new UHList(value); value = aux2; 
						}
						assert node.min != x;
						H = x >>> node.shift;
						L = x ^ (H << node.shift);
						UHNode child = node.LQ[H];
					
						if (child == null) {
							node.HQ.add(H);
							child = new UHNode(order - 1);
							node.LQ[H] = child;
						}
						node = child;
						x = L;
				}
			} 
		}
	}
	
	public void updateBest(UHElement candidate) {
		if (best == null || candidate.getCount() > best.getCount()) {
			best = candidate;
		}
	}
	
	public boolean contains(int x, Object value, UHNode parent) {
		UHNode node = this;
		int H, L;
		while(true) {
			if (node.best != null) {
				synchronized(node.best) {
					 if(node.best.equals(value)) {
						node.best.incr();
						if (parent != null){
							parent.updateBest(node.best);
						}
						return true;
					}
				}
			}
			if(node.isEmpty()) {
				return false;
			} else if (node.min == x) {
				synchronized (node.minElements) {
					return node.minElements.contains(value, this);
				}
			} else {
				H = x >>> node.shift;
				L = x ^ (H << node.shift);		
				UHNode child = node.LQ[H];
				if (child == null) {
					return false;
				} else {
					parent = node;
					node = child;
					x = L;
				}
			}
			
		}
	}
	
	public boolean remove(int x, Object value) {
		synchronized(this){
			if (best != null && best.equals(value)) best = null;
			
			if (isEmpty()) return false;
			int H, L;
			if (min == x) {
				boolean success;
				if (value instanceof UHList) {
					assert ((UHList) value).getSet().equals(minElements.getSet());
					minElements = new UHList();
					best = null;
					success = true;
				} else {
					success = minElements.remove(value);
				}
				if (!success) {
					assert false;
					return false;
				} else {
					if(!minElements.isEmpty()) {
						return true;
					} else {
						if (HQ.isEmpty()) {
							isEmpty = true;
							return true;
						} else {
							H = HQ.getMinHash();
							UHNode child = LQ[H];
							L = child.getMinHash();
							value = minElements = child.getMinElements();
							min = (H << shift) + L;
						}
					}
				}
			} else {
				H = x >>> shift;
				L = x ^ (H << shift);		
			}
			boolean success = false;
			UHNode child = LQ[H];
			if (child != null) {
				success = child.remove(L, value);
				if (child.isEmpty()) {
					LQ[H] = null;
					HQ.remove(H, H);
				}
			}
			return success;
		}
	}
	
}
