import java.io.*;
import java.lang.*;
import java.util.*;

class DualPriorityQueue {
	private PriorityQueue<Element> minHeap;
	private PriorityQueue<Element> maxHeap;
	private int aliveElements;  // 현재 저장된 원소의 수
	
	// 생성자
	public DualPriorityQueue() {
		this.minHeap = new PriorityQueue<>();
		this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		this.aliveElements = 0;
	}
	
	// 현재 남은 원소의 수
	public int size() {
		return this.aliveElements;
	}
	
	/**
	 * 정수 데이터 하나를 추가하는 메소드
	 *
	 * @param value
	 */
	public void push(int value) {
		Element newElement = new Element(value);
		minHeap.add(newElement);
		maxHeap.add(newElement);
		
		this.aliveElements += 1;
	}
	
	/**
	 * @return 현재 저장된 원소들 중 최소값 반환
	 *
	 * @throws NoSuchElementException
	 */
	public int getMinimum() throws NoSuchElementException {
		return this.minHeap.peek().value;
	}
	
	/**
	 * @return 현재 저장된 원소들 중 최대값 반환
	 *
	 * @throws NoSuchElementException
	 */
	public int getMaximum() throws NoSuchElementException {
		return this.maxHeap.peek().value;
	}
	
	/**
	 * 현재 저장된 최소값 원소를 삭제/반환하는 메소드
	 *
	 * @return 최소값
	 *
	 * @throws NoSuchElementException
	 */
	public int popMinimum() throws NoSuchElementException {
		this.aliveElements -= 1;
		return minHeap.poll().value;
	}
	
	/**
	 * 현재 저장된 최대값 원소를 삭제/반환하는 메소드
	 *
	 * @return 최대값
	 *
	 * @throws NoSuchElementException
	 */
	public int popMaximum() throws NoSuchElementException {
		this.aliveElements -= 1;
		return maxHeap.poll().value;
	}
	

	
	/**
	 * 내부에 데이터를 저장할 객체
	 */
	private class Element implements Comparable<Element> {
		private int value;  // 해당 데이터의 실제 값
		private boolean deleted;    // 이미 pop된 객체인지 여부
		
		public Element(int value) {
			this.value = value;
			this.deleted = false;
		}
		
		@Override
		public int compareTo(Element other) {
			return this.value - other.value;
		}
	}
}

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	
	public static final int POP_MINIMUM = -1;
	public static final int POP_MAXIMUM = 1;
	
	public static void testCase(int caseIndex) throws Exception {
		int N = scanner.nextInt();
		
		DualPriorityQueue queue = new DualPriorityQueue();
		
		for (int i = 0; i < N; i += 1) {
			String cmd = scanner.next();
			int value = scanner.nextInt();
			
			if (cmd.equals("I")) {
				queue.push(value);
			} else if (cmd.equals("D")) {
				if (queue.size() == 0) {
					continue;
				}
				
				if (value == POP_MINIMUM) {
					queue.popMinimum();
				} else if (value == POP_MAXIMUM) {
					queue.popMaximum();
				}
			}
		}
		
		if (queue.size() == 0) {
			System.out.println("EMPTY");
		} else {
			int minimum = queue.getMinimum();
			int maximum = queue.getMaximum();
			System.out.printf("%d %d\n", maximum, minimum);
		}
	}
	
	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}