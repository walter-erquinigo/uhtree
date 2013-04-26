package pucp.wallace;

//import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Tester {
	public static void main(String[] args) {
		UHNode tree = new UHNode(5);
		TreeSet<Integer> auxTree = new TreeSet<>();
		// 107, 104, 0
		Random rand = new Random(107);
		long dif1 = 0, dif2 = 0, t1 = 0, t2 = 0;
		long tt1 = System.nanoTime();
		for(int i = 0; i < 200000; i++){
			System.out.println("CASE: " + (i + 1));
			System.out.println("EMPTINESS: " + tree.isEmpty() + " " + auxTree.isEmpty());
			assert tree.isEmpty() == auxTree.isEmpty();
			if (tree.isEmpty() || rand.nextBoolean()) {
				int x = rand.nextInt(1 << 30);
				int hash = x;
				System.out.println("ADD: " + x);
					t1 = System.nanoTime();
					tree.add(hash, x);
					t2 = System.nanoTime();
				dif2 += t2 - t1;
					t1 = System.nanoTime();
					auxTree.add(x);
					t2 = System.nanoTime();
				dif1 += t2 - t1;
			} else {
				int element = auxTree.first();
				System.out.println("MIN: " + tree.getMinHash() + " " + auxTree.first());
				assert tree.getMinHash() == auxTree.first();
				tree.remove(element, element);
				auxTree.remove(element);
			}
		}
		long tt2 = System.nanoTime();
		long calc = (dif1 - dif2);// / 1000000000L;
		long calc2 = (tt2 - tt1);// / 1000000000L;
		System.out.println((dif1 / 1000000000L)  + " " + (dif2 / 1000000000L) + " " + (dif2 - dif1) / 1000000000L);
		System.out.println(calc2);
		System.out.flush();
	}
}
