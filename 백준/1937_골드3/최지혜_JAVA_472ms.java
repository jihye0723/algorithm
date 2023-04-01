package study_17;

import java.util.*;
import java.io.*;

/*
 * dp : 해당 위치에서 갈 수 있는 최대 거리 
 * 각 위치에서 dfs 탐색을 통해 최대 위치를 찾는데, 만약 dp 값이 0이 아닌 경우를 만날 경우 
 * dp에 저장해둔 값을 활용 
 * */
public class Solution_BaekJoon_1937 {
	//사방 탐색을 위한 방향 설정 
	private static int dx[]= {-1,1,0,0};
	private static int dy[]= {0,0,-1,1};
	
	private static int N; 
	private static int map[][]; 
	private static int dp[][]; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N= Integer.parseInt(br.readLine());
		dp= new int[N][N]; //각 위치에서 갈 수 있는 최대값 
		
		map= new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		int res=0; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				res= Math.max(res,dfs(i,j)); 
			}
		}
		System.out.println(res);
			
	}//end of main 
	
	private static int dfs(int x, int y) {
		
		if(dp[x][y] !=0) {
			return dp[x][y];
		}
		dp[x][y]=1;
		
		//사방 탐색 
		for(int dir=0; dir<4; dir++) {
			int nx= x+dx[dir];
			int ny= y+dy[dir]; 
			
			//경계 밖으로 나갔거나, 대나무가 적어서 못가 
			if(isOut(nx,ny) || map[x][y] >= map[nx][ny]) continue; 
			
			dp[x][y] = Math.max(dp[x][y], 1+dfs(nx,ny)); 
		}
		
		return dp[x][y];  
		
	}//end of dfs
	
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=N; 
	}
}//end of class 
