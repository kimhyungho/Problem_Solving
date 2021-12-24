import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_VALUE = 1000000;
	public static final Sieve sieve = new Sieve(MAX_VALUE);

	public static ArrayList<Integer> getAllPrimeNumbers(int from, int to)
	{
		ArrayList<Integer> primes = new ArrayList<>();

		for(int num = from; num <= to; num += 1)
		{
			if(sieve.isPrimeNumber(num) == true)
			{
				primes.add(num);
			}
		}

		return primes;
	}

	public static void testCase(int caseIndex) {
		// L이상 R이하의 소수의 갯수를 공백없이 출력.
		int L = scanner.nextInt();
		int R = scanner.nextInt();

		// 소수가 들어갈 배열
		ArrayList<Integer> allPrimeNumbers = getAllPrimeNumbers(L, R);
		// 케이스 번호 출력
		System.out.printf("Case #%d:\n", caseIndex);
		// 소수의 개수 출력
		System.out.println(allPrimeNumbers.size());
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();
		
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}


class Sieve //소인수 분해를 빠르게
{
	final int maximumValue;     // 에라토스테네스의 체에서 다룰 가장 큰 범위의 값
	final boolean[] isPrime;    // 각 숫자별 소수 여부
	Sieve(int maximumValue)
	{
		this.maximumValue = maximumValue;
		this.isPrime = new boolean[maximumValue+1];
		this.fillSieve();
	}

	/**
	 *
	 * @param num
	 * @return 'num'이 소수라면 true, 그렇지 않으면 false
	 */
	public boolean isPrimeNumber(int num)
	{
		return this.isPrime[num];
	}

	/**
	 * isPrime 배열의 값을 채우는 함수
	 */
	private void fillSieve()
	{
		for(int i = 2 ; i <= maximumValue ; i += 1) {
			Boolean b = true;
			
			for(int j = 2; j <= Math.sqrt(i); j += 1) {
				if(i % j == 0) {
					b = false;
					break;
				}
			}
			this.isPrime[i] = b;
		}
	}
}