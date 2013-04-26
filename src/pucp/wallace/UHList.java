package pucp.wallace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class UHList {
	private Set<UHElement> set = Collections.newSetFromMap(new HashMap<UHElement, Boolean>(2));
	
	public UHList() {
	}

	public UHList(Object value) {
		addNewElement(value);
	}
	
	public void addNewElement(Object value) {
		if(value instanceof UHList) {
			set.addAll(((UHList)value).set);
		} else {
			set.add(new UHElement(value));
		}
	}
	
	public boolean contains(Object value) {
		return set.contains(new UHElement(value));
	}
	
	public boolean isEmpty() {
		return set.isEmpty();
	}
	
	public Set<UHElement> getSet(){
		return set;
	}
	
	public boolean remove(Object value) {
		assert !(value instanceof UHList);
		boolean success = set.remove(new UHElement(value));
		return success;
	}
	
}
