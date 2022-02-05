import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int getMaximumRangeDifference(int n, int k, City[] cities){
		int answer = 0;

		// 소득이 가장 작은 도시부터 pop되는 우선순위 큐
		PriorityQueue<City> rangeMinimum = new PriorityQueue<>();

		// 소득이 가장 높은 도시부터 pop되는 우선순위 큐
		PriorityQueue<City> rangeMaximum = new PriorityQueue<>(Collections.reverseOrder());

		for(int i = 0; i < n - k + 1; i += 1) {
			for(int j = 0; j < k; j += 1) {
				rangeMaximum.add(cities[j]);
				rangeMinimum.add(cities[j]);
			}
			
			int maxIncome = rangeMaximum.poll().income;
			int minIncome = rangeMinimum.poll().income;
			rangeMaximum.clear();
			rangeMinimum.clear();
			
			if(maxIncome - minIncome > answer) {
				answer = maxIncome - minIncome;
			}
			
		}

		return answer;
	}

	public static void testCase(int caseIndex) {
		// n := 도시의 수
		int n = scanner.nextInt();
		// 인접한 비교할 도시의 수.
		int k = scanner.nextInt();
		City[] cities = new City[n];

		for(int i = 0 ; i < n ; i += 1){
			// 소득 수준.
			int income = scanner.nextInt();
			cities[i] = new City(i, income);
		}

		int answer = getMaximumRangeDifference(n, k, cities);

		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		// 테스트케이스의 수를 나타내는 10이하의 자연수 T
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}


class City implements  Comparable<City>{
	public final int index;     // 도시의 인덱스
	public final int income;    // 해당 도시의 소득

	public City(int index, int income){
		this.index = index;
		this.income = income;
	}

	@Override
	public int compareTo(City o) {
		// 소득에 대해 우선순위를 가지도록 대소관계를 정의해준다
		return this.income - o.income;
	}
}