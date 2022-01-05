import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < 4; i += 1) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			points[i] = new Point2D(x, y);
		}

		Rectangle A = new Rectangle(points[0], points[1]);
		Rectangle B = new Rectangle(points[2], points[3]);

		Rectangle C = A.getIntersection(B);

		if(C == null){
			System.out.println("0");
		}else{
			System.out.println(C.getArea());
		}
	}

	public static void main(String[] args) throws Exception {
		// 테스트케이스의 수.
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Rectangle {
	public final int leftX;
	public final int rightX;
	public final int bottomY;
	public final int topY;

	public Rectangle(int leftX, int rightX, int bottomY, int topY) {
		this.leftX = leftX;
		this.rightX = rightX;
		this.bottomY = bottomY;
		this.topY = topY;
	}

	public Rectangle(Point2D pa, Point2D pb) {
		this.leftX = Math.min(pa.x, pb.x);
		this.rightX = Math.max(pa.x, pb.x);
		this.bottomY = Math.min(pa.y, pb.y);
		this.topY = Math.max(pa.y, pb.y);
	}

	public int getWidth() {
		// 너비
		return rightX - leftX;
	}

	public int getHeight() {
		// 높이
		return topY - bottomY;
	}

	public long getArea() {
		// 넓이
		return (long) getWidth() * getHeight();
	}

	public boolean isIntersected(Rectangle other){
		Rectangle intersection = getIntersection(other);
		if(intersection == null){
			return false;
		}
		return true;
	}

	public Rectangle getIntersection(Rectangle other){	
		int rX = Math.min(this.rightX, other.rightX);
		int tY = Math.min(this.topY, other.topY);
		int lX = -1;
		int bY = -1;
		
		for(int i = other.leftX; i <= other.rightX; i += 1){
			if( i >= this.leftX && i <= this.rightX) {
				lX = i;
				break;
			}
		}
		
		for(int j = other.bottomY; j <= other.topY; j += 1){
			if( j >= this.bottomY && j <= this.topY) {
				bY = j;
				break;
			}
		}
		
		if(lX >= 0 && bY >= 0 && rX != lX && tY != bY) {
			return new Rectangle(lX, rX, bY, tY);
		} else {
			return null;
		}
		
	}
}

class Point2D {
	public final int x;
	public final int y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
}