import java.io.*;
import java.util.*;

/*
 * 최대한 긴 등산로 만들기 
 * (규칙)
 * 1. 가장 높은 봉우리에서 시작 
 * 2. (높은 지형 > 낮은 지형) + (가로 또는 세로로 이동) 
 * 3. 더 긴 등산로를 만들기 위해 '딱 한곳만' 최대 k 깊이만큼 깍을 수는 있다.
 * (입력) 
 * N : 지도의 길이 
 * K : 최대 공사 가능 깊이 (K보다 작게 깍아도됨)
 * (출력) 
 * 가장 긴 등산로를 찾아서 그 길이 출력*/

/*
 * (로직)
 * DFS : 깊이 우선 탐색 
 * - 일단 가장 높은 봉우리 찾아서 큐에 넣기 (하나씩 가능 한 탐색) 
 * - 이동할 수 있는 곳이면 굳이 깍을 필요가 없음 !! 그래야 다음 봉우리의 경우의 수가 많아지기 때문에
 * - 이동할 수 없는 곳을 1~K 만큼 깍아보며 최대값 찾기 (백트래킹?) 
 * - K 만큼 깍아서 높이를 음수로 만들어도 됨 ! (깍을때 범위체크 안해도된다는 뜻_
 * */
public class Solution {
	static int N,K;
	static int map[][]; 
	static Queue<int[]> start = new LinkedList<>(); 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		int T = Integer.parseInt(br.readLine()); //테스트 케이스의 갯수 
		for(int tc= 1; tc<=T; tc++) {
			st= new StringTokenizer(br.readLine(), " ");
			N= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken()); 
			map = new int[N][N];
			int max=0; 
			for(int i=0; i<N; i++) {
				st= new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]); 
				}
			}
			//가장 높은 봉우리 (시작점 후보) 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==max) {
						start.add(new int[] {i,j}); 
					}
				}
			}
			
			//탐색 시작 
			findStart(); 
			sb.append("#").append(tc).append(" ");
			sb.append(max_length).append("\n"); 
		}//end of one_testcase 
		System.out.println(sb.substring(0, sb.length()-1));
	}//end of main
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,1,-1};
	private static void findStart() {
		max_length = 0;
		while(!start.isEmpty()) {
			int[] a= start.poll();
			int x= a[0]; int y=a[1]; 
			visited = new boolean[N][N]; 
			visited[x][y] = true; 
			dfs(x, y, 1, map[x][y], false); 
			
		}
	}//end of findStart
	//현재 위치, 등산로길이, 이전높이, 공사했는지 확인 여부 
	//높이 저장하는 이유: 공사했을 때 실제로 깍으면 다른 방향 탐색일때 지장이 가므로 height에 들고다님
	static int max_length; 
	static boolean[][] visited; 
	private static void dfs(int x, int y, int length, int height, boolean fix) {
		
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d]; 
			
			//경계밖으로 나가거나 이미 방문했던 곳이면, 
			if(isOut(nx, ny) || visited[nx][ny]) continue;
			
			//더 높거나 똑같은 곳이라서 갈 수 없는데 아직 공사 안했으면 
			if(map[nx][ny] >= height && !fix) {
				//최대 K만큼 깍았을 때 이동이 되야됨  , 안되면 공사해봤자 말짱도루묵
				if(map[nx][ny]-K < height) {
					//현재 높이에서 딱 1낮게만 공사를 해서 이동
					visited[nx][ny] = true;
					dfs(nx, ny, length+1, map[x][y]-1, true);
					visited[nx][ny] = false; // 백트래킹
					
				}
			}
			
			//낮은곳이라서 갈 수 있으면 
			else if(map[nx][ny] < height) {
				visited[nx][ny] = true; 
				dfs(nx,ny,length+1,map[nx][ny], fix); 
				visited[nx][ny] = false;//백트래킹 
			}
		}
		
		//네방향 다했는데 더이상 갈 곳이 없다 , 끝 
		max_length=Math.max(max_length, length); 
		return ; 
	}// end of dfs
	
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=N; 
	}
}//end of class 
