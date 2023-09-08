import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		StringTokenizer st; 
		
		String s= br.readLine();
		String t= br.readLine();
		 
		System.out.println(bfs(s, t)); 
		
	}
	
	private static int bfs(String s, String t) {
		Queue<String> que= new LinkedList<>();
		que.add(t); 
		
		while(!que.isEmpty()) {
			String find = que.poll();
//			System.out.println(find);
//			System.out.println(find);
			if(find.equals(s)) return 1; 
			
			if(find.length() < s.length()) return 0; 
			
			//제일 마지막 글자가 a여야 지만 A를 뺄 수 있다. 
			if(find.charAt(find.length()-1) == 'A') 
				que.add(case1(find));
			if(find.charAt(0) == 'B')
				que.add(case2(find)); 
			
		}
		
		return 0; 
	}
	
	private static String case1(String s) {
		s = s.substring(0, s.length()-1); 
		return s; 
	}
	
	private static String case2(String s) {
		String rev = "";
		for(int i=s.length()-1; i>=0; i--) {
			rev += s.charAt(i); 
		}
		
		return rev.substring(0, rev.length()-1); 
	}
}//end of class 
