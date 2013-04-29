package pucp.wallace;

public class UHTreeCreator {
	public static UHTree getNewUHTree(int order) {
		return new UHTree(order);
	}
	
	public static IntegerHeap getNewIntegerHeap(int order) {
		if (order <= 2){
			return new BitVectorHeap(order);
		} else {
			return new UHNode(order);
		}
	}
}
