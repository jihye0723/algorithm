import java.util.*;
import java.io.*;
	
public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		int T= Integer.parseInt(br.readLine()); //테스트 케이스만큼 반복 
		
		while(T-- >0) {
			boolean isFirst =true; //앞쪽에서 시작하는지 (리버스 하면 뒤로 바뀜) 
			boolean isError= false; 
			String cmd = br.readLine(); // 명령 
			int n = Integer.parseInt(br.readLine()); //배열의 들어있는 수의 갯수 
			Deque<Integer> deque = new ArrayDeque<>(); 
			
			String s = br.readLine(); // [1,2,3,4] 형태로 주어지는 문자열 
			s= s.substring(1, s.length()-1); //앞뒤 괄호 빼기 
			
			s=s.replace(",", " ");
			st= new StringTokenizer(s, " "); 
			for(int i=0; i<n; i++) {
				deque.add(Integer.parseInt(st.nextToken())); 
			}
			
			for(int i=0; i<cmd.length(); i++) {
				char c = cmd.charAt(i); //명령 
				//뒤집기 
				if(c== 'R') {
					isFirst = !isFirst; 
				}
				//앞에 하나 제거하기 
				else {
					if(deque.isEmpty()) {
						sb.append("error").append("\n"); 
						isError= true; 
						break; 
					}
					
					//앞에꺼 제거하는 거면 
					if(isFirst) {
						deque.poll();
					}else {
						deque.pollLast(); 
					}
				}
			}
			
			if(isError) continue; 
			
			//앞에서 부터 읽는거면
			if(isFirst) {
				int size= deque.size(); 
				int arr[]= new int[size]; 
				for(int i=0; i<size; i++) {
					arr[i] = deque.pollFirst(); 
				}
				String s1 = Arrays.toString(arr); 
				sb.append(s1.replace(" ", "")); 
			}
			else {
				int size= deque.size(); 
				int arr[]= new int[size]; 
				for(int i=0; i<size; i++) {
					arr[i] = deque.pollLast(); 
				}
				String s1 = Arrays.toString(arr); 
				sb.append(s1.replace(" ", "")); 
			}
			
			sb.append("\n");
		}//end of while 
		System.out.println(sb.substring(0,sb.length()-1));
	}//end of main 
}//end of class 
