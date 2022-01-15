import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();
		long timeA = 0; // A팀이 앞섰던 기반
		long timeB = 0; // B팀이 앞섰던 시간
		Timestamp[] logs = new Timestamp[N + 2];
		
		
		System.out.printf("%02d:%02d\n", timeA / 60, timeA % 60);
		System.out.printf("%02d:%02d\n", timeB / 60, timeB % 60);
	}
	
}


// 득점 기록 로그 클래스
class Timestamp {
	public final long timeInSeconds;    // 득점 시간 (초)
	public final int teamIndex;         // 특점 팀 번호
	
	public Timestamp(int teamIndex, String stringTimestamp) {
		this.teamIndex = teamIndex;
		
		// 문자열으로 주어진 시간을 초단위로 환산한다
		String[] splited = stringTimestamp.split(":");
		int minutes = Integer.parseInt(splited[0]);
		int seconds = Integer.parseInt(splited[1]);
		this.timeInSeconds = minutes * 60 + seconds;
	}
	
	public Timestamp(int teamIndex, int minutes, int seconds) {
		this.teamIndex = teamIndex;
		this.timeInSeconds = minutes * 60 + seconds;
	}
	
	/**
	 * 두 로그 this와 next의 사이 시간을 초단위로 계산하는 메소드
	 *
	 * @param next
	 * @return
	 */
	public long getElapsedTimeTo(Timestamp next) {
		long dt = Math.abs(next.timeInSeconds - this.timeInSeconds);
		return dt;
	}
}