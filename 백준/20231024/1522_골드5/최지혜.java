import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int min = Integer.MAX_VALUE; 
		String s= br.readLine();
		int a_cnt =0; //a의 갯수
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == 'a') a_cnt ++; 
		}
		
		for(int i=0; i<s.length(); i++) {
			int b_cnt =0; 
			for(int j=i; j<i+a_cnt; j++) {
				if(s.charAt(j%s.length()) =='b') b_cnt ++; 
			}
			
			min = Math.min(min, b_cnt); 
		}
		System.out.println(min);
		
	}//end of main
}//end of class 
