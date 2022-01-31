import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	// 단어를 검사하는 세 방향에 대하여 한 글자당 행/열에 대한 변화량
	public static int[] deltaR = new int[]{0, 1, 1};
	public static int[] deltaC = new int[]{1, 1, 0};
	
	public static void testCase(int caseIndex) {
		// 데이터를 입력받는다
		int N = scanner.nextInt();
		String P = scanner.next();
		int M = P.length();
		char[][] puzzle = new char[N][N];
		
		for (int row = 0; row < N; row += 1) {
			String line = scanner.next();
			puzzle[row] = line.toCharArray();
		}
		
		// 퍼즐에서 해당 단어가 등장하는 횟수
		int answer = 0;
		
		
		
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();
		
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
	
}