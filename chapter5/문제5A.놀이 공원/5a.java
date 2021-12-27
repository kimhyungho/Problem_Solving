import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	public static void testCase(int caseIndex) {
		// NxN 지도의 크기
		int N = scanner.nextInt();
		// KxK 놀이공원의 크기
		int K = scanner.nextInt();
		
		// 쓰레기 존재 위치
		int[][] wastes = new int[N][N];
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				// 쓰레기 위치 입력
				wastes[r][c] = scanner.nextInt();
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < N - K + 1; i += 1) {
			for(int j = 0; j < N - K + 1; j +=1) {
				int count = 0;
				for(int k = i; k < i + K;  k += 1) {
					for(int l = j; l < j + K; l += 1) {
						if(wastes[k][l] == 1) count += 1;
					}
				}
				answer = Math.min(answer, count);
			}
		}
				
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();
		
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
		
	}
}