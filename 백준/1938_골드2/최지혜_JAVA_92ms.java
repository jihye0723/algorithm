import java.util.*;
import java.io.*;
	
public class Main {
	static class Point{
		int x; int y;
		public Point(int x, int y) {
			this.x=x;
			this.y=y; 
		}
	}
	
	static class Wood{
		int x; int y; //통나무 중간 위치 
		int dir; //방향 (가로/ 세로) 
		int cnt; //횟수 
		
		public Wood(int x, int y, int dir, int cnt) {
			this.x= x;
			this.y=y;
			this.dir=dir;
			this.cnt= cnt;
		}
	}
	
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};//상 하 좌 우 
	
	static Point[] b_locate; 
	static Point[] e_locate; 
 	static int N; 
	static char[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); 
		map= new char[N][N]; 
		
		b_locate = new Point[3];
		e_locate = new Point[3]; 
		
		int b_index=0, e_index=0; 
		for(int i=0; i<N; i++) {
			String s= br.readLine(); 
			for(int j=0; j<N; j++) {
				map[i][j]= s.charAt(j); 
				if(map[i][j]=='B') b_locate[b_index++] = new Point(i,j); 
				if(map[i][j]=='E') e_locate[e_index++] = new Point(i,j); 
			}
		}
		
		int res= bfs();
		System.out.println(res);
		
	}//end of main 
	
	private static int bfs() {
		Queue<Wood> que= new LinkedList<>(); 
		boolean[][][] visited= new boolean[N][N][2]; // 가로, 세로 나누어서 방문체크 (통나무 중간위치만 방문체크하기) 
		
		//초기 통나무 방향 
		int direction; 
		if(b_locate[0].x == b_locate[1].x) direction=0; 
		else direction=1; 
		
		//큐에 넣는 통나무는 통나무의 '중간 위치' 기준 
		que.add(new Wood(b_locate[1].x , b_locate[1].y, direction, 0)); 
		visited[b_locate[1].x][b_locate[1].y][direction] = true; 
		
		while(!que.isEmpty()) {
			Wood w= que.poll();
			int x= w.x;
			int y =w.y;
			int dir= w.dir;
			int cnt = w.cnt; 
			
			//통나무가 목적지에 도착
			if(x == e_locate[1].x && y == e_locate[1].y) {
				//가로 일경우 
				if(dir==0 && map[x][y-1] == 'E' && map[x][y+1]=='E') return cnt; 
				//세로 일 경우 
				else if(dir==1 && map[x-1][y] =='E' && map[x+1][y]== 'E') return cnt; 
			}
			
			//상 하 좌 우 이동 
			for(int d=0; d<4; d++) {
				int nx= x+dx[d];
				int ny= y+dy[d]; 
				
				//이동할 수 없거나 , 이미 탐색한 경우 
				if(!canMove(dir, d, nx, ny) || visited[nx][ny][dir]) continue; 
				
				visited[nx][ny][dir] = true; 
				que.add(new Wood(nx, ny, dir, cnt+1));
			}
			
			//회전 
			if(canRotate(x, y)) {
				//dir 가 바껴야 한다. 
				dir= (dir+1)%2; 
				if(!visited[x][y][dir]) {
					visited[x][y][dir] = true; 
					que.add(new Wood(x, y, dir, cnt+1)); 
				}
				
			}
			
			
		}
		return 0; 
		
	}// end of bfs 
	
	
	//상,하,좌,우로 옮길 수 있는지 체크 
	//dir : 가로인지 세로인지(0:가로, 1:세로) , d: 이동 방향 (상,하,좌,우)
	//x,y : 이동한 새로운 위치 (통나무의 중간부분) 
	private static boolean canMove(int dir, int d, int x, int y) {
		switch(dir) {
		//통나무가 가로 방향으로 놓여 있을 경우, 
		case 0:
			//상하로 이동할 경우 
			if(d< 2) {
				//x좌표가 범위 밖으로 나가는지 체크 
				if(x<0 || x>=N) return false; 
				//이동한 통나무에 나무가 놓여져 있는지 체크 
				if(map[x][y]=='1' || map[x][y-1] =='1' || map[x][y+1]=='1') return false; 
			}
			//좌우로 이동할 경우
			else {
				//y좌표가 범위 밖으로 나가는지 체크 
				if(y-1<0 || y+1 >=N) return false; 
				//이동한 통나무에 나무가 놓여져 있는지 체크 
				if(map[x][y]=='1' || map[x][y-1] =='1' || map[x][y+1]=='1') return false; 
			}
			break; 
		
		//통나무가 세로 방향으로 놓여져 있을 경우 
		case 1: 
			//상하로 이동할 경우 
			if(d<2) {
				//x좌표가 범위 밖으로 벗어나는지 체크 
				if(x-1 < 0 || x+1>=N) return false; 
				//이동한 위치에 나무가 있a을 경우 
				if(map[x-1][y] =='1' || map[x][y]=='1' || map[x+1][y]=='1' ) return false; 
			}
			else {
				//y좌표가 범위 밖으로 벗어나는지 체크 
				if(y<0 || y>=N) return false; 
				//이동한 위치에 나무가 있을 경우 
				if(map[x-1][y] =='1' || map[x][y]=='1' || map[x+1][y]=='1' ) return false; 
			}
			break; 
		}
		return true; 
	}
	
	//회전할 수 있는지 체크 
	//x,y : 이동할 통나무의 중간부분 (회전할 때 중간은 안바뀜) 
	private static boolean canRotate(int x, int y) {
		//회전할 때 주변 3*3 에 나무가 한그루도 있으면 안됨!! 
		//이거 때문에 계속 틀림, 문제 잘읽기 
		for(int i=x-1; i<=x+1; i++) {
			for(int j=y-1; j<=y+1; j++) {
				if(i<0 || i>=N || j<0 || j>=N ) return false; 
				if(map[i][j]=='1') return false; 
			}
		}
		
		return true; 
	}
}//end of class 
