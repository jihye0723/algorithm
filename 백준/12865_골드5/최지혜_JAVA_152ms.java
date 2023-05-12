package study_17;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_12865 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		 
		st= new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken()); 
		int K= Integer.parseInt(st.nextToken()); //배낭의 최대 무게 
		
		int items[][] = new int[N+1][2]; //인덱스 맞춰주기 
		for(int i=1; i<=N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			items[i][0] = Integer.parseInt(st.nextToken());//물건의 무게 
			items[i][1] = Integer.parseInt(st.nextToken());//물건의 가치 
		}
		
		int [][] dp = new int[N+1][K+1]; 
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				//현재 무게에 해당 물건을 넣을 수 없는경우, 이전까지의 최적해 
				if(items[i][0] > j ) dp[i][j] = dp[i-1][j]; 
				//현재 무게에 해당 물건을 넣을 수 있는 경우 
				else {
					//현재 물건을 넣는것(= 현재 물건의 무게를 뺀 무게의 최적해에 현재 가치 더하기) 과 안넣는 것 중 더 높은 가치 
					dp[i][j] = Math.max(dp[i-1][j-items[i][0]]+items[i][1], dp[i-1][j]); 
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}//end of main 
}
