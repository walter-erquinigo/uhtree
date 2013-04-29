package pucp.wallace;

import java.util.concurrent.locks.ReentrantReadWriteLock;

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
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public UHNode(int order) {
		isEmpty = true;
		this.order = order;
		shift = 1 << (order - 1);
		max = (int)((1L << (1 << order)) - 1);
		minElements = new UHList();
		HQ = UHTreeCreator.getNewIntegerHeap(order - 1);
		LQ = new UHNode[max + 1];
	}
	
	/*private void rangeCheck(int x) {
		assert compareUnsignedInt(x, max) <= 0;
	}*/
	
	/*private void minCheck() {
		if (!HQ.isEmpty()) {
			int H = HQ.getMinHash();
			int L = LQ[H].getMinHash();
			int x = (H << shift) + L;
			assert x != min;
		}
	}*/
	
	public boolean isEmpty() {
		rwl.readLock().lock();
		try{
			if (isEmpty) {
				assert best ==  null;
			}
			return isEmpty;
		} finally {
			rwl.readLock().unlock();
		}
	}
	
	public int getMinHash() {
		rwl.readLock().lock();
		try{
			assert isEmpty == false;
			return min;
		} finally {
			rwl.readLock().unlock();
		}
	}
	
	public UHList getMinElements() {
		rwl.readLock().lock();
		try{
			assert isEmpty == false;
			return minElements;
		} finally {
			rwl.readLock().unlock();
		}
	}
	
	public static int compareUnsignedInt(int a, int b) {
		  long val = (a & 0xffffffffL) - (b & 0xffffffffL);
		  return val < 0 ? -1 : val > 0 ? 1 : 0;
	}
	
	public void add(int x, Object value) {
		UHNode node = this;
		int H, L;
		while (true) {
			//node.rangeCheck(x);
			ReentrantReadWriteLock lock = node.rwl;
			lock.writeLock().lock();
			try{
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
			} finally {
				lock.writeLock().unlock();
			}
			//node.minCheck();
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
			ReentrantReadWriteLock lock = node.rwl;
			lock.readLock().lock();
			try{
				if (node.best != null && node.best.equals(value)) {
					node.best.incr();
					if (parent != null){
						parent.updateBest(node.best);
					}
					return true;
				}
				//node.rangeCheck(x);
				if(node.isEmpty()) {
					return false;
				} else if (node.min == x) {
					return node.minElements.contains(value, this);
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
			} finally {
				lock.readLock().unlock();
			}
		}
	}
	
	public boolean remove(int x, Object value) {
		//rangeCheck(x);
		rwl.writeLock().lock();
		try {
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
				//	minCheck();
					return false;
				} else {
					if(!minElements.isEmpty()) {
					//	minCheck();
						return true;
					} else {
						if (HQ.isEmpty()) {
							isEmpty = true;
						//	minCheck();
							return true;
						} else {
						//	minCheck();
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
			//minCheck();
			return success;
		}finally {
			rwl.writeLock().unlock();
		}
	}
	
}
