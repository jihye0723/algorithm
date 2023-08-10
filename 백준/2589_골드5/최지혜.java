import java.util.*;
import java.io.*;
	
public class Main {
	static class Land implements Comparable<Land>{
		int x;
		int y;
		int dis;
		
		public Land(int x ,int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis= dis;
		}

		@Override
		public int compareTo(Land o) {
			return this.dis- o.dis; 
		}
	}
	
	static int N, M; 
	static char[][] map; 
	static List<int[]> list= new ArrayList<>(); //육지 위치 리스트
	static int treasure = Integer.MIN_VALUE; // 보물간 거리 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String s= br.readLine(); 
			for(int j=0; j<M; j++) {
				map[i][j]= s.charAt(j);
				if(map[i][j]=='L') list.add(new int[] {i,j});
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			treasure= Math.max(treasure, bfs(i));
		}

		System.out.println(treasure);
		
	}//end of main
	
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	static int bfs(int i) {
		int max = Integer.MIN_VALUE; 
		int sx= list.get(i)[0];
		int sy= list.get(i)[1];
		
		boolean visited[][] = new boolean[N][M];
		PriorityQueue<Land> que = new PriorityQueue<>();
		
		que.add(new Land(sx, sy, 0));
		visited[sx][sy] = true; 
		
		while(!que.isEmpty()) {
			Land n = que.poll();
			int x= n.x;
			int y= n.y;
			int dis= n.dis;
	
			max= Math.max(max, dis); 
			
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(isOut(nx,ny)||visited[nx][ny]||map[nx][ny]=='W') continue;
				
				visited[nx][ny]=true;
				que.add(new Land(nx,ny,dis+1)); 
			}
			
		}
		return max; 
	}
	
	static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M; 
	}
}//end of class 
