import java.util.*;
import java.io.*;

//v:늑대, k:양, #:울타리
public class Solution_BaekJoon_3187_양치기꿍_실버1 {
	static int dx[]= {-1,0,1,0};
	static int dy[]= {0,-1,0,1};
	private static int r;
	private static int c;
	private static char[][] map;
	private static boolean[][] visited;
	private static int wolf;
	private static int sheep;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		 
		String s = br.readLine();
		st= new StringTokenizer(s, " ");
		r= Integer.parseInt(st.nextToken());
		c= Integer.parseInt(st.nextToken());
		
		int wolfs =0; //늑대
		int sheeps =0; // 양 
		
		map = new char[r][c];
		visited = new boolean[r][c]; 
		
		for(int i=0; i<r; i++) {
			String s1= br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s1.charAt(j);
				if(map[i][j]=='v') wolfs++;
				if(map[i][j]=='k') sheeps++;
			}
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				//늑대나 양이 나오면 탐색 시작, 방문하지 않았던 곳 
				if((map[i][j]=='k'|map[i][j]=='v') && !visited[i][j]) {
					wolf=0; 
					sheep=0;
					if(map[i][j]=='k') sheep=1;
					else if(map[i][j]=='v') wolf=1;
					dfs(i,j); 
					if(wolf < sheep) wolfs-=wolf;
					else sheeps-=sheep;
				}
			}
		}
		
		System.out.println(sheeps+" "+wolfs);
			
	}// end of main
	
	private static void dfs(int i, int j) {
		visited[i][j] = true; 
		
		for(int dir=0; dir<4; dir++) {
			int ni = i+dx[dir];
			int nj = j+dy[dir];
			
			// 방문했거나, 경계밖이거나, 울타리거나
			if(visited[ni][nj] || isOut(ni,nj) || map[ni][nj]=='#') continue; 
			
			if(map[ni][nj]=='k') {
				sheep++;
			}
			
			if(map[ni][nj]=='v') {
				wolf++;
			}
			
			dfs(ni,nj);
		
		}
	
	}
	private static boolean isOut(int i, int j) {
		return (i<0 || i>=r || j<0 || j>=c); //경계밖 > true 
	}
}
