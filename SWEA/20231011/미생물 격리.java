import java.io.*;
import java.util.*;
/*
 * 가장자리에는 미생물이 없음!
 * 가장자리에 도착하면 미생물의 절반이 죽고 , 이동방향 반대로 (이동은 안함) 
 * 두개가 합쳐지면, 미생물 수는 합쳐지고, 이동방향은 더 많은쪽으로
 * 미생물 수가 같은 경우는 주어지지 않음
 * */
public class Solution {
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1}; //상 하 좌 우 
	static int N, M; 
	static Set<int[]> location = new HashSet<>(); 
	static class Dust{
		int x; int y; 
		int cnt; //미생물 수 
		int dir; //이동방향 
		int max_cnt; //여러개 있을 때 미생물 최댓값 
		
		public Dust(int x, int y, int cnt, int dir, int max_cnt) { 
			this.x=x;
			this.y=y;
			this.cnt =cnt;
			this.dir = dir; 
			this.max_cnt = max_cnt; 
		}
		
	}
	static Queue<Dust> dust_que;
	static Dust[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T = Integer.parseInt(br.readLine());//테스트케이스의 갯수 
		for(int tc=1; tc<=T; tc++) {
			st= new StringTokenizer(br.readLine(), " ");
			res=0; 
			dust_que = new LinkedList<>(); //1초 동안 움직일 큐 
			N= Integer.parseInt(st.nextToken()); //셀의 갯수 
			M= Integer.parseInt(st.nextToken()); //시간 
			map = new Dust[N][N]; 
			int K= Integer.parseInt(st.nextToken()); 
			for(int i=1; i<=K; i++) {
				st= new StringTokenizer(br.readLine(), " ");
				int x= Integer.parseInt(st.nextToken());
				int y= Integer.parseInt(st.nextToken());
				int cnt= Integer.parseInt(st.nextToken()); //미생물 수 
				int dir =Integer.parseInt(st.nextToken())-1; //방향
				
				dust_que.add(new Dust(x,y,cnt,dir,0)); //첫번째에 이동할 아이들 
				
			}
			
			start(0); 
			sb.append("#").append(tc).append(" ").append(res).append("\n"); 
		}//end of testcase 
	System.out.println(sb.substring(0, sb.length()-1));
	}//end of main
	static int res; 
	private static void start(int time) {
		while(time != M) {
		
			move(); 			
			nextMove(); 		
			
			time++; 
		}
		
		//남은 수 세기 
		while(!dust_que.isEmpty()) {
			res += dust_que.poll().cnt; 
		}
		
	}//end of start
	
	//dust_que 에 담겨있는 아이들 옮기기 
	private static void move() {
		Dust d; 
		while(!dust_que.isEmpty()) {
			d = dust_que.poll();
			int dir= d.dir;
			int cnt= d.cnt; 
			
			//이동할 위치 
			int nx = d.x+dx[d.dir]; 
			int ny = d.y+dy[d.dir]; 
			
			//경계로 이동하는 거면, 반으로 바꾸고 방향 바꾸기 
			if(isOut(nx,ny)) {
				if(dir==0 | dir==2 ) dir+=1; 
				else dir-=1; 
				cnt/=2; 
				
				if(cnt <=0) continue; 
			}
			
			if(map[nx][ny] == null) {
				map[nx][ny] = new Dust(nx, ny, cnt, dir, cnt); 
				location.add(new int[] {nx, ny}); 
			}
			//뭔가 값이 있다면, 
			else {
				//저장되어있는 총 값 , 방향 
				int ex_cnt = map[nx][ny].cnt;
				int ex_dir = map[nx][ny].dir; 
				
				int max_cnt = map[nx][ny].max_cnt;
				
				//지금 넣을려는 값이 더 크다면 , 방향 갱신 
				if(max_cnt < cnt) {
					map[nx][ny] = new Dust(nx, ny, cnt+ex_cnt, dir, cnt); 
				}
				else map[nx][ny] = new Dust(nx, ny, cnt+ex_cnt, ex_dir, ex_cnt); 
			}
			
			
		}//end of while 
	}//end of move
	
	//다음 실행을 위해 map 정보를 훑으면서 dust_que에 다음거 넣고, map set 비우기 
	private static void nextMove() {
		for(int[] a : location) {
			int x= a[0];
			int y= a[1]; 
			
			dust_que.add(map[x][y]); 
			map[x][y] =null;
		}
		location.clear(); 
	}
	
	//경계에 놓여있는 미생물인지를 화긴 
	private static boolean isOut(int x, int y) {
		return (x==0 || x==N-1 || y==0 || y==N-1); 
	}
}//end of class 
