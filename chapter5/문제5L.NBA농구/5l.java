import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
		// 득점 수
		int N = scanner.nextInt();
		long timeA = 0; // A팀이 앞섰던 시간
		long timeB = 0; // B팀이 앞섰던 시간
		int scoreA = 0; // A팀 점수
		int scoreB = 0; // B팀 점수
		
		// 득점 시간과 득점 팀
		Timestamp[] logs = new Timestamp[N+2];
		
		// 0초와 48분 입력
		logs[0] = new Timestamp(1, "00:00");
		logs[N + 1] = new Timestamp(1, "48:00");
		
		for(int i = 1; i < N + 1; i += 1) {
			int T = scanner.nextInt();
			String time = scanner.next();
				
			logs[i] = new Timestamp(T, time);
		}
		
		// 첫 득점 부터 계산
		for(int i = 1; i < N + 1; i += 1) {
			// 점수 부터 계산
			if(logs[i].teamIndex == 1) {
				scoreA += 1;
			} else {
				scoreB += 1;
			}
			long time = logs[i].getElapsedTimeTo(logs[i+1]);

			if(scoreA > scoreB) {
				timeA += time;
			}else if(scoreB > scoreA) {
				timeB += time;
			}	
		}

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