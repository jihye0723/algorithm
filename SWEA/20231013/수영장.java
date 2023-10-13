import java.io.*;
import java.util.*;

public class Solution {
	static int[]cost= new int[4];
	static int[]plan = new int[12]; 
	
	static int[] min_cost; 
	static int three_month ;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T = Integer.parseInt(br.readLine());//테스트케이스의 갯수 
		for(int tc=1; tc<=T; tc++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int i=0; i<4; i++) {
				cost[i] = Integer.parseInt(st.nextToken()); 
			}
			
			st= new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<12; i++) {
				plan[i]= Integer.parseInt(st.nextToken()); 
			}
			
			min_cost= new int[12]; //각 달을 이용하는데 1일권 / 1달권 
			three_month = Integer.MAX_VALUE; //3달치 중 최소
			//달마다 일일권과 한달권 비교해서 더 싼 값을 min_cost에 넣기 
			for(int i=0; i<12; i++) {
				if(plan[i]==0) continue; //이용계획 없는 달 
				//i달에 1일권으로 이용하는 것 보다 한달권이 더 쌀 경우 
				if(plan[i] * cost[0] > cost[1]) min_cost[i]= cost[1]; 
				//1일권이 더 쌀 경우 또는 같을 경우
				else min_cost[i] = plan[i]*cost[0]; 
			}


			
			//3달간의 가능한 조합 구하기 
			getComb(0); 
			
			if(three_month > cost[3]) three_month = cost[3]; 
			
			sb.append("#").append(tc).append(" ");
			sb.append(three_month).append("\n"); 
			
		}//end of testcase for
		
		
		System.out.println(sb.substring(0,sb.length()-1));
	}//end of main
	static int isStart[] = new int[12]; 
	private static void getComb(int index) {
		if(index > 11) {
			//1: 3달치의 시작점 
			int sum=0; 
			boolean[] isCheck = new boolean[12]; 
			for(int i=0; i<12; i++) {
				if(isCheck[i]) continue; //이미 더해짐 (3달치겠지?) 
				if(isStart[i] == 1) {
					//3개 더하기
					int three_sum=0; 
					for(int j=i; j<i+3; j++) {
						if(j>11) break; 
						three_sum += min_cost[j]; 
						isCheck[j] = true; 
					}
					//세달치 구매하는게 이득이면, 
					if(three_sum > cost[2]) sum += cost[2]; 
					else sum += three_sum; 
				}
				else sum+= min_cost[i]; 
			}
			
			three_month = Math.min(three_month, sum); 
			return; 
		}
		
		for(int i=index; i<12; i++) {
			isStart[i] = 1; 
			getComb(i+3); 
			isStart[i] = 0; 
		}
	}
	
}//end of class 
