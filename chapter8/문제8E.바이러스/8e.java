import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAXIMUM_VIRUS = 10000;

	public static void testCase(int caseIndex) {
		// 목표 개체수를 나타내는 10만 이하의 자연수 N.
		int targetNumber = scanner.nextInt();

		// 거리 = 시간(초).
		int[] distance = new int[MAXIMUM_VIRUS + 1];
		// 방문 유무
		boolean[] visited = new boolean[MAXIMUM_VIRUS + 1];

		State initialState = new State(1, 1);
		Queue<State> queue = new LinkedList<>();
		queue.add(initialState);

		while (queue.isEmpty() == false) {
			State currentState = queue.poll();
			distance[currentState.numberOfVirus] = currentState.depth - 1;
			visited[currentState.numberOfVirus] = true;

			if(currentState.numberOfVirus == targetNumber) break;
			
			
			for(int next = 0; next < 2; next += 1) {
				State nextState = new State((currentState.numberOfVirus * 2) + next, currentState.depth + 1);
				queue.add(nextState);
			}
		}

		int answer = distance[targetNumber];
		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		// T := testCase의 수.
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class State {
	// 바이러스의 번호.
	public final int numberOfVirus;
	// 분열 횟수 = 분열 시간.
	public final int depth;

	public State(int numberOfVirus, int depth) {
		this.numberOfVirus = numberOfVirus;
		this.depth = depth;
	}
}