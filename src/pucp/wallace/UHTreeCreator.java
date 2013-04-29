package pucp.wallace;

/*
 * Esta clase permite crear un UHTree o un IntegerHeap basado en un UHTree o en una mascara de bits.
 */
public class UHTreeCreator 
{	
	/*
	 * Retorna un UHTree de indices de 2^order bits
	 */
	public static UHTree getNewUHTree(int order) 
	{
		return new UHTree(order);
	}
	
	/*
	 * Retorna un IntegerHeap de indices de 2^order bits. Experimentalmente se sabe que
	 * 2^2^order debe ser maximo 32 para mejorar el rendimiento.
	 */
	public static IntegerHeap getNewIntegerHeap(int order) 
	{
		if (order <= 2)
		{
			return new UHBitmaskHeap(order);
		} else {
			return new UHNode(order);
		}
	}
}
