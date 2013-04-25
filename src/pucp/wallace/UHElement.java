package pucp.wallace;

public class UHElement<T>{
	private int count = 0;
	private T value;
	
	public UHElement(T value) {
		this.value = value;
	}
	
	public void incr() {
		count++;
	}
	
	public int getCount() {
		return count;
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
	
	public T getValue() {
		return value;
	}

	public boolean equals(UHElement<T> other) {
		return value.equals(other.getValue());
	}
	
	public boolean equals(Object other) {
		return value.equals(other);
	}
	
}
