package study;

import java.util.*;
/*
 * 배열로 입력받자, 앞칸 하나씩 사라지는 거는 방문처리 변경 
 * bfs 
 * 한칸 앞, 한칸뒤, 옆+k 칸의 경우의 수 다 해보자 
 * */
public class BaekJoon_Solution_15558_점프게임_실버1 {
	private static int[][] map;
	private static boolean[][] visited;
	private static int k;
	private static int N;

	static class Point{
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N= sc.nextInt();
		k= sc.nextInt();
		
		map = new int[2][N+1]; 
		visited = new boolean[2][N+1]; 
		for(int i=0; i<2; i++) {
			String s=sc.next();
			for(int j=1; j<=N; j++) {
				map[i][j] = s.charAt(j-1)-'0'; 
			}
		}
		
		int res =bfs(0,1);
		System.out.println(res);
	}//end of main 

	private static int bfs(int x, int y) {
		Queue<Point> que= new LinkedList<>();
		que.add(new Point(x,y));
		visited[x][y]= true;
		int time=0; 
		while(!que.isEmpty()) { //   (0,1) - (0,2) (0,0) (1,4)
			int size = que.size(); // 시간 체크를 해야되기 때문에 시간별로 이동 
			time++; 
			
			for(int s=0; s<size; s++) { // 젤 처음 for -> 1초대에 일어나는 일 
				Point now = que.poll();
				
				int r = now.r;
				int c = now.c;

				// 한칸앞으로, 한칸뒤로, 옆줄k칸이동
				int[][] next = {{r,c+1}, {r,c-1}, {(r+1)%2, c+k}}; // r%1, c+k
				
				for(int i=0; i<3; i++) {
					int nr = next[i][0];
					int nc = next[i][1];
					
					if(nc > N) return 1;
					
					//0이하이거나, 방문했거나, 갈수 없는 곳 
					if(nc<=0 || visited[nr][nc] || map[nr][nc]==0) continue;
					
					// 현재시간과 같은 것은 곧 사라질 거기 떄문에, 넣지 말것 
					if(nc==time) continue;
					
					visited[nr][nc] = true;
					que.add(new Point(nr,nc));

				}
				
			}
			// 시간에 따른 칸 삭제 
			visited[0][time] = true;
			visited[1][time] = true;
		}
		return 0;
		
	}
}
