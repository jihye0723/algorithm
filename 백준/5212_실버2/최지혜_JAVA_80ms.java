package study;

import java.io.*;
import java.util.*;

public class BaekJoon_Solution_5212 {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	static class Point{
		int x ; 
		int y ;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ; 
		Queue<Point> que= new LinkedList<>();
		
		String s= br.readLine();
		st= new StringTokenizer(s, " ");
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] map = new char[r][c];
		
		int minr = r-1; 
		int maxr = 0; 
		int minc = c-1;
		int maxc = 0; 
		
		
		for(int i=0; i<r; i++) {
			String line = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = line.charAt(j); 
				// 만약 섬이라면, 
				if(map[i][j]=='X') que.add(new Point(i,j));
			}
		}
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			int x = p.x;
			int y = p.y;
			
			int cnt = 0; 
			// 사방에 섬이 몇개 있는지 탐색 
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(nx<0 || ny <0 || nx>=r || ny>=c || map[nx][ny]=='.') cnt++; 
				
//				if(map[nx][ny]=='.') cnt++;
			}
			// 인접한 3-4면이 바다라면 잠겨버림 
			if (cnt>=3) map[x][y] = '-'; 
			
		}
	
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] =='-') map[i][j]='.'; 
				
				if(map[i][j]=='X') {
					minr= Math.min(minr, i); 
					maxr= Math.max(maxr, i); 
					minc= Math.min(minc, j);
					maxc= Math.max(maxc, j); 
				}
			}
		}
		
		
		for(int i=minr; i<=maxr; i++) {
			for(int j=minc; j<=maxc; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
		
		
		
		
	}
}
