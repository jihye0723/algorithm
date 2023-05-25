package study_17;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_2252 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<ArrayList<Integer>> list = new ArrayList<>(); 
		
		st= new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken()); //학생 수 
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		int[] inCnt = new int[N+1]; //각 학생들 보다 더 키가 작은 학생수 (=진입차수) 
 		int M= Integer.parseInt(st.nextToken()); //키 비교 횟수 
		while(M-- >0) {
			st= new StringTokenizer(br.readLine(), " ");
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			list.get(a).add(b); 
			inCnt[b]++; 
		}
		
		Queue<Integer> que = new LinkedList<>(); 
		for(int i=1; i<=N; i++) {
			if(inCnt[i]==0) que.add(i); 
		}
		
		boolean[] inLine= new boolean[N+1]; //줄 세웠는지 확인 
		StringBuilder sb= new StringBuilder(); 
		while(!que.isEmpty()) {
			int student = que.poll(); 
			inLine[student]=true; 
			sb.append(student).append(" "); 
			
			for(int i=0; i<list.get(student).size(); i++) {
				int next_student= list.get(student).get(i); 
				inCnt[next_student]--; 
				
				if(inCnt[next_student]==0) que.add(next_student); 
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(!inLine[i]) sb.append(i).append(" "); 
		}
		
		System.out.println(sb.substring(0, sb.length()-1));
	}//end of main 
}//end of class
