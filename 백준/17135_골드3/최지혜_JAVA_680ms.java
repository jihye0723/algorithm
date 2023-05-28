package study_17;

import java.util.*;
import java.io.*;
/*
 * 조합 : 궁수의 위치를 조합으로 구해서 모든 경우의 수 중 최대 적 제거 수 계산*/
public class Solution_BaekJoon_17135 {
	private static int N,M,D;
	private static int[][] map;
	private static boolean[][] visited; //bfs 탐색을 위한 방문 체크 
	private static boolean[] picked;
	private static int attack_max= Integer.MIN_VALUE; 
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		N= Integer.parseInt(st.nextToken()); 
		M= Integer.parseInt(st.nextToken());
		D= Integer.parseInt(st.nextToken()); 
		
		picked = new boolean[M]; 
		map = new int[N][M]; 
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<M; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		setComb(0, 3); 
		System.out.println(attack_max);
	}//end of main
	
	//궁수 3명 배치하기 
	private static void setComb(int index, int cnt) {
		//3명 모두 배치했을 경우, 
		if(cnt==0) {
			int count= attack(); 
			attack_max= Math.max(count, attack_max); 
			return ; 
		}
		for(int i=index; i<M; i++) {
			picked[i]=true;   
			setComb(i+1, cnt-1); 
			picked[i]=false;   
		}
	}
	
	static class Point implements Comparable<Point>{
		int x; int y;
		int dis;
		
		public Point(int x, int y, int dis) {
			this.x= x;
			this.y= y;
			this.dis= dis; 
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.dis == o.dis) {
				return this.y - o.y; 
			}
			return this.dis- o.dis; 
		}
		
	}
	static int dx[]= {-1,0,0};
	static int dy[]= {0,1,-1};
	//공격 시작하기 
	private static int attack(){
		int enemy_cnt =0; //이번 공격에 제거할 수 있는 적의 수 
		PriorityQueue<Point> que; 
		int[][] copymap = new int[N][M]; //이번 턴에서 공격할 지도 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) 
				copymap[i][j] = map[i][j];//원본배열 복사 
		}
		
		//모든 적이 없어질 때까지 반복 
		while(!isFinish(copymap)) {
			for(int i=0; i<picked.length; i++) {
				//궁수가 있는 곳에서 공격 시작 
				if(picked[i]) {
					que= new PriorityQueue<>();
					visited= new boolean[N][M]; 
					que.add(new Point(N-1, i, 1)); 
					
					//해당 궁수와 가장 가까운 적 찾기 
					while(!que.isEmpty()) {
						Point p = que.poll();
						int x= p.x;
						int y= p.y;
						int dis= p.dis;
						//가장 가까운 적 ! 
						if(copymap[x][y] != 0 && dis <= D) {
							//처음 공격 받는 적 
							if(copymap[x][y]==1) {
								enemy_cnt ++; 
								copymap[x][y]=-1; 
							}
							break; 
						}
						
						for(int d=0; d<3; d++) {
							int nx = x+dx[d];
							int ny = y+dy[d]; 
							
							if(isOut(nx, ny) || visited[nx][ny]) continue; 
							
							visited[nx][ny] = true;
							que.add(new Point(nx, ny, dis+1)); 
						}
						
					}
						
				}
			}
			
			for(int i=N-1; i>=1; i--) {
				for(int j=0; j<M; j++) {
					copymap[i][j] = copymap[i-1][j];
				}
			}
			Arrays.fill(copymap[0], 0);
			
		}//end of while 
		return enemy_cnt; 
	}
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
	//모든 적이 격자판에서 제외됬으면 true 반환 
	private static boolean isFinish(int[][] copymap) {
		boolean flag = true; 
		for(int i=0; i<N; i++) {
			for(int j=0;j <M; j++) {
				if(copymap[i][j] ==-1) copymap[i][j] = 0; 
				if(copymap[i][j] ==1 ) flag= false; 
			}
		}
		return flag; 
	}
}//end of class
