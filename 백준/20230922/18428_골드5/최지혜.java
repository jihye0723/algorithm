import java.util.*;
import java.io.*;

public class Main {
	static int N; 
	static char[][] map ; 
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1}; 
	static class Node{
		int x; int y;
		
		public Node(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		N= Integer.parseInt(br.readLine()); 
		map = new char[N][N]; 
		
		for(int i=0; i<N; i++) {
			st =new StringTokenizer(br.readLine() , " "); 
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0); 
			}
		}
		
		//세개 가능한 장애물 수 조합탐색 
		comb(3); 
		System.out.println("NO");
	}
	
	private static void comb(int num) {
		//가능한 3개의 장애물을 모두 세웠음 
		if(num==0) {
			//모든 학생들이 감시에서 피할 수 있는 방법이 있다 ! 
			if(bfs()) {
				System.out.println("YES");
				System.exit(0);
			}
			return; 
		}
			
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'X') {
					map[i][j] = 'O';
					comb(num-1); 
					map[i][j] = 'X'; 
				}
			}
		}
		
		
	}//end of comb 
	
	private static boolean bfs() {
		Queue<Node> que = new LinkedList<>(); 
		char[][] copyMap = new char[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copyMap[i][j] = map[i][j]; 
				
				if(copyMap[i][j] =='T') que.add(new Node(i,j)); 
			}
		}
		
		while(!que.isEmpty()) {
			Node n = que.poll(); 
			int x = n.x;
			int y = n.y;
			
			for(int d=0; d<4; d++) {
				int nx= x;
				int ny= y;
				
				while(true) {
					nx += dx[d];
					ny += dy[d]; 
					
					//경계밖으로 나갔거나, 
					if(isOut(nx, ny) || copyMap[nx][ny] == 'O' || copyMap[nx][ny] == 'T') break;  
					
					//학생 만났으면 NO 
					if(copyMap[nx][ny] == 'S') return false; 
				}

				
			}
		}
		return true; 
	}
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=N; 
	}
}//end of class 
