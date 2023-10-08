import java.io.*;
import java.util.*;
/*
 * bfs 탐색 
 * 탈주범은 시간당 1만큼 이동 가능 
 * 맨홀 뚜껑까지 가는데 한시간, 
 * 맨홀 뚜껑까지 포함해서 갯수 출력 (맨홀 뚜껑은 무조건 터널이 있는 위치임이 보장됨)
 */
public class Solution {
	static class Point implements Comparable<Point>{
		int x; 
		int y;
		int time; //몇시간 후에 도착한 곳인지 
		
		public Point(int x, int y, int time) {
			this.x=x;
			this.y=y;
			this.time=time;
		}

		@Override
		public int compareTo(Point o) {
			return this.time-o.time; 
		}
		
	}
	static int N,M,L;
	static int[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N= Integer.parseInt(st.nextToken()); //세로
			M= Integer.parseInt(st.nextToken()); //가로 
			int sx= Integer.parseInt(st.nextToken()); //맨홀의 위치 
			int sy= Integer.parseInt(st.nextToken()); 
			L = Integer.parseInt(st.nextToken()); //시간 
			
			map = new int[N][M]; 
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			sb.append("#").append(tc).append(" ");
			sb.append(bfs(sx, sy)).append("\n"); 	
		}//end of testcase 
		
		System.out.println(sb.substring(0,sb.length()-1));
	}//end of main
	static int dx[]= {-1,1,0,0}; //상,하,좌,우
	static int dy[]= {0,0,-1,1};
	private static int bfs(int sx, int sy) {
		int cnt=0; 
		PriorityQueue<Point> que= new PriorityQueue<>(); 
		boolean[][] visited = new boolean[N][M]; 
		
		que.add(new Point(sx,sy,1)); //1초 후에 맨홀 입성 
		visited[sx][sy]= true;
		cnt=0; //있을 수있는 장소 
		
		while(!que.isEmpty()) {
			Point p =que.poll(); 
			int x= p.x;
			int y= p.y; 
			int time= p.time; 
//			System.out.println(x+" "+y+" "+time);
			//처음 커지는 순간 바로 종료해야됨 (우선순위큐에 의해서 L이후 첫 등장) 
			if(time > L) break; 
			cnt++;
			
			//현재위치의 터널 모양에 따라 다름 ! (0은 터널 x : 큐에 넣지 않음) 
			//상하좌우 터널과 연결 
			if(map[x][y]==1) {
				for(int d=0; d<4; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0|| !isConnect(x,y,d)) continue;
					visited[nx][ny] = true; 
					que.add(new Point(nx, ny, time+1)); 
				}
			}
			//상 하 연결 
			else if(map[x][y]==2) {
				for(int d=0; d<2; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0|| !isConnect(x,y,d)) continue;
					visited[nx][ny] = true; 
					que.add(new Point(nx, ny, time+1)); 
				}
			}
			else if(map[x][y]==3) {
				for(int d=2; d<4; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0|| !isConnect(x,y,d)) continue;
					visited[nx][ny] = true; 
					que.add(new Point(nx, ny, time+1)); 
				}
			}
			else if(map[x][y]==4) {
				for(int d=0; d<4; d+=3) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0 || !isConnect(x,y,d)) continue;
					visited[nx][ny] = true; 
					que.add(new Point(nx, ny, time+1)); 
				}
			}
			else if(map[x][y]==5) {
				for(int d=1; d<4; d+=2) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0|| !isConnect(x,y,d)) continue;
					visited[nx][ny] = true; 
					que.add(new Point(nx, ny, time+1)); 
				}
			}
			else if(map[x][y]==6) {
				for(int d=1; d<3; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0|| !isConnect(x,y,d)) continue;
					visited[nx][ny] = true; 
					que.add(new Point(nx, ny, time+1)); 
				}
			}
			else {
				for(int d=0; d<4; d+=2) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					
					if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny]==0|| !isConnect(x,y,d)) continue;
					visited[nx][ny] = true; 
					que.add(new Point(nx, ny, time+1)); 
				}
			}
			
		}
		return cnt; 
	}//end of bfs
	//x,y 위치에서 dir 방향으로 연결되어 있는지 
	private static boolean isConnect(int x, int y, int dir) {
		int nx= x+dx[dir];
		int ny= y+dy[dir];
		
		int next = map[nx][ny]; 
		
		//x,y 방향에서 상 방향으로 이동 
		if(dir == 0) {
			if(next == 1 | next == 2 | next == 5 | next ==6) return true; 
			return false; 
		}
		//아래방향
		else if(dir ==1) {
			if(next ==1 | next== 2 | next == 4 | next == 7) return true;
			return false; 
		}
		//왼쪽
		else if(dir==2) {
			if(next ==1 | next ==3| next==4 | next ==5) return true;
			return false;
		}
		else {
			if(next==1|next==3|next==6|next==7) return true;
			return false;
		}
		
	}
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M; 
	}//end of isOut
}//end of class 
