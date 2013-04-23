package pucp.wallace;

import java.util.ArrayList;

public class UHList<T> {
	private ArrayList<UHElement<T>> list = new ArrayList<>();
	
	public UHList(T value) {
		list.add(new UHElement<T>(value));
	}
	
	public void addNewElement(T value) {
		list.add(new UHElement<T>(value));
	}
	
	public boolean search(T value) {
		for(int i = 0; i < list.size(); i++)
			if(list.get(i).getValue().equals(value)) return true;
		return false;
	}
	
}
