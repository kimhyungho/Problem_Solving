import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static final int EMPTY = -1;
	// 익지 않은 토마토.
	public static final int UNRIPE = 0;
	// 익은 토마토.
	public static final int RIPE = 1;
	
	public static final int[] dirR = {0, 1, 0, -1};
	public static final int[] dirC = {1, 0, -1, 0};

	public static int getMinimumRequiredDays(int R, int C, int[][] tomato){
		boolean[][] visited = new boolean[R+2][C+2];
		int[][] distance = new int[R+2][C+2];

		Queue<State> bfsQueue = new LinkedList<>();
		for(int i = 1; i <= R; i += 1){
			for(int j = 1; j <= C; j += 1) {
				if(tomato[i][j] == RIPE){
					State state = new State(i, j, 1);
					bfsQueue.add(state);
				}
			}
		}

		while(!bfsQueue.isEmpty()){
			State current = bfsQueue.poll();
			
			if(visited[current.row][current.col] == true) continue;
			visited[current.row][current.col] = true;
			tomato[current.row][current.col] = RIPE;
			distance[current.row][current.col] = current.depth - 1;
			
			for(int i = 0; i < 4; i += 1) {
				int nextR = current.row + dirR[i];
				int nextC = current.col + dirC[i];
				
				if(nextR >= 1 && nextC >= 1 && nextR <= R && nextC <= C && tomato[nextR][nextC] != EMPTY) {
					State nextState = new State(nextR, nextC, current.depth + 1);
					bfsQueue.add(nextState);				
				}
			}
		}


		int unripeCount = 0;
		int requiredDays = 0;
		for(int i = 1; i <= R; i += 1){
			for(int j = 1; j <= C; j += 1){
				if(tomato[i][j] == EMPTY){
					continue;
				}
				if(visited[i][j] == false){
					unripeCount += 1;
					break;
				}else{ // tomato[i][j] == RIPE
					requiredDays = Math.max(requiredDays, distance[i][j]);
				}
			}
		}

		if(unripeCount >= 1){
			return -1;
		}else{
			return requiredDays;
		}
	}

	public static void main(String[] args) throws Exception {
		// 상자의 가로 크기.
		int C = scanner.nextInt();
		// 상자의 세로 크기
		int R = scanner.nextInt();

		int[][] tomato = new int[R+2][C+2];
		for(int i = 0 ; i < R + 2 ; i += 1){
			// EMPTY := -1
			Arrays.fill(tomato[i], EMPTY);
		}

		for(int i = 1; i <= R; i += 1){
			for(int j = 1; j <= C ; j += 1){
				// 토마토들의 정보. 
				tomato[i][j] = scanner.nextInt();
			}
		}

		int answer = getMinimumRequiredDays(R, C, tomato);

		System.out.println(answer);
	}

}
class State {
	public final int row;
	public final int col;
	public final int depth;

	public State(int row, int col, int depth) {
		this.row = row;
		this.col = col;
		this.depth = depth;
	}
}