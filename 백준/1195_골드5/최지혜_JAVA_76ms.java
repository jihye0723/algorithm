import java.util.*;
import java.io.*;
	
public class Main {
	
	static int min; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String up = br.readLine();
		String down = br.readLine(); 
		min = up.length()+down.length();
		makeMax(up,down);
		makeMax(down, up);
		System.out.println(min);
		
	}//end of main 
	private static void makeMax(String s1, String s2) {
		int start=0; 
		int index; 
		while(start < s1.length()) {
			boolean isok = true; //안될 경우 false 
			index= start; 
			
			for(int i=0; i<s2.length(); i++) {
				
				if(index >= s1.length()) break; 
				
				//둘다 이일 경우, 안됨 ! 
				if(s1.charAt(index) =='2' && s2.charAt(i)=='2') {
					isok = false; 
					break; 
				}
				
				index++; 
				
			}
			
			//두개를 모두 맞물린 경우, 
			if(isok) {
				int length = (s1.length()-start >= s2.length())? s1.length() : s2.length()+start; 
//				System.out.println(start+"일때 가능, "+"길이는 "+length);
				min = Math.min(min, length);
			}
			start++; 
		}
	}
}//end of class 
