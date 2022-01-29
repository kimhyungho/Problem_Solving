import java.io.*;
import java.lang.*;
import java.util.*;

class Main {

	public static final Scanner scanner = new Scanner(System.in);
	
  public static void main(String[] args) {
		int n = scanner.nextInt();
		String whildWord = scanner.next();
		
		for(int i = 0; i < n; i += 1) {
 			Boolean isSame = true;


			String word = scanner.next();
			
			if(word.length() != whildWord.length()) {
				continue;
			}
			
			for(int index = 0; index < word.length(); index += 1) {
				if(whildWord.charAt(index) != word.charAt(index)) {
					if(whildWord.charAt(index) == '?'){
						continue;
					} else {
						isSame = false;
						break;
					}
				}
			}
			
			if(isSame == true) {
				System.out.println(word);
			}
		}
  }
}