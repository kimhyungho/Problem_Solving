import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		TreeSet<Integer> integers = new TreeSet<>();
		int oldSize = integers.size();
		
		for(int i = 0 ; i < N ; i++){
			// integers := 이전까지 등장한 모든 정수를 저장한 집합
			// X := 중복을 확인할 수.
			int X = scanner.nextInt();
			oldSize = integers.size();
			integers.add(X);
			
			if(oldSize == integers.size()) {
				writer.write("DUPLICATED\n");
			} else {
				writer.write("OK\n");
				oldSize = integers.size();
			}
		}

		writer.flush();
		writer.close();
	}

}