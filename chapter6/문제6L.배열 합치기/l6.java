import java.io.*;
import java.lang.*;
import java.util.*;

class ArrayUtil {
	
	/**
	 * 두 오름차순 배열 A와 B를 하나로 합쳐 재정렬하는 함수
	 *
	 * @param A 배열 1
	 * @param N 배열 1의 길이
	 * @param B 배열 2
	 * @param M 배열 2의 길이
	 * @return 두 배열을 합쳐 정렬한 결과를 반환한다
	 */
	public static int[] mergerTwoSortedArray(int[] A, int N, int[] B, int M) {
		int[] C = new int[N + M];
		
		int aIndex = 0;
		int bIndex = 0;
		
		for(int i = 0; i < N + M; i += 1) {
			int a = Integer.MAX_VALUE;
			int b = Integer.MAX_VALUE;
			
			if(aIndex < N) a = A[aIndex];
			if(bIndex < M) b = B[bIndex];
			
			if(a < b) {
				C[i] = a;
				aIndex += 1;
			} else {
				C[i] = b;
				bIndex += 1;
			}
		}
		
		return C;
	}
}

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) throws Exception {
		// 첫 배열의 크기
		int N = scanner.nextInt();
		// 두번째 배열의 크기
		int M = scanner.nextInt();
		
		int[] A = new int[N];
		int[] B = new int[M];
		
		// 배열 A 채우기
		for (int i = 0; i < N; i += 1) {
			A[i] = scanner.nextInt();
		}
		// 배열 B 채우기
		for (int j = 0; j < M; j += 1) {
			B[j] = scanner.nextInt();
		}
		
		// 배열 합치기.
		int[] mergedArray = ArrayUtil.mergerTwoSortedArray(A, N, B, M);
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < mergedArray.length; i += 1) {
			if (i > 0) {
				builder.append(' ');
			}
			
			builder.append(mergedArray[i]);
		}
		
		System.out.println(builder.toString());
	}
	
}

