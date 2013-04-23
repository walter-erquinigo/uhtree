package pucp.wallace;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.TreeSet;

public class Tester {
	public static void main(String[] args) {
		UHTree<Long> tree = new UHTree<>(32, 1);
		TreeSet<Long> auxTree = new TreeSet<>();
		long t1, t2, t3, t4;
		
		System.out.println("Finish creating");
		Scanner in = new Scanner(System.in);
		int casos = in.nextInt();
		for(int i = 0; i < casos; i++){
			String tipo = in.next();
			long val = in.nextLong();
			int hash = (int)(val & Constants.FULL_MASK);
			System.out.println(new BigInteger("" + val).toString(16) + " " + new BigInteger("" + hash).toString(16));
			// UHTree
			t1 = System.nanoTime();
			if (tipo.charAt(0) == 'I')
				tree.insert(hash, val);
			else if(tipo.charAt(0) == 'S')
				System.out.println(tree.search(hash, val));
			t2 = System.nanoTime();
			// TreeSet
			t3 = System.nanoTime();
			if (tipo.charAt(0) == 'I')
				auxTree.add(val);
			else if(tipo.charAt(0) == 'S')
				System.out.println(auxTree.contains(val));
			t4 = System.nanoTime();
			System.out.println((t2 - t1) + " " + (t4 - t3) + " " + (t2 - t1 - t4 + t3));
		}
		in.close();
	}
}
