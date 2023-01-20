import java.io.*;
import java.util.*;

public class Main {
	
	private static int dx[] = {-1,1,0,0};
	private static int dy[] = {0,0,-1,1};
	private static int M;
	private static int N;
	private static int K;
	private static int[][] map;
	private static boolean[][] visited;
	private static int count; 
	private static PriorityQueue<Integer> que= new PriorityQueue<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		
		
		st= new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken()); // 직사각형 세로 길이 
		N = Integer.parseInt(st.nextToken()); // 직사각형 가로 길이 
		K = Integer.parseInt(st.nextToken()); // 내부 직사각형 갯수 
		
		map = new int[M][N];
		visited= new boolean[M][N]; 
		
		while(K-->0) {
			st= new StringTokenizer(br.readLine(), " ");
			int lx = Integer.parseInt(st.nextToken());
			int ly = Integer.parseInt(st.nextToken());
			int rx = Integer.parseInt(st.nextToken());
			int ry = Integer.parseInt(st.nextToken());
			
			for(int i=M-ry; i<M-ly; i++) {
				for(int j=lx; j<rx; j++) {
					map[i][j]=1;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				count=0;
				if(map[i][j]==0 && !visited[i][j]) {
					dfs(i,j); 
					que.add(count);
				}
			}
		}
		
		System.out.println(que.size()); 
		
		while(!que.isEmpty()) {
			System.out.print(que.poll()+" ");
		}

	}// end of main 
	private static void dfs(int x, int y) {
		visited[x][y]=true;
		count++;
		
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			

			if(isOut(nx,ny)||visited[nx][ny]||map[nx][ny]==1 ) continue;
			
			dfs(nx,ny); 
			
		}
	
	}
	
	// 경계를 벗어났는지 
	private static boolean isOut(int x, int y) {
		return (x<0 || x>=M || y<0 || y>=N); 
	}
}// end of class
