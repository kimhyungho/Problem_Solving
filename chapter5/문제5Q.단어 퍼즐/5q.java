import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	// 단어를 검사하는 세 방향에 대하여 한 글자당 행/열에 대한 변화량
	// 가로, 대각선, 세로
	public static int[] deltaR = new int[]{0, 1, 1};
	public static int[] deltaC = new int[]{1, 1, 0};
	
	public static void testCase(int caseIndex) {
		// 데이터를 입력받는다
		// NxN 격자
		int N = scanner.nextInt();
		// 2글자 이상 100글자 이하의 대문자 영단어
		String P = scanner.next();
		int M = P.length();
		char[][] puzzle = new char[N][N];
		
		// 퍼즐 판 입력.
		for (int row = 0; row < N; row += 1) {
			String line = scanner.next();
			puzzle[row] = line.toCharArray();
		}
		
		// 퍼즐에서 해당 단어가 등장하는 횟수
		int answer = 0;
		
		for(int i = 0; i < N; i += 1) {
			for(int j = 0; j < N; j += 1) {
				
				if(puzzle[i][j] == P.charAt(0)) {
					for(int count = 0; count < 3; count += 1) {
						Boolean isSame = true;
						for(int k = 0; k < M; k += 1) {
							int r = i + deltaR[count] * k;
							int c = j + deltaC[count] * k;
							
							if( r >= N || c >= N) {
								isSame = false;
								break;
							}
							
							if(puzzle[r][c] != P.charAt(k)) {
								isSame = false;
								break;
							}
						}
						if(isSame) answer += 1;
					}
				}
			}
		}
		
		
		
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws Exception {
		// 테스트 케이스의 수 t
		int caseSize = scanner.nextInt();
		
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
	
}