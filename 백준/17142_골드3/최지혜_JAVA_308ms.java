package samsung;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_17142 {
	private static int N, M; 
	private static int map[][]; 
	private static List<int[]> list; //바이러스를 놓을 수 있는 위치 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
		N = Integer.parseInt(st.nextToken()); //연구소의 크기 4≤N≤50
		M = Integer.parseInt(st.nextToken()); //바이러스의 갯수 10개 이하 
		map = new int[N][N]; 
		
		list= new ArrayList<>(); 
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if(map[i][j]==2) list.add(new int[] {i,j}); 
			}
		}
		selected = new boolean[list.size()];
		start(0,M); 
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}//end of main 
	
	/*
	 * 비활성 바이러스 : 2로 놔두고 
	 * 활성 바이러스 : 3 
	 * 결국엔 0이 있으면 안됨 (모든 칸에 바이러스가 퍼질려면)*/
	
	private static boolean[] selected; //조합 선택 
	private static Queue<int[]> que;
	private static int min = Integer.MAX_VALUE;
	//1.일단 바이러스 놓을 수 있는 위치중 m개 고르기 
	private static void start(int index, int M) {
		//조합 모두 골랐을 경우, 
		if(M==0) {
			que= new LinkedList<>(); 
			for(int i=0; i<list.size(); i++) {
				if(selected[i]) {
					que.add(new int[] {list.get(i)[0], list.get(i)[1]});//퍼질 수 있는 활성 바이러스 
				}
			}
			int res= spread(); 
			min = Math.min(res, min); 
		}
		
		for(int i=index; i<selected.length; i++) {
			selected[i]=true; 
			start(i+1, M-1);
			selected[i]=false; 
		}
	}
	
	private static int dx[]= {-1,1,0,0};
	private static int dy[]= {0,0,-1,1}; 
	private static boolean visited[][]; 
	private static int[][] copy; 
	/*활성화된 바이러스 큐에 담고 있는 상태로 바이러스 퍼지게 */
	private static int spread() {
		copy(); //일단 맴 상태 복사 
		int time=0; //몇초 
		visited= new boolean[N][N]; 
		while(!que.isEmpty()) {
			if(isAll()) return time; 
			int size= que.size(); 
			//현재 1초동안 활성화 될 수 있는 바이러스 퍼지기 
			for(int i=0; i<size; i++) {
				int[] a= que.poll();
				int x=a[0];
				int y=a[1]; 
				
				for(int dir=0; dir<4; dir++) {
					int nx= x+dx[dir];
					int ny= y+dy[dir];
					
					//경계밖, 이미 퍼짐, 벽
					if(isOut(nx, ny) || visited[nx][ny] || copy[nx][ny]==1) continue; 
					
					visited[nx][ny]= true; 
					copy[nx][ny]=2; 
					que.add(new int[] {nx, ny}); 
				}
			}
			time++; 
		}

		return Integer.MAX_VALUE; 
	}
	
	private static void copy() {
		copy = new int[N][N]; 
		for(int i=0; i<N; i++) {
			copy[i]= map[i].clone();
		}
	}
	
	//모두 바이러스로 채워졌는지 확인 
	private static boolean isAll() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(copy[i][j] ==0) return false; 
			}
		}
		return true; 
	}
	
	//경계밖으로 나가는지 
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=N; 
	}
}//end of class
