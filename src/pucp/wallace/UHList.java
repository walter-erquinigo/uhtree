package pucp.wallace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class UHList<T> {
	private Set<UHElement<T>> set = Collections.newSetFromMap(new HashMap<UHElement<T>, Boolean>());
	//private UHElement<T> best;
	
	public UHList(T value) {
		set.add(/*best = */new UHElement<T>(value));
	}
	
	public void addNewElement(T value) {
		UHElement<T> element = new UHElement<T>(value);
		//if (set.isEmpty()) best = element;
		set.add(element);
	}
	

	/*public UHElement<T> getBest(){
		return best;
	}*/
	
	public boolean remove(T value) {
		boolean success = set.remove(new UHElement<T>(value));
		/*if (best != null && best.equals(value)) {
			best = null;
		}*/
		return success;
	}
	
}
