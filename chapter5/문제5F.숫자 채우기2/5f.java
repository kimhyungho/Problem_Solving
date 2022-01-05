import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void testCase(int caseIndex) throws Exception {
		int N = scanner.nextInt();

		int[][] map = new int[N][N];

		Robot robot = new Robot();

		int index = 0;
		int lastIndex = N * N;
		int count1 = 0;
		int count2 = 1;
		int temp1 = 1;
		int temp2 = 1;
		
		while(index < lastIndex) {
			index += 1;
			
			Position2D currentPosition =  robot.getPosition();
			map[currentPosition.r][currentPosition.c] = index;
			
			if(index == temp1 + count1 * 2 + 1) {
				robot.turnNext();
				temp1 = index;
				count1 += 1;
			} else if(index == temp2 + count2 * 2) {
				robot.turnNext();
				temp2 = index;
				count2 += 1;
			} else if (index == (int) Math.sqrt(index) * (int) Math.sqrt(index) && index != 1) {
				robot.turnNext();
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
	public static final int[] deltaR = new int[]{0, 1, 0, 1, 0, -1};
	public static final int[] deltaC = new int[]{1, 0, -1, 0, 1, 0};

	private int direction;
	private Position2D position;

	public Robot() {
		this.position = new Position2D(0, 0);
		this.direction = 0;
	}

	public void goStraight() {
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