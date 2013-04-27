package pucp.wallace;

import java.util.HashMap;

public class UHList {
	private HashMap<UHElement, UHElement> set = new HashMap<>(2);
	private UHElement best = null;
	
	public UHList() {
	}

	public UHList(Object value) {
		addNewElement(value);
	}
	
	public void addNewElement(Object value) {
		if(value instanceof UHList) {
			set.putAll(((UHList)value).set);
		} else {
			UHElement newElement = new UHElement(value);
			set.put(newElement, newElement);
		}
	}
	
	public boolean contains(Object value, UHNode uhNode) {
		UHElement element = set.get(new UHElement(value));
		if (element == null) {
			return false;
		} else {
			element.incr();
			if (best == null || best.getCount() < element.getCount()) {
				best = element;
			}
			uhNode.updateBest(best);
			return true;
		}
	}
	
	public boolean isEmpty() {
		return set.isEmpty();
	}
	
	public HashMap<UHElement, UHElement> getSet(){
		return set;
	}
	
	public boolean remove(Object value) {
		assert !(value instanceof UHList);
		boolean success = set.remove(new UHElement(value)) != null;
		if (success && value.equals(best)) best = null;
		return success;
	}
	
}
