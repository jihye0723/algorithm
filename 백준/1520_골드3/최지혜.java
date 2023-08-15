import java.util.*;
import java.io.*;

public class Main {
	static int N,M;
	static int[][] map; 
	static int[][] dp; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken()); 
		
		map = new int[N][M];
		dp = new int[N][M]; //해당 지점에서 목적지까지 갈수 있는 경로의 갯수 
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<M; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], -1);
		} 
		int res= dfs(0,0); 
		
		System.out.println(res);
		
	
	}//end of main
	
	static int dx[]= {-1,0,1,0};
	static int dy[]= {0,-1,0,1}; //상 좌 하 우 
	
	//0,0에서 시작하여 깊이우선 탐색 
	private static int dfs(int x, int y) {
		if(x==N-1 && y==M-1) return 1;
		
		if(dp[x][y] != -1) return dp[x][y]; //이미 한번 탐색한 경로 등장 
//		if(x==N-1 && y==M-1) {
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(dp[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		dp[x][y] =0; //해당 위치에서 탐색 
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d]; 
			
			if(isOut(nx, ny)) continue;
			
			//이동 가능 
			if(map[nx][ny] < map[x][y]) {
				dp[x][y] += dfs(nx,ny); 
			
			}
		}
		return dp[x][y];
	}
	
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M; 
	}
}//end of class 
