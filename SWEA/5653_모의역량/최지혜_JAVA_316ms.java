package study;

import java.util.*;
import java.io.*;

public class Solution_SWEA_5653_줄기세포배양 {
	static PriorityQueue<Cell> que;
	static Queue<Cell> temp;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	private static boolean[][] visited;
	private static int[][] map;
	
	static class Cell implements Comparable<Cell>{
		int x;
		int y;
		int time;
		int last;
		boolean isAct;
		
		public Cell(int x, int y, int time, int last, boolean isAct) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.last = last;
			this.isAct = isAct;
		}

		//동시에 두개 이상의 줄기세포가 번식한다면, 생명력이 더 높은것으로 배양하기 위해 내림차순 정렬 
		@Override
		public int compareTo(Cell o) {
			return o.time- this.time;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			que= new PriorityQueue<>(); 
			
			String s= br.readLine();
			st= new StringTokenizer(s, " ");
			
			int N= Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new int[2*K+N+4][2*K+M+4];
			visited = new boolean[2*K+N+4][2*K+M+4];
//			map=new int[1200][1200];
//			visited= new boolean[1200][1200];
			
			for(int i=(2*K+N)/2; i<(2*K+N)/2+N; i++) {
				String sl = br.readLine();
				st= new StringTokenizer(sl, " ");
				for(int j=(2*K+M)/2; j<(2*K+M)/2+M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
					if(map[i][j] !=0) {
						que.add(new Cell(i,j,map[i][j], map[i][j], false));
						visited[i][j] = true;
					}
				}
			}
			
			bfs(K); 
			sb.append("#").append(tc).append(" ").append(que.size()).append("\n"); 

		}// end of testcase for 
		System.out.println(sb);
	}

/**
 * 큐 : 환성화 / 비활성화 상태인 것만 저장 ( 죽은 것 X  ) 
 * 1시간 
 *  : 일단, 큐(que)에있는것들은 다 써야됨 ( 한시간 동안 ) 
 *  : 큐가 빌때까지 반복 
 *  : last - 1 
 *  : 비활성화 상태인데, last = 0 이라면, 활성화로 바꿔서 큐에 넣기 
 *  : 활성화 상태인데, time-last =1 이라면, 사방으로 time 만큼 퍼지게 + 방문처리
 *  : 활성화 상태인데, last = 0 이라면, 죽은거니까 큐에 안넣어돼 
 *  : 줄어든 last 로 큐에 넣기 !! last = 0 이 아닌경우에 한해, 
 *  : 방문체크 해주면서 ~ 
 *  : ▲ 위 과정에서, 새롭게 큐에 넣어야 하는 애들은 새롭게 정의한 큐(temp)에 넣어주고, 
 *  기존의 큐가 다비면, 거기에다가 다시 넣어주기 ! 
 *  
 * */
	private static void bfs(int limitTime) {
		temp = new LinkedList<>(); 
		for(int t= 1; t<=limitTime; t++) {
			while(!que.isEmpty()) {
				Cell now = que.poll();
				 int x = now.x;
				 int y = now.y;
				 int time = now.time;
				 int last = now.last;
				 boolean isAct= now.isAct; // 비활성화 : false , 활성화 : true 
				 
				 last--;
				 
				 //비활성화 상태인데, last = 0 → 활성화 
				 if(!isAct && last==0) {
					 temp.add(new Cell(x,y,time,time,true));
				 }
				 
				 //활성상태인데, 활성한지 1시간째라면, 사방으로 분포 
				 else if(isAct && (time-last)==1) {
					 for(int dir=0; dir<4; dir++) {
						 int nx = x+dx[dir];
						 int ny = y+dy[dir];
						 // 방문체크 
						 if(visited[nx][ny]) continue;
						 
						 temp.add(new Cell(nx, ny , time, time, false));
						 visited[nx][ny] = true; // 새로 분포한 아이들 방문처리 
						 
					 }
					 if(last==0) continue;
					 temp.add(new Cell(x,y,time,last,isAct));
				 }
				 
				 else if (last !=0 ) {
					 temp.add(new Cell(x,y,time,last,isAct));
				 }
				
				
			}
			//한 시간동안 새롭게 생긴 아이들 다시 큐에 넣어주기 
			while(!temp.isEmpty()) {
				que.add(temp.poll()); 
			}
		}
		
		
	}
}
