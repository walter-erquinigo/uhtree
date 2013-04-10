package pucp.wallace;

public class UHElement<T>{
	private int count = 0;
	private T value;
	private int key;
	
	public UHElement(int key, T value) {
		this.key = key;
		this.value = value;
	}
	
	public void incr() {
		count++;
	}
	
	public void decay(int factor) {
		count >>>= factor;
	}
	
	public int getKey() {
		return key;
	}
	
	public T getValue() {
		return value;
	}

	public boolean equals(UHElement<T> other) {
		return value.equals(other.getValue());
	}
	
	
}
