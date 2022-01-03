import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {

   // TO두 Retry

	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void testCase(int caseIndex) throws Exception {
		int N = scanner.nextInt();

		int[][] map = new int[N][N];

		Robot robot = new Robot();

		int index = 1;
		int lastIndex = N * N;
		
		int count1 = 1;
		int count2 = 1;
		int count3 = 1;
		
		
		while(index <= lastIndex) {
			// 로봇으로 맵을 채워줌

			Position2D position = robot.getPosition();
			map[position.r][position.c] = index;
						
			index += 1;

			if(index == Math.sqrt(index) * Math.sqrt(index) ) {
				robot.turnNext();
			}
			
			if(index == count1 * 8 + 2 || index == 2) {
				robot.turnNext();
				
				if(index != 2) {
					count1 += 1;
				}
			}
			
			if(index == count2 * 8 + 4 || index == 5) {
				robot.turnNext();
				
				if(index != 5) {
					count2 += 1;
				}
			}
			
			if(index == count3 * 2 + 1 ) {
				robot.turnNext();
				count3 += 1;
			}
			
			robot.goStraight();
		}
		
		
		printMap(map, N);
	}

	public static void printMap(int[][] map, int N) throws Exception{
		for (int r = 0; r < N; r += 1) {
			for (int c = 0; c < N; c += 1) {
				if (c > 0) {
					writer.write(" ");
				}
				writer.write(String.valueOf(map[r][c]));
			}
			writer.write("\n");
		}
		writer.flush();
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}

		writer.flush();
		writer.close();
	}

}

class Robot {
	
	// 가로 r0 c1에서 1번   r1 c0 에서 n번
	public static final int[] deltaR = new int[]{0, 1, 0, 1, 0, -1};
	
	// 세로 r1 c0에서 1번  r0 c1 에서 n번
	public static final int[] deltaC = new int[]{1, 0, -1, 0, 1, 0};

	private int direction;
	private Position2D position;

	public Robot() {
		this.position = new Position2D(0, 0);
		this.direction = 0;
	}

	public void goStraight() {
		// 다이렉션은 그대로.
		this.position = getNextPosition();
	}

	public Position2D getNextPosition() {
		
		int newR = position.r + deltaR[direction];
		int newC = position.c + deltaC[direction];

		Position2D newPosition = new Position2D(newR, newC);
		return newPosition;
	}

	public Position2D getPosition() {
		return this.position;
	}

	public void turnNext(){
		// 방향 바꿈.
		this.direction = (this.direction + 1) % deltaC.length;
	}
}

class Position2D {
	public final int r;
	public final int c;

	public Position2D(int r, int c) {
		this.r = r;
		this.c = c;
	}
}