package study_17;

import java.util.*;
public class Solution_BaekJoon_1543 {
	 public static void main(String[] args) {
		Scanner sc= new Scanner(System.in); 
		
		String docu= sc.nextLine(); //문서 
		String word= sc.nextLine(); //찾으려는 단어 
		int count=0; 
		while(docu.length()>0) {
			if(docu.contains(word)) {
				count++;
				int next = docu.indexOf(word) + word.length(); 
				docu = docu.substring(next); 
			}
			else break; 
		}
		System.out.println(count);
	 }//end of main 
}
