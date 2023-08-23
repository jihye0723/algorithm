import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int map[][]; 
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};

	static class Room implements Comparable<Room>{
		int x;
		int y;
		int change;
		
		public Room(int x, int y, int change) {
			this.x=x;
			this.y=y;
			this.change=change;
		}

		@Override
		public int compareTo(Room o) {
			return this.change- o.change;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	
		n = Integer.parseInt(br.readLine()); 
		
		map= new int[n][n];
		for(int i=0; i<n; i++) {
			String s= br.readLine();
			for(int j=0; j<n; j++) {
				map[i][j] = s.charAt(j)-'0';  
			}
		}
		
		System.out.println(bfs()); 
		
		
	}//end of main
	
	private static boolean isOut(int x, int y) {
		return x<0 || x>=n ||y<0 || y>=n;
	}
	private static int bfs() {
		PriorityQueue<Room> que= new PriorityQueue<>();
		boolean visited[][] = new boolean[n][n]; 
		
		que.add(new Room(0,0,0));
		visited[0][0] = true; 
		
		while(!que.isEmpty()) {
			Room r= que.poll();
			int x= r.x;
			int y= r.y;
			int change= r.change;
			
//			System.out.println(x+" "+y+" "+change);
			if(x==n-1 && y==n-1) return change; 
			
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(isOut(nx, ny)|| visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				//검은방일 경우, 흰방으로 바꾸어야함 
				if(map[nx][ny]==0) {
					map[nx][ny]=1; 
					que.add(new Room(nx,ny,change+1)); 
				}
				else que.add(new Room(nx,ny,change)); 
			}
 		}
		
		return -1;
	}
}//end of class 
