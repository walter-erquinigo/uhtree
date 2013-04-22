package pucp.wallace;

public class UHElement<T>{
	private int count = 0;
	private T value;
	private int hash;
	private UHElement<T> next;
	
	public UHElement(int key, T value) {
		this.hash = key;
		this.value = value;
	}
	
	public void incr() {
		count++;
	}
	
	public void decay() {
		if(count >= (1<<24))
			count >>>= 4;
		else if(count >= (1<<16))
			count >>>= 3;
		else if(count >= (1<<8))
			count >>>= 2;
		else count >>>= 1;
	}
	
	public int getHash() {
		return hash;
	}
	
	public T getValue() {
		return value;
	}

	public boolean equals(UHElement<T> other) {
		return value.equals(other.getValue());
	}
	
	public void setNext(UHElement<T> newElement) {
		next = newElement;
	}
}
