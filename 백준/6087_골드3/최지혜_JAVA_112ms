import java.io.*;
import java.util.*;
/**
 * 최단 거리를 구하자 → BFS 
 * 방문 배열에는, 해당 지점까지 필요한 거울 갯수를 저장 
 * 	i ) 다음번에 더 많은 거울을 필요로 한 경로로 온다면, 무시 
 * ii ) 더 적은 거울 갯수로 들어온다면, visited 배열의 값 갱신 , 큐에 넣고 !! 
 * 
 * 1) 길 
 * 	들어온방향과, 그 전 방향 비교 후 거울 갯수 조정, 큐에 지점 삽입 후, 방문배열에 거울 갯수 저장 
 *  
 * 2) 경계나 벽 : 다음 방향으로 
 */
public class Solution_BaekJoon_6087_레이저통신_골드3 {
	static class Point implements Comparable<Point>
	{
		int px;
		int py;
		int dir; // 해당 좌표에 들어온 방향 (처음에는 -1로 ) 
		int mirror; //해당 지점까지의 거울 갯수 

		public Point(int px, int py, int dir, int mirror) {
			this.px = px;
			this.py = py;
			this.dir=dir;
			this.mirror = mirror;
		}

		@Override
		public int compareTo(Point o) {
			return this.mirror-o.mirror;

		}
	}
	
	static int dx[] = {-1, 0 , 1, 0}; // 상 우 하 좌  
	static int dy[] = {0 , 1 , 0, -1};
	private static int[][] visited ; // 해당 지점 들어올때의 거울 갯수 저장 
	private static char[][] map;
	private static int W;
	private static int H; 
	private static int endx, endy; 
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String s= br.readLine();
		st= new StringTokenizer(s, " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		visited = new int[H][W]; 
		for(int i=0; i<H; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		map = new char[H][W];
		List<Integer> cloc = new ArrayList<Integer>(); // c 위치 x,y,x,y 저장 
		for(int i=0; i<H; i++) {
			String line = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'C') {
					cloc.add(i);
					cloc.add(j); 
					
				}
			}
		}
		/* --------- end of input --------- */ 
		int startx=cloc.get(0); 
		int starty=cloc.get(1);
		// 도착 지점 
		endx = cloc.get(2);
		endy = cloc.get(3);
		int res = bfs(startx, starty);
		System.out.println(res);
		
		
	}// end of main 

	private static int bfs(int startx, int starty) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(startx, starty, -1,0 ));
		visited[startx][starty]= 0;
		
		while(!pq.isEmpty()) {
			Point now  = pq.poll();
			int x= now.px;
			int y= now.py;
			int d= now.dir; // 현 지점에 들어온 방향 
			int m= now.mirror; // 현지점까지의 거울 갯수
			if(x==endx && y==endy) {
				return m; // 우선순위 큐에 의해 가장 작은 거울 수 리턴 
			}
			
			for(int i=0; i<4; i++) {
				int nx= x+dx[i];
				int ny= y+dy[i];
	
				//경계나 벽이면, 다음 방향으로 
				if(isOut(nx,ny) || map[nx][ny] == '*') continue; 
				//길이나 도착지 → 방향으로 거울갯수 , 큐에 삽입 후 방문체크 
				if(map[nx][ny]=='.' || map[nx][ny] == 'C') {
					int mirror = m;
					//출발지점이 아닌데, 방향이 바꼈으면, 거울 추가 
					if(d != -1 && i!=d) {
						mirror++;
					}
					if(visited[nx][ny] < mirror) continue; // 거울갯수가 더 많은 경우에는 필요 x 
					//더 적은 거울 갯수인 경우에는, visited 에 거울 갯수 갱신하고 큐 저장  
					visited[nx][ny] = mirror; 
					pq.add(new Point(nx, ny, i, mirror));
				}
				
			}
		}
		return -1;
		
	}
	//경계밖으로 나갔으면 true 리턴 
	private static boolean isOut(int x, int y) {
		return (x<0 || y<0 || x>=H || y>=W);
	}
} // end of class 
