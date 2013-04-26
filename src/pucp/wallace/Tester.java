package pucp.wallace;

//import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class Tester {
	
	private static int getHash(int x, int bits) {
		int mask = (int)((1L << (1 << bits)) - 1);
		return x & mask;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int bits = 2;
		UHNode tree = new UHNode(bits);
		TreeSet<Integer> auxTree = new TreeSet<>();
		Scanner in = new Scanner(System.in);
		// 107, 104, 0
		Random rand = new Random();
		long dif1 = 0, dif2 = 0, t1 = 0, t2 = 0;
		long tt1 = System.nanoTime();
		for(int i = 0; i < 200000; i++){
			System.out.println("CASE: " + (i + 1));
			System.out.println("EMPTINESS: " + tree.isEmpty() + " " + auxTree.isEmpty());
			assert tree.isEmpty() == auxTree.isEmpty();
			char op = in.next().charAt(0);
			if (op == 'I') {
				int x = in.nextInt();
				int hash = getHash(x, bits);
				System.out.println("ADD: " + x);
					t1 = System.nanoTime();
					tree.add(hash, x);
					t2 = System.nanoTime();
				dif2 += t2 - t1;
					t1 = System.nanoTime();
					auxTree.add(x);
					t2 = System.nanoTime();
				dif1 += t2 - t1;
			} else if (op == 'D'){
				int element = auxTree.first();
				System.out.println("MIN: " + tree.getMinHash() + " " + auxTree.first());
				assert tree.getMinHash() == auxTree.first();
				element = in.nextInt();
				int hash = getHash(element, bits);
				tree.remove(hash, element);
				auxTree.remove(element);
				System.out.println("MIN: " + tree.getMinHash() + " " + auxTree.first());
			} else if (op == 'S') {
				int element = in.nextInt();
				int hash = getHash(element, bits);
				System.out.println(tree.contains(hash, element));
				assert tree.contains(hash, element) == auxTree.contains(element);
			}
		}
		long tt2 = System.nanoTime();
		long calc = (dif1 - dif2);// / 1000000000L;
		long calc2 = (tt2 - tt1);// / 1000000000L;
		System.out.println((dif1 * 100 / dif2));

		System.out.println((dif1 / 1000000000L)  + " " + (dif2 / 1000000000L) + " " + (dif2 - dif1) / 1000000000L);
		System.out.println(calc2);
		System.out.flush();
		in.close();
	}
}
