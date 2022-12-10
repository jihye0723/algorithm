 package study;

import java.io.*;
import java.util.*;

public class Solution_BaekJoon_20529_가장가까운 {
	static String[] mbti; // 학생들의 mbti 배열 
	static boolean[] combi; // 3명 조합 포함 여부 
	static int min; 
	public static void main(String args[]) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- >0) {
			min = Integer.MAX_VALUE;
			int N = Integer.parseInt(br.readLine()); // 학생의 수
		
			mbti = new String[N]; 
			combi= new boolean[N]; 
			
			st= new StringTokenizer(br.readLine(), " "); 
			for(int i=0; i<N; i++) {
				mbti[i] = st.nextToken();
			}
			
			if(N > 32) {
				System.out.println(0);
			}
			else {
			// 3개 조합 찾기 
			getCombi(0,0); 
			
			System.out.println(min); 
			
			}
			 
		} // end of 테스트케이스 반복 
		
	}// end of main 
	private static void getCombi(int index, int count) {
		//3명 다 뽑았을 때 
		if(count==3) {
			List<String> list= new ArrayList<String>(); 
			for(int i=0; i<mbti.length; i++) {
				// 만약 조합에 포함된 아이라면 , 
				if(combi[i]) {
					list.add(mbti[i]); 
				}
				
			}
			int num = getCount(list);
			min = Math.min(min, num); 
			return; 

 		}
		
		for(int start=index; start<mbti.length; start++) {
			// 해당 학생 포함 
			combi[start] = true; 
			getCombi(start+1, count+1); 
			// 해당 학생 미포함 
			combi[start] = false; 
		}
	}
	private static int getCount(List<String> list) {
		String first= list.get(0);
		String second= list.get(1);
		String third= list.get(2);
		
		int count =0;
		for(int i=0; i<4; i++) {
			if(first.charAt(i) != second.charAt(i)) count++;
			if(first.charAt(i) != third.charAt(i)) count++;
			if(second.charAt(i) != third.charAt(i)) count++;
		}
		return count;
	}
}
