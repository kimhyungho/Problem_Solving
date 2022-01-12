import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int[] deltaX = new int[]{ 0, 0, -1, 1 };
	public static final int[] deltaY = new int[]{ -1, 1, 0, 0 };

	public static long getTotalLength(int N, int K, int initR, int initC, int[] lengths, int[] directions) {
		Segment leftBound = new Segment(new Point2D(0,0), new Point2D(0, N+1));
		Segment rightBound = new Segment(new Point2D(N+1, 0), new Point2D(N+1, N+1));
		Segment topBound = new Segment(new Point2D(0,0), new Point2D(N+1, 0));
		Segment bottomBound = new Segment(new Point2D(0, N+1), new Point2D(N+1, N+1));
		Segment startingPoint = new Segment( new Point2D(initC, initR) , new Point2D(initC, initR) );

		ArrayList<Segment> segments = new ArrayList<Segment>();
		segments.add( leftBound );
		segments.add( rightBound );
		segments.add( topBound );
		segments.add( bottomBound );
		segments.add( startingPoint );

		Segment lastMove = startingPoint;

		for(int i = 0 ; i < K ; i += 1) {
			int D = directions[i];
			int L = lengths[i];
			Point2D origin = segments.get( segments.size()-1 ).dest;
			Point2D dest = new Point2D( origin.x + L*deltaX[D] , origin.y + L*deltaY[D] );

			Segment expectedMove = new Segment( origin,  dest );
			boolean intersected = false;
			int movableLength = L;

			for(Segment segment : segments) {
				if(segment != lastMove && expectedMove.isIntersectedTo(segment)) {
					movableLength = 0;
					
					for(int j = 1; j <= L; j += 1) {
						movableLength = j;
						Point2D movableDest = new Point2D(origin.x + movableLength*deltaX[D] , origin.y + movableLength*deltaY[D]);
						Segment movableSegment = new Segment(origin, movableDest);
						if(movableSegment.isIntersectedTo(segment)) {
							intersected = true;
							movableLength -= 1;
							break;
						}
					}
					if(intersected) break;
				}
			}

			dest = new Point2D(origin.x + movableLength*deltaX[D] , origin.y + movableLength*deltaY[D]);
			expectedMove = new Segment(origin, dest);

			lastMove = expectedMove;
			segments.add(expectedMove);

			if(intersected){
				break;
			}
		}

		long answer = 1;
		for(int i = 4; i < segments.size(); i+= 1){
			Segment s = segments.get(i);
			answer += s.length();
		}
		return answer;
	}


	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		int initRow = scanner.nextInt();
		int initCol = scanner.nextInt();

		int[] lengths = new int[K];
		int[] directions = new int[K];
		for(int i = 0 ; i < K ; i += 1){
			directions[i] = scanner.nextInt() - 1;
			lengths[i] = scanner.nextInt();
		}

		long answer = getTotalLength(N, K, initRow, initCol, lengths, directions);

		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Segment {
	public static final int DIRECTION_UP = 1;
	public static final int DIRECTION_DOWN = -1;
	public static final int DIRECTION_LEFT = -2;
	public static final int DIRECTION_RIGHT = 2;
	public static final int DIRECTION_NONE = 0;

	public final Point2D origin;
	public final Point2D dest;
	public final int directionType;

	public Segment(Point2D origin, Point2D dest){
		this.origin = origin;
		this.dest = dest;
		if(origin.y < dest.y){
			this.directionType = DIRECTION_UP;
		}else if(origin.y > dest.y){
			this.directionType = DIRECTION_DOWN;
		}else if(origin.x < dest.x){
			this.directionType = DIRECTION_RIGHT;
		}else if(origin.x > dest.x){
			this.directionType = DIRECTION_LEFT;
		}else{
			this.directionType = DIRECTION_NONE;
		}
	}

	public long length(){
		if( directionType == DIRECTION_LEFT || directionType ==  DIRECTION_RIGHT){
			return Math.abs( getRightX() - getLeftX() );
		}else{
			return Math.abs( getTopY() - getBottomY() );
		}
	}

	public int getLeftX(){
		return Math.min(origin.x, dest.x);
	}

	public int getRightX(){
		return Math.max(origin.x, dest.x);
	}

	public int getTopY(){
		return Math.max(origin.y, dest.y);
	}

	public int getBottomY(){
		return Math.min(origin.y, dest.y);
	}

	public boolean isIntersectedTo(Segment other){
		int crossLeftX = Math.max(this.getLeftX(), other.getLeftX());
		int crossRightX = Math.min(this.getRightX(), other.getRightX());
		int crossBottomY = Math.max(this.getBottomY(), other.getBottomY());
		int crossTopY = Math.min(this.getTopY(), other.getTopY());

		if(crossLeftX > crossRightX || crossBottomY > crossTopY){
			return false;
		}
		return true;
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