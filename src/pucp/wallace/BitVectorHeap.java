package pucp.wallace;

public class BitVectorHeap extends IntegerHeap{
	private int mask;
	
	public BitVectorHeap(int order) {
		mask = 0;
	}
	public boolean isEmpty() {
		return mask == 0;
	}
	
	public void add(int x, Object y) {
		mask |= 1 << x;
	}
	
	public boolean remove(int x, Object y) {
		if ((mask & (1 << x)) != 0){
			mask ^= 1 << x;
			return true;
		} else {
			return false;
		}
	}
	
	public int getMinHash() {
		assert mask != 0;
		return Integer.numberOfTrailingZeros(mask);
	}

}
