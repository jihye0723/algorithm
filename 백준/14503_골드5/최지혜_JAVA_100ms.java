package study;

import java.io.*;
import java.util.*;

/*
 * 0:청소되지 않은 빈 칸 
 * 1:벽 
 * */
public class Solution_BaekJoon_14503 {
	//북-동-남-서
	private static int dr[]= {-1,0,1,0};
	private static int dc[]= {0,1,0,-1};
	
	private static int N,M;
	private static int[][] map; 
	private static int cnt=0; 
//	private static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine(), " ");
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine(), " ");
		int robotr= Integer.parseInt(st.nextToken());
		int robotc= Integer.parseInt(st.nextToken()); 
		int robotd= Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; 
//		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j]= Integer.parseInt(st.nextToken()); 
			}
		}
		
		dfs(robotr, robotc, robotd); 
//		System.out.println(cnt);
		
	}//end of main 
	private static void dfs(int r, int c, int d) {
		if(map[r][c] == 0) {
			cnt++; 
		}
		map[r][c] =2; //청소하기   

		
//		System.out.println(r+" "+c+" "+map[r][c]+" "+cnt);
		
		int nd=d;
		for(int i=0; i<4; i++) {
			//반시계 방향으로 4번 회전하기 
			nd=(nd+3)%4; //반시계 방향으로 회전 
			int nr= r+dr[nd];
			int nc= c+dc[nd];
			
			if(map[nr][nc]==1 || map[nr][nc]==2) continue; //벽이거나, 청소한 곳일 경우 
			
			dfs(nr,nc,nd); 

		}
		//주변 4칸 중 청소되지 않은 빈칸이 없는 경우, 
		int bd= (d+2)%4; //후진 방향 
		int br= r+dr[bd];
		int bc= c+dc[bd]; 
		if(map[br][bc]==1) { //후진할 수 없다면 작동 멈추기
			System.out.println(cnt);
			System.exit(0); 
		}
			 
	
		dfs(br,bc,d); //바라보는 방향 유지한채로


	}//dfs 
}//end of class 
