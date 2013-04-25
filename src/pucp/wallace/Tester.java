package pucp.wallace;

//import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Tester {
	public static void main(String[] args) {
		UHNode<Integer> tree = new UHNode<>(5);
		TreeSet<Integer> auxTree = new TreeSet<>();
		long t1, t2, t3, t4;
		
		Random rand = new Random();
		ArrayList<Long> difs1 = new ArrayList<>();
		ArrayList<Long> difs2 = new ArrayList<>();
		
		for(int i = 0; i < 20000; i++){
			
			if (tree.isEmpty() || rand.nextBoolean()) {
				int x = rand.nextInt();
				int hash = x;
				tree.add(hash, x);
				auxTree.add(x);
			} else {
				int element = auxTree.first().intValue();
				assert tree.getMinHash() == auxTree.first().intValue();
				tree.remove(element, element);
				auxTree.remove(element);
			}
		}
	}
}
