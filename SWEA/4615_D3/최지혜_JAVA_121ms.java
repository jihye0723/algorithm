package study;

import java.util.*;
import java.io.*;

public class Solution_SWEA_4615_D3 {
	static int dx[] = {-1,-1,-1,0,0,1,1,1};
	static int dy[] = {-1,0,1,-1,1,-1,0,1}; // 팔방탐색
	private static int N;
	private static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=t; tc++) {
			st= new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 보드의 한변 길이 ( 4,6,8 ) 
			int M = Integer.parseInt(st.nextToken()); // 돌을 놓는 횟수 
			
			// 흑돌 : 1 , 백돌 : 2 
			map = new int[N][N];
			
			map[N/2-1][N/2] = map[N/2][N/2-1] = 1;  
			map[N/2-1][N/2-1] = map[N/2][N/2] = 2;
			
			
			while(M-->0) {
				st= new StringTokenizer(br.readLine(), " ");
				int r= Integer.parseInt(st.nextToken());
				int c= Integer.parseInt(st.nextToken());
				int color= Integer.parseInt(st.nextToken());
				
				int x = c-1;
				int y = r-1;
				// 돌 놓기 
				map[x][y] = color;
				
				
				for(int dir=0; dir<8; dir++) {
					int nx = x+dx[dir];
					int ny = y+dy[dir];
					
					if(isOut(nx,ny)) {
						continue; 
					}
					if(map[nx][ny] != color) {
						find(x,y,dir);
					}
					
				}
				
//				for(int i=0; i<N; i++) {
//					for(int j=0; j<N; j++) {
//						System.out.print(map[i][j]);
//					}
//					System.out.println();
//					
//				}
//				System.out.println();
			}
			
			int black = 0; 
			int white = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==1) black++;
					if(map[i][j]==2) white++; 
				}
			}
	
			System.out.print("#"+tc+" "+black+" "+white);
			System.out.println();
		}// end of testcase for 
		
	}// end of main 
	static class Point{
		int r;
		int c;
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	private static void find(int r, int c, int direction) {
		int rock= map[r][c]; //방금 놓은 돌의 색깔 
		Queue<Point> que = new LinkedList<>();
		boolean flag = true; 
		// 벽을 만나기 전까지 
		while(true) {
			r += dx[direction]; 
			c += dy[direction]; 
			
			//가다가 벽을 만났으면, 같은 색 못만난 것 
			if(isOut(r,c)) {
				flag= false; 
				break; 
			}
			//방금 놓은 돌과 같은 팀의 말 만남 
			if(map[r][c] == rock) break; 
			
			if(map[r][c] == 0) {
				flag=false;
				break;
			}
			
			que.add(new Point(r,c)); 
			
		}
		if(!flag) return ;
		while(!que.isEmpty()) {
			Point p= que.poll();
			map[p.r][p.c] = rock; 
		}
	}
	
	private static boolean isOut(int r, int c) {
		return (r<0 || r>=N || c<0 || c>=N); 
	}
}// end of class 
