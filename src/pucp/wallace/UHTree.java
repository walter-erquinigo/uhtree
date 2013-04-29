package pucp.wallace;

/*
 * Esta clase es el UHTree, que es un arbol binario de busqueda concurrente que trabaja con pares
 * <hash, value>, donde hash es un entero que es hash de value.
 */
public class UHTree 
{
	private UHNode root;
	
	/*
	 * Crea una nuevo UHTree cuyos hash estan en el rango [0, 2^2^order[. Order es un entero entre 0 y 5 inclusive
	 */
	public UHTree(int order)
	{
		root = new UHNode(order);
	}

	/*
	 * Determina si un elemento pertenece al arbol.
	 */
	public boolean contains(int hash, Object value) 
	{
		return root.contains(hash, value);
	}
	
	/*
	 * Elimina un elemento del arbol, devuelve true si y solo si dicho elemento existia en el arbol.
	 */
	public boolean remove(int hash, Object value)
	{
		return root.remove(hash, value);
	}
	
	/*
	 * Agrega un elemento al arbol.
	 */
	public void add(int hash, Object value)
	{
		root.add(hash, value);
	}
	
	/*
	 * Determina si el arbol esta vacio.
	 */
	public boolean isEmpty()
	{
		return root.isEmpty();
	}
	
	/*
	 * Obtiene el menor valor hash del arbol.
	 */
	public int getMinHash()
	{
		return root.getMinHash();
	}
}
