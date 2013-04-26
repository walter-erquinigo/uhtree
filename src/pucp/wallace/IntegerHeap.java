package pucp.wallace;

public abstract class IntegerHeap {
	public abstract boolean isEmpty();
	public abstract int getMinHash();
	public abstract void add(int x, Object value);
	public abstract boolean remove(int x, Object value);
	public void add(int x) {
		add(x, x);
	}
}
