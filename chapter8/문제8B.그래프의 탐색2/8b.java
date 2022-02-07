import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * @param N     그래프의 정점의 수
	 * @param adj   각 노드들에 대한 인접 리스트의 배열
	 * @return      깊이 우선 탐색으로 탐색된 노드들의 번호 리스트
	 */
	public static ArrayList<Integer> getDfsOrder(int N, ArrayList<Integer>[] adj){
		ArrayList<Integer> visitedNodes = new ArrayList<>();

		Stack<State> dfsStack = new Stack<>();
		State initialState = new State(1, 1);
		dfsStack.add(initialState);

		boolean visited[] = new boolean[N+1];

		while(dfsStack.isEmpty() == false){
			// 현재 정점.
			State state = dfsStack.pop();
			
			if(visited[state.nodeIndex] == true){
				continue;
			}
			
			visited[state.nodeIndex] = true;
			visitedNodes.add(state.nodeIndex);
			
			// 뒤에서부터 넣어야됨.!!
			for( int i = adj[state.nodeIndex].size() - 1; i >= 0; i -= 1) {
				if(visited[adj[state.nodeIndex].get(i)] == false) {
					State nextState = new State(adj[state.nodeIndex].get(i), state.depth + 1);
					dfsStack.push(nextState);
				}
			}
			
		}

		return visitedNodes;
	}

	/**
	 * @param N     그래프의 정점의 수
	 * @param adj   각 노드들에 대한 인접 리스트의 배열
	 * @return      너비 우선 탐색으로 탐색된 노드들의 번호 리스트
	 */
	public static ArrayList<Integer> getBfsOrder(int N, ArrayList<Integer>[] adj){
		ArrayList<Integer> visitedNodes = new ArrayList<>();

		Queue<State> bfsQueue = new LinkedList<>();
		State initialState = new State(1, 1);
		bfsQueue.add(initialState);

		boolean visited[] = new boolean[N+1];

		while(bfsQueue.isEmpty() == false){
			State state = bfsQueue.poll();
			
			if(visited[state.nodeIndex] == true){
				continue;
			}
			
			visited[state.nodeIndex] = true;
			visitedNodes.add(state.nodeIndex);
			
			for(int index : adj[state.nodeIndex]) {
				if(visited[index] == false) {
					State nextState = new State(index, state.depth + 1);
					bfsQueue.add(nextState);
				}
			}
		}

		return visitedNodes;
	}

	public static void main(String[] args){
		// 정점의 개수.
		int N = scanner.nextInt();
		// 그래프 간선의 수.
		int M = scanner.nextInt();
		
		// 리스트의 배열
		ArrayList<Integer>[] adj = new ArrayList[N+1];

		for(int i = 1; i <= N; i += 1){
			// 정점들의 관계들을 저장할 리스트로 초기화
			adj[i] = new ArrayList<>();
		}

		for(int i = 0 ; i < M; i += 1){
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		for(int i = 1; i <= N; i+= 1){
			Collections.sort(adj[i]);
		}

		ArrayList<Integer> dfsOrders = getDfsOrder(N, adj);
		ArrayList<Integer> bfsOrders = getBfsOrder(N, adj);

		printArrayList(dfsOrders);
		printArrayList(bfsOrders);
	}

	public static void printArrayList(ArrayList<Integer> arr){
		for(int i = 0 ; i < arr.size(); i+= 1){
			if( i > 0 ){
				System.out.print("-");
			}

			int node = arr.get(i);
			System.out.print(node);
		}
		System.out.println();
	}
}


class State{
	public final int nodeIndex;
	public final int depth;
	public State(int nodeIndex, int depth){
		this.nodeIndex = nodeIndex;
		this.depth = depth;
	}
}