package pucp.wallace;

/*
 * Clase que mantiene un heap de enteros en el rango [0,32[
 */
public class UHBitmaskHeap extends IntegerHeap
{
	private int mask;
	
	/*
	 * Crea un UHBitmaskHeap de maximo 2^2^2 enteros.
	 */
	public UHBitmaskHeap(int order) 
	{
		mask = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see pucp.wallace.IntegerHeap#isEmpty()
	 */
	public boolean isEmpty() 
	{
		return mask == 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see pucp.wallace.IntegerHeap#add(int, java.lang.Object)
	 */
	public void add(int x, Object y) 
	{
		mask |= 1 << x;
	}
	
	/*
	 * (non-Javadoc)
	 * @see pucp.wallace.IntegerHeap#remove(int, java.lang.Object)
	 */
	public boolean remove(int x, Object y) 
	{
		if ((mask & (1 << x)) != 0)
		{
			mask ^= 1 << x;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see pucp.wallace.IntegerHeap#getMinHash()
	 */
	public int getMinHash() 
	{
		assert mask != 0;
		return Integer.numberOfTrailingZeros(mask);
	}

}
