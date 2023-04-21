package study_17;

import java.util.*;
import java.io.*;

public class BaekJoon_Solution_11279 {
	public static void main(String[] args) throws Exception{
		//수가 클수록 우선순위가 높은 큐 생성 
		PriorityQueue<Integer> que= new PriorityQueue<>((o1,o2)->o2-o1); 
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringBuilder sb= new StringBuilder(); 
		
		int N= Integer.parseInt(br.readLine()); 
		while(N-- >0) {
			int n = Integer.parseInt(br.readLine()); 
			
			//배열에서 가장 큰 값 출력하고 제거, 없다면 0 출력 
			if(n==0) {
				if(que.isEmpty()) sb.append(0).append("\n"); 
				else sb.append(que.poll()).append("\n"); 
			}
			
			//0이 아닌 자연수라면 추가 
			else {
				que.add(n); 
			}
		}
		
		sb.deleteCharAt(sb.length()-1); 
		
		System.out.println(sb);
		
	}//end of main 
}//end of class 
