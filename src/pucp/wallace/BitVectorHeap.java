package pucp.wallace;

public class BitVectorHeap {
	private int mask;
	
	public boolean isEmpty() {
		return mask == 0;
	}
	
	public void add(int x) {
		mask |= 1 << x;
	}
	
	public void remove(int x) {
		mask &= ~(1 << x);
	}
	
	public int getMin(int x) {
		if(isEmpty()) return -1;
		return Integer.numberOfTrailingZeros(mask);
	}
}
