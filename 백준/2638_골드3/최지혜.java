import java.util.*;
import java.io.*;

public class Main {
	static int N,M; 
	static int map[][]; 
	static int cheese= 0; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st = new StringTokenizer(br.readLine() , " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		map = new int[N][M]; 
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if(map[i][j] ==1) cheese ++; //치즈 총 갯수 
			}
		}
		
		int res= start(); 
		System.out.println(res);
	}//end of main
	
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1};
	static boolean visited[][]; 
	static Queue<int[]> melt_que = new LinkedList<>(); //한타임에 녹는 치즈 
	private static int  start() {
		int time=0; 
		
		while(cheese >0) {
			//치즈 외부공간 2로 바꾸기 
			visited = new boolean[N][M]; 
			change(0,0); 
			
			//이번에 지울 치즈 큐에 넣기 
			erase(); 
			
			//지울 치즈 다 지우기 
			while(!melt_que.isEmpty()) {
				int[] melt = melt_que.poll(); 
				map[melt[0]][melt[1]] = 0; 
				cheese--;
			}
//			System.out.println();
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
		
			time++; 
			
		}
		
		return time; 
	}
	
	//외부 공기 2로 바꾸기 
	private static void change(int x, int y) {
		visited[x][y]= true;
		map[x][y]=2; 
	
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			
			if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny] == 1 ) continue;
			
			change(nx, ny); 
		}
	}
	
	
	private static void erase() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					if(isMelt(i,j)) melt_que.add(new int[] {i,j}); 
				}
			}
		}
	}
	//이번에 녹는치즈인지 확인 
	private static boolean isMelt(int x, int y) {
		int cnt =0; 
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(map[nx][ny]==2) cnt++; 
			
			if(cnt >=2) {
				return true; 
			}
		}
		
		return false; 
	}
	//경계밖으로 나가는지 
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=M; 
	}
}//end of class 
