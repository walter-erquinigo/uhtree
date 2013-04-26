package pucp.wallace;

public class UHTree {
	public static UHNode getNewUHTree(int order) {
		return new UHNode(order);
	}
	
	public static IntegerHeap getNewIntegerHeap(int order) {
		if (order <= 2){
			return new BitVectorHeap(order);
		} else {
			return new UHNode(order);
		}
	}
}
