import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_VALUE = 1000000;
	public static final Sieve sieve = new Sieve(MAX_VALUE);

	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void testCase(int caseIndex) {
		int x = scanner.nextInt();

		int a = -1;
		int b = -1;
				
		for(int i = 0 ; i < sieve.list.size(); i += 1) {
			if(sieve.list.contains(x - sieve.list.get(i))) {
				a = sieve.list.get(i);
				b = x - a;
				break;
			}
		} 

		// 정답을 출력한다
		System.out.printf("Case #%d:\n", caseIndex);
		if(a > 0 && b > 0)
		{
			System.out.printf("%d = %d + %d\n", x, a, b);
		}else{
			System.out.println(-1);
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}


class Sieve {
	final int maxmumValue;
	final ArrayList<Integer> list;
		
	Sieve(int maxmumValue) {
		this.maxmumValue = maxmumValue;
		this.list = new ArrayList<>();
		this.fillSieve();
	}
	
	public void fillSieve() {
		list.add(2);
		for(int i = 3 ; i < this.maxmumValue; i += 2) {
			Boolean b = true;
			for(int j = 2 ; j <= Math.sqrt(i) ;  j += 1) {
				if(i % j == 0) {
					b = false;
					break;
				}
			}
			if(b) this.list.add(i);
		}
	}
}