package pucp.wallace;

import java.util.concurrent.ConcurrentHashMap;

/*
 * Clase que para cierto hash, mantiene todos los values asociados.
 */
public class UHList {
	private ConcurrentHashMap<UHElement, UHElement> set = new ConcurrentHashMap<>(
			2);
	private UHElement best = null;

	public UHList() {
	}

	public UHList(Object value) {
		addNewElement(value);
	}

	/*
	 * Agrega un nuevo elemento a la lista.
	 */
	public void addNewElement(Object value) {
		if (value instanceof UHList) {
			set.putAll(((UHList) value).set);
		} else {
			UHElement newElement = new UHElement(value);
			set.put(newElement, newElement);
		}
	}

	/*
	 * Determina si la lista contiene el value dado. uhNode es el padre de esta
	 * lista.
	 */
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

	/*
	 * Determina si la lista esta vacia.
	 */
	public boolean isEmpty() {
		return set.isEmpty();
	}

	/*
	 * Devuelve el conjunto que mantiente los objetos.s
	 */
	public ConcurrentHashMap<UHElement, UHElement> getSet() {
		return set;
	}

	/*
	 * Remueve el value dado de la lista.
	 */
	public boolean remove(Object value) {
		assert !(value instanceof UHList);
		boolean success = set.remove(new UHElement(value)) != null;
		if (success && value.equals(best))
			best = null;
		return success;
	}

}
