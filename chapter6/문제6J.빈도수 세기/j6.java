import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		// N개의 정수.
		int N = scanner.nextInt();

		// 각 <정수, 빈도수> 형태로 key-value를 저장할 Map 자료구조
		TreeMap<Integer, Integer> frequencyMap = new TreeMap<>();

		for(int i = 0 ; i < N; i+= 1){
			// frequencyMap := 이전에 입력된 정수들의 빈도수를 저장하고 있다.
			int X = scanner.nextInt();
			if(frequencyMap.get(X) == null) {
				frequencyMap.put(X, 1);
			} else {
				frequencyMap.put(X, frequencyMap.get(X) + 1);
			}
			
			// System.out.printf("%d %d\n", frequencyMap.size(), frequencyMap.get(X));
			writer.write(frequencyMap.size() + " " + frequencyMap.get(X) + "\n");
			
		}
		
		for(int i = 0; i < N; i += 1)

		writer.flush();
		writer.close();
	}

}