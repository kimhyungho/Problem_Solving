import java.io.*;
import java.lang.*;
import java.util.*;

class Main {

	public static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
		int t = scanner.nextInt();
		
		for(int i = 0; i < t; i += 1) {
			testCase();
		}
		
  }
	
	
	public static void testCase() {
		int n = scanner.nextInt();
		int[][] array = new int[n][n];
		
		for(int i = 0; i < n; i += 1){
			Arrays.fill(array[i], 0);
		}
		
		int k = 1;
		int i = 0;
		int j = 0;
		boolean isJ = true;
		boolean isPlus = true;
		
		while(k <= n * n) {
			
			array[i][j] = k;
			
			k += 1;
			
			if(isJ && isPlus) {
				j += 1;
				if(j >= n || array[i][j] != 0) {
					j -= 1;
					isJ = false;
					i += 1;
				}
			} else if(isJ && !isPlus) {
				j -= 1;
				if(j < 0 || array[i][j] != 0) {
					j += 1;
					isJ = false;
					i -= 1;
				}
			} else if(!isJ && isPlus) {
				i += 1;
				if(i >= n || array[i][j] != 0) {
					i -= 1;
					isJ = true;
					isPlus = false;
					j -= 1;
				}
			} else if(!isJ && !isPlus) {
				i -= 1;
				if(i < 0 || array[i][j] != 0) {
					i += 1;
					isJ = true;
					isPlus = true;
					j += 1;
				}
			}
		}
		
		for(int p = 0; p < n; p += 1) {
			for(int q = 0; q < n; q += 1) {
				System.out.print(array[p][q]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
}