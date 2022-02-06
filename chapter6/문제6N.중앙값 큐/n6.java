import java.io.*;
import java.lang.*;
import java.util.*;

class MedianQueue<T extends Comparable<T>> {
	private PriorityQueue<T> minHeap;
	private PriorityQueue<T> maxHeap;
	
	// 생성자
	public MedianQueue() {
		this.minHeap = new PriorityQueue<>();
		this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	}
	
	public int size() {
		return minHeap.size() + maxHeap.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * 새 원소를 큐에 추가한다.
	 *
	 * @param value
	 */
	public void push(T value) {
		// maxHeap + minHeap
		if (maxHeap.isEmpty() == false && value.compareTo(maxHeap.peek()) > 0) minHeap.add(value);
		else maxHeap.add(value);
		
		if(maxHeap.size() > minHeap.size() + 1) minHeap.add(maxHeap.poll());
		if(maxHeap.size() < minHeap.size()) maxHeap.add(minHeap.poll());
	}
	
	/**
	 * 현재 중앙값인 원소를 반환한다.
	 *
	 * @return 중앙값 원소
	 * @throws NoSuchElementException 큐가 비어있는 경우
	 */
	public T peek() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		
		return maxHeap.peek();
	}
	
	/**
	 * 현재 중앙값인 원소를 pop하고 반환한다.
	 *
	 * @return 중앙값 원소
	 * @throws NoSuchElementException 큐가 비어있는 경우
	 */
	public T poll() throws NoSuchElementException {
		T value = maxHeap.poll();
		if(maxHeap.size() < minHeap.size()) maxHeap.add(minHeap.poll());
		
		return value;
	}
			

}

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		// N개의 명령어.
		int N = scanner.nextInt();
		
		MedianQueue<Integer> medianQueue = new MedianQueue<Integer>();
		
		for (int i = 0; i < N; i += 1) {
			// 명령어
			String cmd = scanner.next();
			try {
				// I X이면 중앙에 X를 삽입하고. 갱신된 중앙값을 한줄에 출력한다.
				if (cmd.equals("I")) {
					int value = scanner.nextInt();
					medianQueue.push(value);
					
					int median = medianQueue.peek();
					writer.write(String.format("%d\n", median));
				} else if (cmd.equals("P")) {
					// P일 경우 중앙 값 숫자를 삭제하고 갱신된 중앙값을 한줄에 출력
					medianQueue.poll();
					
					int median = medianQueue.peek();
					writer.write(String.format("%d\n", median));
				} else if (cmd.equals("S")) {
					// S일 경우 현재 큐에 저장된 원소의 수를 정수로 한줄에 출력.
					int size = medianQueue.size();
					writer.write(String.format("%d\n", size));
				}
			} catch (NoSuchElementException ex) {
				writer.write("ERROR\n");
			}
		}
		
		writer.close();
	}
	
}