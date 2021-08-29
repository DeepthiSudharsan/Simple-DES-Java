package AC;

import java.util.*;

public class A2 {

	public static int Primes() {
		ArrayList<Integer> Primes = new ArrayList<Integer>( );
		int i, j, flag;
		for (i = 100; i <= 1000; i++) {
			
			// Skip 0 and 1 as they are
			// neither prime nor composite
			if (i == 1 || i == 0)
				continue;

			// flag variable to tell
			// if i is prime or not
			flag = 1;

			for (j = 2; j <= i / 2; ++j) {
				if (i % j == 0) {
					flag = 0;
					break;
				}
			}

			// flag = 1 means i is prime
			// and flag = 0 means i is not prime
			if (flag == 1)
				Primes.add(i);
		}
		
		int max = Primes.size();
		int min = 1;
		int m = Primes.get((int) (Math.random()*(max-min+1)+min));

		return m;
	}
	
	public static ArrayList<Integer> AddInv(){
		ArrayList<Integer> AddInv = new ArrayList<Integer>( );
		int m = Primes();
		
		for(int i = 1;i < m - 1; i++) {
			AddInv.add(((m - i)+i) % m);
		}
		return AddInv;	
	}
	
	public static ArrayList<Integer> MultInv(){
		ArrayList<Integer> MultInv = new ArrayList<Integer>( );
		int m = Primes();
		
		for(int i = 1;i < m - 1; i++) {
			int q = m / i;
			int r1 = m;
			int r2 = i;
			int r = m % i;
			int t1 = 0;
			int t2 = 1;
			int t = t1 - q * t2;
			
			while (r != 0) {
				q = r2/r;
				r1 = r2;
				r2 = r;
				r = r1 % r2;
				t1 = t2;
				t2 = t;
				t = t1 - q * t2;
			}
			MultInv.add(Math.floorMod(t2 * i, m));
		}
		return MultInv;	
	}
	
	public static void main(String[] args) {
		
		System.out.print(Primes());
		System.out.println();
		System.out.print(AddInv().toString());
		System.out.println();
		System.out.print(MultInv().toString());
		
		
	}
}
