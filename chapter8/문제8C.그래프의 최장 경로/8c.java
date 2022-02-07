import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final int NO_PARENT = -1;

	public static final Scanner scanner = new Scanner(System.in);


	/**
	 * 두 노드 current부터 goal까지의 최장 경로의 길이를 반환하는 함수
	 *
	 * @param current
	 * @param goal
	 * @param adj
	 * @return
	 */
	public static int getLongestPathLength(int current, int goal, ArrayList<Integer>[] adj){
		int N = adj.length;

		boolean[] visited = new boolean[N];

		return getLongestPathLength(current, goal, 1, adj, visited);
	}

	public static int getLongestPathLength(int current, int goal, int depth, ArrayList<Integer>[] adj, boolean[] visited){
		// 현재 위치가 목적지 이거나 or 
		if(visited[current] == true || current == goal) {
			return 0;
		}
		
		int maxLength = 0;
		
		visited[current] = true;
		// 경로를 모두 돈다.
		for(int next : adj[current]) {
			
			int length = 1 + getLongestPathLength(next, goal, depth + 1, adj, visited);
			maxLength = Math.max(maxLength, length);
		}
		
		visited[current] = false;
		return maxLength;
	}


	public static void main(String[] args) throws Exception {
		// N := 정점의 개수.
		int N = scanner.nextInt();
		// M := 간선의 수.
		int M = scanner.nextInt();
		ArrayList<Integer>[] adj = new ArrayList[N+1];

		// orgin := 출발점
		int origin = scanner.nextInt();
		// dest := 목적지
		int dest = scanner.nextInt();

		for(int i = 1; i <= N; i += 1){
			adj[i] = new ArrayList<>();
		}

		// u와 v 사이의 양방향 간선
		for(int i = 0 ; i < M; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		int answer = getLongestPathLength(origin, dest, adj);

		System.out.println(answer);
	}

}