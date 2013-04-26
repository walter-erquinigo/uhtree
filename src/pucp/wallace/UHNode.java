package pucp.wallace;

import java.util.Hashtable;

public class UHNode extends IntegerHeap{
	private int min;
	private UHList minElements;
	private boolean isEmpty;
	private int order;
	private int shift;
	private int max;
	private IntegerHeap HQ;
	private Hashtable<Integer, UHNode> LQ;
	
	public UHNode(int order) {
		isEmpty = true;
		this.order = order;
		shift = 1 << (order - 1);
		max = (int)((1L << (1 << order)) - 1);
		minElements = new UHList();
		HQ = UHTree.getNewIntegerHeap(order - 1);
		LQ = new Hashtable<>();
	}
	
	private void rangeCheck(int x) {
		assert compareUnsignedInt(x, max) <= 0;
	}
	
	private void minCheck() {
		if (!HQ.isEmpty()) {
			int H = HQ.getMinHash();
			int L = LQ.get(H).getMinHash();
			int x = (H << shift) + L;
			assert x != min;
		}
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public int getMinHash() {
		assert isEmpty == false;
		return min;
	}
	
	public UHList getMinElements() {
		assert isEmpty == false;
		return minElements;
	}
	
	public int compareUnsignedInt(int a, int b) {
		  long val = (a & 0xffffffffL) - (b & 0xffffffffL);
		  return val < 0 ? -1 : val > 0 ? 1 : 0;
	}
	
	public void add(int x, Object value) {
		rangeCheck(x);
		if(isEmpty() || min == x){
			min = x;
			minElements.addNewElement(value);
			isEmpty = false;
		} else {
			if (compareUnsignedInt(x, min) < 0) {
				int aux = min; min = x; x = aux;
				Object aux2 = minElements; minElements = new UHList(value); value = aux2; 
			}
			assert min != x;
			int H = x >>> shift;
			int L = x ^ (H << shift);
			if (!LQ.containsKey(H)) {
				HQ.add(H);
				LQ.put(H, new UHNode(order - 1));
			}
			LQ.get(H).add(L, value);
		}
		minCheck();
	}
	
	public boolean remove(int x, Object value) {
		rangeCheck(x);
		if (isEmpty()) return false;
		int H, L;
		if (min == x) {
			boolean success;
			if (value instanceof UHList) {
				assert ((UHList) value).getSet().equals(minElements.getSet());
				minElements = new UHList();
				success = true;
			} else {
				success = minElements.remove(value);
			}
			if (!success) {
				assert false;
				minCheck();
				return false;
			} else {
				if(!minElements.isEmpty()) {
					minCheck();
					return true;
				} else {
					if (HQ.isEmpty()) {
						isEmpty = true;
						minCheck();
						return true;
					} else {
						minCheck();
						H = HQ.getMinHash();
						L = LQ.get(H).getMinHash();
						value = minElements = LQ.get(H).getMinElements();
						min = (H << shift) + L;
						//assert minElements.getSet().contains(o)
					}
				}
			}
		} else {
			H = x >>> shift;
			L = x ^ (H << shift);		
		}
		boolean success = false;
		if (LQ.containsKey(H)) {
			success = LQ.get(H).remove(L, value);
			if (LQ.get(H).isEmpty()) {
				LQ.remove(H);
				HQ.remove(H, H);
			}
		}
		minCheck();
		return success;
	}
	
}
