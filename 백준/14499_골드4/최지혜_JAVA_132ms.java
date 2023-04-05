package samsung;

import java.util.*;
import java.io.*;
/*최악의 경우 : N=20, M=20, 명령 1000개, 지도의 수 = 9 */
public class Solution_BaekJoon_14499 {
	//동 서 북 남 
	private static int dx[]= {0,0,-1,1};
	private static int dy[]= {1,-1,0,0}; 
	
	private static int dice[] = new int[7]; //주사위 (천장:1,뒷면:2,우:3,좌:4,앞면:5,바닥:6) 
	private static int [][] map; 
	private static int [] order;
	private static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		N= Integer.parseInt(st.nextToken()); //지도의 세로
		M= Integer.parseInt(st.nextToken()); //지도의 가로 
		int x= Integer.parseInt(st.nextToken()); //주사위 좌표 
		int y= Integer.parseInt(st.nextToken());
		int K= Integer.parseInt(st.nextToken()); //명령의 갯수
		
		map = new int[N][M]; //지도 
		for(int i=0; i<N; i++) {
			String s =br.readLine(); 
			for(int j=0, c=0; j<M; j++, c+=2) {
				map[i][j]=s.charAt(c)-'0'; 
			}
		}
		
		st=new StringTokenizer(br.readLine()," ");
		order = new int[K];
		for(int i=0; i<K; i++) {
			order[i]=Integer.parseInt(st.nextToken()); 
		}
		start(x,y);
	}//end of main 
	
	private static void start(int x, int y) {
		for(int i=0; i<order.length; i++) {
			int dir= order[i];
			
			int nx= x+dx[dir-1];
			int ny= y+dy[dir-1];
			
			if(isOut(nx,ny)) continue; 
			
			switch(dir) {
			//동쪽 
			case 1:
				goRight();
				break;
			//서쪽
			case 2:
				goLeft();
				break;
			//북쪽
			case 3:
				goUp();
				break;
			//남쪽
			case 4:
				goDown();
				break; 
			}
			
			//이동한 칸이 0이면, 바닥면에 있는 값 복사 
			if(map[nx][ny]==0) {
				map[nx][ny] = dice[6];
			}
			//0이 아닐 경우에는, 칸에 쓰여 있는수가 바닥으로 칸=0
			else {
				dice[6]=map[nx][ny];
				map[nx][ny]=0;
			}
			
			System.out.println(dice[1]);

			//주사위 현재 위치 갱신
			x=nx;
			y=ny;
			
		}
		
	}
	//경계 밖으로 나가는지
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
	//북쪽으로 굴리기 
	private static void goUp() {
		int tmp=dice[2];
		dice[2]=dice[1];
		dice[1]=dice[5];
		dice[5]=dice[6];
		dice[6]=tmp;
	}
	
	//남쪽으로 굴리기 
	private static void goDown() {
		int tmp=dice[6];
		dice[6]=dice[5];
		dice[5]=dice[1];
		dice[1]=dice[2];
		dice[2]=tmp;
	}
	
	//동쪽으로 굴리기 
	private static void goRight() {
		int tmp=dice[3];
		dice[3]=dice[1];
		dice[1]=dice[4];
		dice[4]=dice[6];
		dice[6]=tmp;
	}
	
	//서쪽으로 굴리기
	private static void goLeft() {
		int tmp=dice[4];
		dice[4]=dice[1];
		dice[1]=dice[3];
		dice[3]=dice[6];
		dice[6]=tmp;
	}
}//end of class
