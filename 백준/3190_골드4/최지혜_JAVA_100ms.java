package samsung;

import java.io.*;
import java.util.*;
/*
 * 2차원 배열 만들고, 
 * 사과가 있는 곳은 1로 변경, 
 * 뱀이 지나가는 곳을 리스트에 넣는다, 꼬리가 잘릴때마다  젤 앞에꺼 빼기 */
public class 골드4_뱀 {
	static int dx[]= {0,1,0,-1};
	static int dy[]= {1,0,-1,0}; // 우 하 좌 상
	static int[][] map;
	static int[][] dir;
	static int N , L; 
	static int time=0;
	static List<int[]> snake = new ArrayList<>(); 
	
	public static void main(String args[]) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		N = Integer.parseInt(br.readLine()); 
		map= new int[N][N];
		
		int K = Integer.parseInt(br.readLine());
		while(K-- >0) {
			st= new StringTokenizer(br.readLine(), " ");
			int r= Integer.parseInt(st.nextToken())-1; 
			int c= Integer.parseInt(st.nextToken())-1; 
			
			map[r][c]=1;
		}
		L = Integer.parseInt(br.readLine());
		dir = new int[L][2]; 
		for(int i=0; i<L; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			dir[i][0] = Integer.parseInt(st.nextToken()); // 몇초후에 ? 
			char  c =st.nextToken().charAt(0); 
			
			dir[i][1] = c=='D' ? 1 : -1; // 오른쪽이면 1, 왼쪽이면 -1
		}
		
		snake.add(new int[] {0,0});
		
		
		get(0,0,0,0);
		System.out.println(time);
		
		
		
	} // end of main 
	private static void get(int r, int c, int count,  int d) {
		
		while(true) {
			time++;
			int nr = r+dx[d];
			int nc = c+dy[d]; 
			// 벾에 부딪혔거나 몸이랑 부딪혔음
			if(isOut(nr,nc)) break;
		
			snake.add(new int[] {nr,nc});
			if(map[nr][nc] == 1) {
				map[nr][nc] = 0;// 사과없애기 
			}
			
			else if(map[nr][nc] == 0) {
				snake.remove(0);
			} 
		
			
			// 방향 바꿔주기 
			if(count < L && dir[count][0]==time) {
				d = (d+dir[count][1])%4;
				if(d==-1) d=3;
				count += 1;
			}
			r= nr; c= nc;
			
		} // end of while 
		
		return; 
	}
	
	private static boolean isOut(int r, int c) {
		// 벽에 부딪혔음 
		if(r<0 || r>=N || c<0 || c>=N) return true;
		for(int i=0; i<snake.size(); i++) {
			if(snake.get(i)[0] == r && snake.get(i)[1] ==c) return true; 
		}
		return false; 
	}
}// end of class 
