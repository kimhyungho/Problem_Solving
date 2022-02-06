import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		// 한 번 이상 등장한 적 있는 후보들의 이름
		ArrayList<String> existingNames = new ArrayList<>();

		// 각 후보 이름과 득표 수를 저장하는 key-value Map
		TreeMap<String, Integer> frequencyMap = new TreeMap<>();
		
		// 가장 많은 표를 받은 후보들의 이름
		ArrayList<String> winnersList = new ArrayList<>();

		int maxFrequency = 0;   // 가장 많은 득표수

		for(int i = 0; i < N; i += 1) {
			String name = scanner.next();
			if(frequencyMap.get(name) == null) {
				existingNames.add(name);
				frequencyMap.put(name, 1);
			} else {
				frequencyMap.put(name, frequencyMap.get(name) + 1);
				if(frequencyMap.get(name) > maxFrequency) maxFrequency = frequencyMap.get(name);
			}
		}
		
		for(String name : existingNames) {
			if(frequencyMap.get(name) == maxFrequency) {
				winnersList.add(name);
			}
		}
		
		

		// 최대 득표수를 출력한다
		writer.write(String.format("%d\n", maxFrequency));

		// 최대 득표를한 동점 후보들 이름을 사전순으로 출력한다.
		Collections.sort(winnersList);
		for (int i = 0; i < winnersList.size(); i += 1) {
			if (i > 0) {
				writer.write(" ");
			}
			String name = winnersList.get(i);
			writer.write(name);
		}

		writer.flush();
		writer.close();
	}

}