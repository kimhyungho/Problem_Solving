import java.lang.*;
import java.util.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * 그래프의 두 노드 org에서 dest로 이동하는 가장 짧은 경로의 길이를 반환하는 함수
	 * 
	 * @param org
	 * @param dest
	 * @param adj
	 * @return
	 */
	public static int getShortestPathLength(int org, int dest, ArrayList<Integer>[] adj) {
		int N = adj.length;

		boolean[] visited = new boolean[N];
		int[] distance = new int[N];
		Arrays.fill(distance, -1);

		State initialState = new State(org, 1);
		Queue<State> bfsQueue = new LinkedList<>();
		bfsQueue.add(initialState);
		
		while(!bfsQueue.isEmpty()) {
			State currentState = bfsQueue.poll();
			
			distance[currentState.nodeIndex] = currentState.depth - 1;
			visited[currentState.nodeIndex] = true;
			
			if(currentState.nodeIndex == dest) break;
			
			for(int next : adj[currentState.nodeIndex]) {
				if(visited[next] == true) continue;
				
				bfsQueue.add(new State(next, currentState.depth + 1));
			}
		}


		return distance[dest];
	}

	public static void main(String[] args) throws Exception {
		// N := 정점의 개수.
		int N = scanner.nextInt();
		// M := 간선의 수.
		int M = scanner.nextInt();

		// origin := 출발점
		int origin = scanner.nextInt();
		// dest := 목적 점.
		int dest = scanner.nextInt();

		ArrayList<Integer>[] adj = new ArrayList[N + 1];


		for (int i = 1; i <= N; i += 1) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i += 1) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		int answer = getShortestPathLength(origin, dest, adj);

		System.out.println(answer);
	}

}

class State {
	public final int nodeIndex;
	public final int depth;

	public State(int nodeIndex, int depth) {
		this.nodeIndex = nodeIndex;
		this.depth = depth;
	}
}