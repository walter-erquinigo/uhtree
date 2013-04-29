package pucp.wallace;

/*
 * Clase abstracta que mantiene un heap de enteros, los cuales tienen valores asociados.
 */
public abstract class IntegerHeap {
	/*
	 * Determina si el heap esta vacio.
	 */
	public abstract boolean isEmpty();

	/*
	 * Determina el menor entero dentro del heap.
	 */
	public abstract int getMinHash();

	/*
	 * Agrega un nuevo entero con su value asociado.
	 */
	public abstract void add(int x, Object value);

	/*
	 * Remueve un entero junto con su valor asociado.
	 */
	public abstract boolean remove(int x, Object value);

	/*
	 * Agrega un entero cuyo valor asociado es el mismo.
	 */
	public void add(int x) {
		add(x, x);
	}
}
