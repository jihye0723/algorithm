import java.util.*;
import java.io.*;

public class Main {
	static int r,c;
	static int map[][]; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		r= Integer.parseInt(st.nextToken());
		c= Integer.parseInt(st.nextToken()); 
		
		int n = Integer.parseInt(st.nextToken()); 
		
		map = new int[r][c];
		
		for(int i=0; i<r; i++) {
			String s= br.readLine(); 
			for(int j=0; j<c; j++) {
				char ch= s.charAt(j);
				if(ch=='.') map[i][j] =0;
				else map[i][j] = 3; 
			}
		}
		
		start(n); 
		print(); 
	}
	private static void print() {
		StringBuilder sb= new StringBuilder(); 
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] == 0) sb.append('.'); 
				else sb.append('O'); 
			}
			sb.append("\n"); 
		}
		System.out.println(sb);
	}
	private static void start(int n) {
		timeGo(); 
		n--; 
		if(n==0) return; 
		
		while(true) {	
			plusBomb();
			n--; 
			if(n==0) return; 
	
			Bomb();
			n--; 
			if(n==0) return; 
		}


	}
	
	//봄버맨은 아무것도 하지 않지만, 그냥 폭탄있는 곳의 시간이 1초씩 줄어들게 
	private static void timeGo() {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] > 0) {
					map[i][j]--; 
				}
			}
		}
	}
	private static int dx[]= {-1,1,0,0};
	private static int dy[]= {0,0,-1,1}; 
	//3초가 지나서 터질 폭탄이 있다면 터트리기 
	private static void Bomb() {
		Queue<int[]> bomb = new LinkedList<int[]>(); 
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				map[i][j]--; 
				//터져야 할 폭탄(4방에 있는거 같이) 
				if(map[i][j]==0) {
					for(int d=0; d<4; d++) {
						int ni = i+dx[d];
						int nj = j+dy[d]; 
						if(isOut(ni,nj)) continue;
						bomb.add(new int[] {ni,nj}); //인접한거 같이 터트리기 
					}
				}
			}
		}
		
		while(!bomb.isEmpty()) {
			int[] b = bomb.poll();
			map[b[0]][b[1]] = 0; 
		}
	}
	//폭탄이 설치되어 있지 않은 칸에 폭탄 설치
	private static void plusBomb() {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] == 0) map[i][j] = 3;
				else map[i][j]--; 
			}
		}
	}
	
	private static boolean isOut(int i, int j) {
		return i<0 || i>=r || j<0 || j>=c; 
	}
	
}//end of class 
