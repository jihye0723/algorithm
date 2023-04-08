package samsung;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_13460 {
	
	static class marvel{
		int x; int y; 
		public marvel(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	//빨간, 파랑구슬 위치와 몇번움직였는지 
	static class Info implements Comparable<Info>{
		marvel red; 
		marvel blue; 
		int count; 
		
		public Info(marvel red, marvel blue, int count) {
			this.red=red;
			this.blue=blue; 
			this.count=count;
		}

		@Override
		public int compareTo(Info o) {
			return this.count-o.count;
		}
		
		
	}
	private static int N, M;
	private static char[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," " );
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		map = new char[N][M]; 
		
		int rx=0, ry=0, bx=0, by=0; 
		for(int i=0; i<N; i++) {
			String s=br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=s.charAt(j);
				if(map[i][j]=='B') {
					bx=i; by=j; 
				}
				else if(map[i][j]=='R') {
					rx=i; ry=j; 
				}
			}
		}
		int res=bfs(rx,ry,bx,by); 	
		System.out.println(res);
				
	}//end of main 
	
	private static boolean visited[][][][]; //빨간공+파란공 조합 방문체크 
	private static int dx[]= {-1,1,0,0};
	private static int dy[]= {0,0,-1,1};
	
	private static int bfs(int rx, int ry, int bx, int by) {
		Queue<Info> que = new LinkedList<>();
		visited = new boolean[N][M][N][M];
		
		que.add(new Info(new marvel(rx, ry),new marvel(bx,by),0)); 
		visited[rx][ry][bx][by]=true; 
		
		while(!que.isEmpty()) {
			Info i = que.poll();
			int ex_rx= i.red.x;
			int ex_ry= i.red.y;
			int ex_bx=i.blue.x;
			int ex_by=i.blue.y;
			int cnt = i.count;
		
			if(map[ex_rx][ex_ry]=='O') {
				return cnt; 
			}
			
			if(cnt>10) break; 
		
			for(int dir=0; dir<4; dir++) {
				//같은 방향으로 움직이기 
				marvel nred= move(ex_rx,ex_ry,dir);
				marvel nblue=move(ex_bx,ex_by,dir); 
				
				int nrx= nred.x;
				int nry= nred.y;
				int nbx= nblue.x;
				int nby= nblue.y;

				//파란구슬이 구멍에 빠지는 거라면, 이 방향은 안됨 
				if(map[nbx][nby]=='O') continue; 
				
				//만약 같은 곳으로 굴러갔다면, 하나 자리 조정해주기 
				if(nrx==nbx && nry == nby) {
					switch(dir) {
					//위로 올렸을 경우
					case 0:
						//빨간색이 더 밑에 있었음 , 빨간색 한칸 내리기
						if(ex_rx > ex_bx) nrx+=1; 
						else nbx+=1; 
						break; 
					//아래로 내렸을 경우 
					case 1:
						//빨간색이 더 위에 있었음 , 빨간색 한칸 올리기
						if(ex_rx < ex_bx) nrx -=1; 
						else nbx -=1;
						break;
					//왼쪽으로 굴렸을 경우
					case 2:
						//빨간색이 더 오른쪽에 있었음, 빨간색 한칸 오른쪽으로 
						if(ex_ry > ex_by) nry+=1; 
						else nby+=1; 
						break;
					//오른쪽으로 밀었을 경우
					case 3:
						//빨간색이 더 왼쪽에 있었음
						if(ex_ry < ex_by) nry-=1;
						else nby-=1;
						break; 
					}
				}
				
				/*-------정확하게 옮겨졌음 */
				
				if(visited[nrx][nry][nbx][nby]) continue; //이미 이전에 있던 곳 
				
				visited[nrx][nry][nbx][nby]=true;
				que.add(new Info(new marvel(nrx, nry), new marvel(nbx,nby), cnt+1));
				
				
			}//사방탐색
		
		}
		return -1; 
	}
	
	/*공 움직이기*/
	private static marvel move(int x, int y, int dir) {

		int nx=x; int ny=y; 
		while(true) {
			nx+=dx[dir];
			ny+=dy[dir]; 
			
			if(map[nx][ny]=='#') {
				break; 
			}
			
			if(map[nx][ny]=='O') {
				return new marvel(nx,ny);
			}
		}
		
		return new marvel(nx-dx[dir],ny-dy[dir]); 
	}
}//end of class 
