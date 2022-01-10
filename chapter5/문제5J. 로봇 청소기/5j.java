import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static boolean isPossible(boolean[][] visited, int N, Position2D pos) {
		if(pos.r < 0 || pos.r >= N){
			return false;
		}
		if(pos.c < 0 || pos.c >= N){
			return false;
		}

		if(visited[pos.r][pos.c] == true){
			return false;
		}
		return true;
	}

	public static int getTotalLength(int N, int K, int initR, int initC, int[] lengths, int[] directions) {
		Robot robot = new Robot(initR, initC);
		boolean[][] visited = new boolean[N][N];
		boolean continued = true;

		visited[initR][initC] = true;
		
        // 첫 칸 카운트
		int count = 1;
		
		for(int i = 0; i < lengths.length; i += 1) {
            // 방향 설정
			robot.setCurrentDirection(directions[i]);
            // 중간에 멈추지 않는 다면 lenth만큼 이동.
			for(int j = 0; j < lengths[i]; j += 1) {
                // 다음 위치
				Position2D nextPosition = robot.getNextPosition();
				if(isPossible(visited, N, nextPosition)) {
					// 다음 위치가 이동가능하다면
                    // 직진, 방문 체크, 카운트 증가.
					robot.goStraight(1);
					int r = nextPosition.r;
					int c = nextPosition.c;
					visited[r][c] = true;
					count += 1;
				} else {
                    // 다음 위치가 불가능 하다면 컨티뉴 펄스
					continued = false;
                    break;
				}
			}
            // 컨티뉴가 펄스면 반복문 종료
			if(continued == false) {
				break;
			}
		}

        // 카운트 수 반환
		return count;
	}


	public static void testCase(int caseIndex) {
		// 정사각형의 가로세로 크기 1 ~ 100
		int N = scanner.nextInt();
		// 명령어의 개수 1 ~ 100
		int K = scanner.nextInt();
		
		// 초기 로봇청소기의 위치
		int initRow = scanner.nextInt() - 1;
		int initCol = scanner.nextInt() - 1;

		// 
		int[] lengths = new int[K];
		int[] directions = new int[K];
		for(int i = 0 ; i < K ; i += 1){
			// 어느 방향으로
			directions[i] = scanner.nextInt() - 1;
			// 몇 칸
			lengths[i] = scanner.nextInt();
		}

		int answer = getTotalLength(N, K, initRow, initCol, lengths, directions);

		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		// testCase수
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Robot{
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_SOUTH = 1;
	public static final int DIRECTION_WEST  = 2;
	public static final int DIRECTION_EAST  = 3;

	public static final int[] deltaRow = new int[]{ -1, 1, 0, 0 };
	public static final int[] deltaColumn = new int[]{0, 0, -1, 1 };

	private Position2D currentPosition;
	private int currentDirection;

	public Robot(int initRow, int initColumn){
		this.currentPosition = new Position2D(initRow, initColumn);
		this.currentDirection = 0;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

	public void goStraight(int length){
		int nextR = getCurrentRow() + length * deltaRow[getCurrentDirection()];
		int nextC = getCurrentColumn() + length * deltaColumn[getCurrentDirection()];
		this.currentPosition = new Position2D(nextR, nextC);
	}

	public Position2D getNextPosition(){
		int nextR = getCurrentRow() + deltaRow[getCurrentDirection()];
		int nextC = getCurrentColumn() + deltaColumn[getCurrentDirection()];
		return new Position2D(nextR, nextC);
	}

	public int getCurrentColumn() {
		return this.currentPosition.c;
	}

	public int getCurrentRow() {
		return this.currentPosition.r;
	}

	public Position2D getCurrentPosition() {
		return currentPosition;
	}
}

class Position2D{
	public final int r;
	public final int c;
	public Position2D(int r, int c){
		this.r = r;
		this.c = c;
	}
}