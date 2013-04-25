package pucp.wallace;

import java.util.Hashtable;

public class UHNode<T> {
	private int min;
	private UHList<T> minElements;
	private boolean isEmpty;
	private int order;
	private int shift;
	private int max;
	private UHNode<Integer> HQ;
	private Hashtable<Integer, UHNode<T>> LQ;
	
	public UHNode(int order) {
		isEmpty = true;
		this.order = order;
		shift = 1 << (order - 1);
		max = (int)((1L << (1 << order)) - 1);
		HQ = new UHNode<Integer>(order - 1);
		LQ = new Hashtable<>();
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public int getMinHash() {
		return min;
	}
	
	public UHList<T> getMinElements() {
		return minElements;
	}
	
	public int compareUnsignedInt(int a, int b) {
		if (((a ^ b) << 31) == 0) {
			return a - b;
		}
		if (a < 0) {
			return 1;
		}
		return -1;
	}
	
	public void add(int x, T value) {
		if(isEmpty || min == x){
			min = x;
			minElements = new UHList<T>(value);
		} else {
			if (compareUnsignedInt(x, min) < 0) {
				int aux = x;
				x = min;
				min = aux;
			}
			int H = x >>> shift;
			int L = x ^ (H << shift);
			if (!LQ.contains(H)) {
				HQ.add(H, H);
				LQ.put(H, new UHNode<T>(order - 1));
			}
			LQ.get(H).add(L, value);
		}
		isEmpty = false;
	}
	
	public boolean remove(int x, T value) {
		if (isEmpty()) return false;
		int H, L;
		if (min == x) {
			boolean success = minElements.remove(value);
			if (!success) {
				return false;
			} else {
				if (HQ.isEmpty()) {
					isEmpty = true;
					return true;
				} else {
					H = HQ.getMinHash();
					L = LQ.get(H).getMinHash();
					minElements = LQ.get(H).getMinElements();
					x = min = (H << shift) + L;
				}
			}
		} else {
			H = x >>> shift;
			L = x ^ (x << shift);		
		}
		boolean success = false;
		if (LQ.contains(H)) {
			success = LQ.get(H).remove(L, value);
			if (LQ.get(H).isEmpty()) {
				LQ.remove(H);
				HQ.remove(H, H);
			}
		}
		
		return success;
	}
	
}
