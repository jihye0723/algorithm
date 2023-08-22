import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String s= br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]= s.charAt(j)-'0'; 
			}
		}
		
		//bfs
		System.out.println(bfs()); 
		
	}//end of main
	
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1};
	
	static boolean isOut(int x, int y) {
		return x<0 || x>= N || y<0 || y>=M; 
	}
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int wall; //부셔야하는 벽 
		
		public Point(int x, int y, int wall){
			this.x= x;
			this.y= y;
			this.wall = wall; 
		}
		
		@Override
		public int compareTo(Point o) {
			return this.wall - o.wall; 
		}
	}
	static int bfs() {
		PriorityQueue<Point> que = new PriorityQueue<>(); 
		boolean visited[][] = new boolean[N][M]; 
		
		que.add(new Point(0,0,0));
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			int x= p.x;
			int y= p.y;
			int wall = p.wall;
			
			if(x==N-1 && y==M-1) return wall; 
			
			for(int d=0; d<4; d++) {
				int nx= x+dx[d];
				int ny= y+dy[d];
				
				if(isOut(nx,ny) || visited[nx][ny]) continue;
				
				visited[nx][ny]=true;
				//벽일 경우 
				if(map[nx][ny]==1) {
					map[nx][ny]=0; //벽 부수고 
					que.add(new Point(nx, ny, wall+1)); 
				}
				
				//그냥 빈 곳 
				else que.add(new Point(nx,ny, wall)); 
			}
		}
		
		return -1; 
	}
}//end of class 
