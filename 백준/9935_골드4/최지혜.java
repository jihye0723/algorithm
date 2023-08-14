import java.util.*;
import java.io.*;
/*
 * replace로 했떠니 메모리 초과 ! 
 * stack */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		String s =br.readLine(); 
		String bomb= br.readLine(); //폭발 문자열 
		
		char start= bomb.charAt(0); //폭발 문자열의 첫 문자 
		
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<s.length(); i++) {
			stack.push(s.charAt(i));
	
			//폭발문자열 길이보다 스택크기가 더 크다면, 스택에 폭발문자열 포함되어있는지 확인
			if(stack.size() >= bomb.length()) {
				boolean flag = true; //폭발문자열이 포함되어있는지 확인 여부 
				for(int j=0; j<bomb.length(); j++) {
					//폭발문자열 포함 x 
					if(bomb.charAt(j) != stack.get(stack.size() - bomb.length() + j)) {
						flag =false;
						break; 
					}
				}
				
				//폭발문자열이 포함되어 있을 경우, 
				if(flag) {
					for(int j=0; j<bomb.length(); j++) {
						stack.pop(); //폭발문자열 길이만큼 pop 
					}
				}
			}
			
		
		}
		
		if(stack.size()==0) System.out.println("FRULA");
		
		else {
			StringBuilder sb= new StringBuilder();
			for(int i=0; i<stack.size(); i++) {
				sb.append(stack.get(i)); 
			}
			System.out.println(sb);
		}
		

	}//end of main
}//end of class 
