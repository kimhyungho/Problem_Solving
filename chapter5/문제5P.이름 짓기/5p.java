import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

	public static final Scanner scanner = new Scanner(System.in);
  
	public static Boolean testCase(int index) {
		int N = scanner.nextInt();
		String name = scanner.next();
		String[] words = new String[N];
		int startAt = 0;
		int firstWordIndex = -1;
		int secondWordIndex = -1;
		
		for(int i = 0; i < N; i += 1) {
			words[i] = scanner.next();
		}
		
		for(int i = 0; i < 3; i += 1) {
			for(int j = 0; j < N; j += 1) {
				String word = words[j];
				
				if(name.length() - startAt < word.length()){
					continue;
				}
				
				String namePart = name.substring(startAt, startAt + word.length());
					
				if(word.equals(namePart) && j != firstWordIndex && j != secondWordIndex) {
					startAt = startAt + word.length();
					if(i == 0) firstWordIndex = j;
					if(i == 1) secondWordIndex = j;
					break;
				}
				
				if(j == N - 1) return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		int T = scanner.nextInt();
		
		for(int i = 0; i < T; i += 1) {
			if(testCase(i)) System.out.println("YES");
			else System.out.println("NO");
		}
  }
}