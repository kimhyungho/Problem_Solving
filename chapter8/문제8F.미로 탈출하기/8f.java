import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	public static int[] dirR = {0, 1, 0, -1};
	public static int[] dirC = {1, 0, -1, 0};

	public static int getShortestPathLength(int originR, int originC, int destR, int destC, int R, int C, boolean[][] passable) {
		boolean[][] visited = new boolean[R + 2][C + 2];
		int[][] distance = new int[R + 2][C + 2];

		Queue<State> bfsQueue = new LinkedList<>();
		State initialState = new State(originR, originC, 1);
		bfsQueue.add(initialState);

		while (!bfsQueue.isEmpty()) {
			State current = bfsQueue.poll();
			
			if(passable[current.row][current.col] == false) continue;
			visited[current.row][current.col] = true;
			distance[current.row][current.col] = current.depth - 1;
			
			if(current.row == destR && current.col == destC) break;
			
			for(int next = 0; next < 4; next += 1) {
				State nextState = new State(current.row + dirR[next], current.col + dirC[next], current.depth + 1);
				bfsQueue.add(nextState);
			}
			
		}

		if(visited[destR][destC] == false){
			return -1;
		}
		return distance[destR][destC];
	}

	public static void main(String[] args) throws Exception {
		// 지도의크기 R := 행의 수.
		int R = scanner.nextInt();
		// C := 열의 수.
		int C = scanner.nextInt();

		int originR = -1;
		int originC = -1;
		int destR = -1;
		int destC = -1;

		boolean[][] passable = new boolean[R + 2][C + 2];

		for (int i = 1; i <= R; i += 1) {
			// 각 행에대한 정보.
			String line = scanner.next();
			for (int j = 1; j <= C; j += 1) {
				// 입력을 받는다.
				char c = line.charAt(j - 1);
				
				if (c != '#') {
					// 벽이 아니라면 이동가능
					passable[i][j] = true;
				}
				// 만약 초기위치라면 위치 설정.
				if (c == 'S') {
					originR = i;
					originC = j;
				} else if (c == 'E') {
					// 미로의 탈출구
					destR = i;
					destC = j;
				}				
			}
		}

		int answer = getShortestPathLength(originR, originC, destR, destC, R, C, passable);

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