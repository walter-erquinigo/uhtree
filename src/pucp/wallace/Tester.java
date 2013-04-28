package pucp.wallace;

//import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import pucp.wallace.RandomDistribution.Zipf;

public class Tester {
	
	private static int getHash(int x, int bits) {
		int mask = (int)((1L << (1 << bits)) - 1);
		return x & mask;
	}

	private static char nextOperation(Zipf rand) {
		int o = rand.nextInt();
		return o == 0? 'I' : o == 1 ? 'S' : 'D';
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Random random = new Random(0);
		RandomDistribution.Zipf zipf = new RandomDistribution.Zipf(random, 0, 10000, 1/0.9);
		RandomDistribution.Zipf rand = new RandomDistribution.Zipf(random, 0, 3, 1/0.98);
		
		int bits = 4;
		UHNode tree = new UHNode(bits);
		TreeSet<Integer> auxTree = new TreeSet<>();
		Scanner in = new Scanner(System.in);
		// 107, 104, 0
		long dif1 = 0, dif2 = 0, t1 = 0, t2 = 0;
		long tt1 = System.nanoTime();
		for(int i = 0; i < 20000; i++){
			System.out.println("CASE: " + (i + 1));
			System.out.println("EMPTINESS: " + tree.isEmpty() + " " + auxTree.isEmpty());
			assert tree.isEmpty() == auxTree.isEmpty();
			char op = nextOperation(rand);
			if (op == 'I') {
				int x = zipf.nextInt();
				int hash = getHash(x, bits);
				System.out.println("ADD: " + x);
					t1 = System.nanoTime();
					tree.add(hash, x);
					t2 = System.nanoTime();
				dif1 += t2 - t1;
					t1 = System.nanoTime();
					auxTree.add(x);
					t2 = System.nanoTime();
				dif2 += t2 - t1;
			} else if (op == 'D' && !tree.isEmpty()){
				System.out.println("MIN: " + tree.getMinHash() + " " + auxTree.first());
				assert tree.getMinHash() == auxTree.first();
				int element = zipf.nextInt();
				int hash = getHash(element, bits);
					t1 = System.nanoTime();
					tree.remove(hash, element);
					t2 = System.nanoTime();
				dif1 += t2 - t1;
					t1 = System.nanoTime();
					auxTree.remove(element);
					t2 = System.nanoTime();
				dif2 += t2 - t1;
				System.out.println("MIN: " + tree.getMinHash() + " " + auxTree.first());
			} else if (op == 'S') {
				int element = zipf.nextInt();
				int hash = getHash(element, bits);
				System.out.println(tree.contains(hash, element, null));
					t1 = System.nanoTime();
					tree.contains(hash, element, null);
					t2 = System.nanoTime();
				dif1 += t2 - t1;
					t1 = System.nanoTime();
					auxTree.contains(element);
					t2 = System.nanoTime();
				dif2 += t2 - t1;
				assert tree.contains(hash, element, null) == auxTree.contains(element);
			}
		}
		long tt2 = System.nanoTime();
		long calc = (dif1 - dif2);// / 1000000000L;
		long calc2 = (tt2 - tt1);// / 1000000000L;
		System.out.println((dif1 / dif2));

		System.out.println((dif1 / 1000000000.0)  + " " + (dif2 / 1000000000.0) + " " + (dif2 - dif1) / 1000000000.0);
		System.out.println(calc2);
		System.out.flush();
		in.close();
	}
}
