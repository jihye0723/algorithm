//import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int T= Integer.parseInt(br.readLine()); 
		while(T-- >0) {
			String s= br.readLine(); 
			int res= check(s, 0, s.length()-1); 
			System.out.println(res);
		}
		
	}//end of main
	
	/*
	 * 0:화문 
	 * 1:유사화문
	 * 2:그 외/
	 */
	private static int check(String s, int left, int right) {
		while(left<right) {
			
			if(s.charAt(left)==s.charAt(right)) {
				left++;
				right--; 
			}
			
			//만약 다를 경우 ,
			else {
				//왼쪽 하나 제외/ 오른쪽 하나 제외하고 회문인지 확인 
				boolean res1 = checkSec(s, left+1, right);
				boolean res2 = checkSec(s, left, right-1); 
				
				if(res1 || res2) return 1;
				
				return 2;
			}
		}
		
		return 0;
	}
	private static boolean checkSec (String s, int left, int right) {
		while(left< right) {
			if(s.charAt(left) == s.charAt(right)) {
				left++;
				right--; 
				continue; 
			}
			
			return false; 
		}
		return true; 
	}
}//end of class 
