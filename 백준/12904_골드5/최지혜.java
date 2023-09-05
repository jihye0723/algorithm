import java.util.*;
import java.io.*;

/*
 * t에서 s로 만들기 
 * 1. 문자열의 마지막에서 A를 제거한다. 
 * 2. 문자열의 마지막에서 b를 제거하고 문자열을 뒤집는다. */
public class Main {
	static String t; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		StringTokenizer st; 
		
		String s= br.readLine();
		t= br.readLine(); 
		
		while(t.length() > s.length()) {
			char last = t.charAt(t.length()-1); 
			//만약 A라면 , A를 빼는 거 밖에 방법이 없음 
			if(last== 'A') DeleteLast(); 
				
			//만약 B라면, B를 빼고 뒤집는 거 밖에 방법이 없음 
			else {
				DeleteLast();
				Reverse(); 
			}
			
			if(s.equals(t)) {
				System.out.println(1);
				return; 
			}
		}
		System.out.println(0);
	}//end of main
	
	//마지막 뒤에 한 단어 빼기 
	private static void DeleteLast() {
		t= t.substring(0, t.length()-1); 
	}
	
	private static void Reverse() {
		String rev =""; 
		for(int i=t.length()-1; i>=0; i--) {
			rev += t.charAt(i); 
		}
		
		t=rev;
	}
}//end of class 
