import java.util.*;
import java.io.*;
	
public class Main {
	
	static class Point implements Comparable<Point>{
		int broke; //벽을 뚫었으면 1, 아니면 0 
		int x;
		int y; 
		int cnt;
		
		
		public Point(int broke, int x, int y, int cnt) {
			this.broke= broke;
			this.x=x;
			this.y=y;
			this.cnt=cnt; 
		}


		@Override
		public int compareTo(Point o) {
			return this.cnt -o.cnt;
		}
	}
	
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	 
	static int[][] map;
	static boolean[][][] visited; //벽을 부수지 않고  :0 , 부수고: 1 
	static int N, M; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; 
		visited = new boolean[N][M][2]; 
		for(int i=0; i<N; i++) {
			String s = br.readLine(); 
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j)-'0'; 
			}
		}
		
		int res= bfs(0,0); 
		System.out.println(res);
		
	}//end of main 
	private static int bfs(int x, int y) {
		PriorityQueue<Point> que= new PriorityQueue<>();
		
		visited[x][y][0] = true;
		que.add(new Point(0, x, y, 1));  
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			int px = p.x;
			int py = p.y;
			int cnt = p.cnt;
			int broke = p.broke; 
			
//			System.out.println(px+" "+py+" "+cnt+" "+broke);
			
			if(px == N-1 && py == M-1) return cnt; 
			
			for(int dir= 0; dir<4; dir++) {
				int nx = px+dx[dir];
				int ny = py+dy[dir];
				
				if(isOut(nx, ny)|| visited[nx][ny][broke]) continue; 
				
				//broke : 현재까지 벽을 부순적이 있는지 
				if(broke==0) {
					//아직 벽 부순적 없는데, 지나갈 수 있는 칸을 만났을 때 
					if(map[nx][ny]==0) {
//						System.out.println(nx+" "+ ny +" "+broke +" "+ (cnt+1));
						visited[nx][ny][broke] = true;
						que.add(new Point(broke, nx, ny, cnt+1));
					}
					//아직 벽 부순적 없는데, 벽을 만났을 때 
					else {
						visited[nx][ny][1]= true;
						que.add(new Point(1, nx, ny, cnt+1)); 
//						System.out.println(nx+" "+ ny +" "+1 +" "+ (cnt+1));
					}
				}
				//이미 벽을 부쉈을 떄, 
				else {
					if(map[nx][ny]==0) {
//						System.out.println(nx+" "+ ny +" "+broke +" "+ (cnt+1));
						visited[nx][ny][broke] = true;
						que.add(new Point(broke, nx, ny, cnt+1));
					}
					//벽을 부쉈는데, 또 벽을 만나면 지나갈 수 없음 
				}
			}
		}
		return -1; 
	}
	
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M; 
	}
}//end of class 
