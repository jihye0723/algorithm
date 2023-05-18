package study_17;

import java.util.*;
import java.io.*;

public class BaekJoon_Solution_14846 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N= Integer.parseInt(br.readLine());
		int[][][] dp = new int[N+1][N+1][11]; //특정 위치에 번호가 몇개 있는지 (번호는 1~10의 자연수) 
		
		StringTokenizer st; 
		int num=0; 
		for(int a=1; a<=N; a++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int b=1; b<=N; b++) {
				for(int c=1; c<=10; c++) {
					dp[a][b][c] = dp[a-1][b][c]+dp[a][b-1][c]-dp[a-1][b-1][c]; 
				}
				num = Integer.parseInt(st.nextToken());//해당 위치 의 번호 
				dp[a][b][num]++;  
			}
		}
		
//		for(int a=1; a<=N; a++) {
//			for(int b=1; b<=N; b++) {
//				for(int c=1; c<=10; c++) {
//					System.out.println(a+"행,"+b+"열,"+c+"의 누적 갯수:" +dp[a][b][c]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		int Q= Integer.parseInt(br.readLine()); 
		while(Q-- >0) {
			st= new StringTokenizer(br.readLine(), " ");
			int x1= Integer.parseInt(st.nextToken());
			int y1= Integer.parseInt(st.nextToken());
			int x2= Integer.parseInt(st.nextToken());
			int y2= Integer.parseInt(st.nextToken());
		
			int cnt=0; 
			for(int i=1; i<=10; i++) {
				if((dp[x2][y2][i]-dp[x2][y1-1][i]-dp[x1-1][y2][i]+dp[x1-1][y1-1][i]) >0 ) cnt++;
			}
			System.out.println(cnt);
		}
	}//end of main 
}//end of class 
