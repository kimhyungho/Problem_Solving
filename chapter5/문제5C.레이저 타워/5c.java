import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	// 여덟 방향에 대해 행/열 방향으로의 변량을 정의한다.
	public static final int[] deltaR = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static final int[] deltaC = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	
	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		
		// 지도를 초기화하고 각 칸의 정보를 입력받는다.
		GameMap gameMap = new GameMap(N, N);
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				int buildings = scanner.nextInt();
				gameMap.setBuildingsAt(r, c, buildings);
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i < M; i += 1) {
			// 총 M개의 후보 중점 (centerR, centerC)에 대해 
			int centerR = scanner.nextInt() - 1;
			int centerC = scanner.nextInt() - 1;
			
			// 방어할 수 있는 건물의 수를 계산하자 
			int buildings = 0;
			
			// 중점위에 존재하는 칸은 방어할 수 있다.
			buildings += gameMap.getBuildingsAt(centerR, centerC);
			
			for (int di = 0; di < deltaR.length; di += 1) {
				// 총 8가지 방향에 대하여
				
				for(int length = 1 ; length <= N; length += 1) {
					int r = centerR + deltaR[di] * length;
					int c = centerC + deltaC[di] * length;
					if(gameMap.isInside(r, c) && gameMap.getBuildingsAt(r, c) == 1) {
						buildings += 1;
					}
				}
			}
			
			// 최대값을 갱신한다
			answer = Math.max(answer, buildings);
		}
		
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();
		
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
	
}

class GameMap {
	public final int rows;              // 총 열 수
	public final int columns;           // 총 행 수
	private final int[][] buildings;    // buildings[r][c] : = (r, c)칸에 존재하는 건물의 수
	
	/**
	 * (row x columns) 크기의 지도를 초기화한다.
	 *
	 * @param rows
	 * @param columns
	 */
	public GameMap(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.buildings = new int[rows][columns];
	}
	
	/**
	 * (r, c)칸에 존재하는 건물의 수를 반환해준다.
	 *
	 * @param r
	 * @param c
	 * @return 해당 칸에 존재하는 건물의 수
	 */
	public int getBuildingsAt(int r, int c) {
		if (this.isInside(r, c) == false)
			return 0;
		
		return this.buildings[r][c];
	}
	
	/**
	 * (r, c)칸에 존재하는 건물의 수를 변경해준다.
	 *
	 * @param r
	 * @param c
	 * @param value
	 */
	public void setBuildingsAt(int r, int c, int value) {
		this.buildings[r][c] = value;
	}
	
	/**
	 * (r, c)칸이 지도에 존재하는 칸인지 검사한다.
	 *
	 * @param r
	 * @param c
	 * @return 유효한 칸인지 여부
	 */
	public boolean isInside(int r, int c) {
		if (r < 0 || r >= this.rows)
			return false;
		if (c < 0 || c >= this.columns)
			return false;
		return true;
	}	
}