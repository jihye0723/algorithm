import java.util.*;

public class Solution_BaekJoon_2504_괄호의값_실버3{
	static Stack<Character> stack = new Stack<>();
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		String s = sc.nextLine();
		int val = 1; 
		int answer=0; // 전체 값 
		boolean isFail = false;	
		for(int i=0; i<s.length(); i++) {
			char now = s.charAt(i);
			
			if(now=='(') {
				stack.push(now);
				val*=2; 
			}
			if(now=='[') {
				stack.push(now);
				val*=3; 
			}
			if(now==')') {
				if(stack.isEmpty()) {
					isFail = true;
					break;
				}
				char top = stack.pop();
				if(top != '(') {
					isFail = true; 
					break; 
				}
				//바로앞에가 열린 거였으면, 
				if(s.charAt(i-1)=='(') {
					answer+=val;
				}
				val/=2;
				
			}
			if(now==']') {
				if(stack.isEmpty()) {
					isFail = true;
					break;
				}
				char top = stack.pop();
				if(top != '[') {
					isFail = true; 
					break; 
				}
				//바로앞에가 열린 거였으면, 
				if(s.charAt(i-1)=='[') {
					answer+=val;
				}
				val/=3;
				
			}
			
		}
		if(isFail || !stack.isEmpty()) System.out.println(0);
		else System.out.println(answer);
	}
}// end of class
