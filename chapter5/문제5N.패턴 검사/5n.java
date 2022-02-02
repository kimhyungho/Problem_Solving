import java.lang.*;
import java.util.*;

class Main {
	public static final Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {
		String s = scanner.next();
		String p = scanner.next();
		
		for(int i = 0; i < s.length() - p.length(); i ++) {
			String d = s.substring(i, i + p.length());
			if(d.equals(p)) {
				System.out.println(i);
			}
		}
  }
}