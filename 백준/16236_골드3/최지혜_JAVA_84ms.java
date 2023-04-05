import java.util.*;
import java.io.*;
/*
 * 아기상어가 갈 수 있는 곳 중 자기보다 크기가 작은 것이 1개 라면, 그거 먹는다.
 * 갈 수 있는 곳 중 자기보다 크기가 작은것이 여러개라면, 그 중 가까운거 > 젤 위 > 젤 왼쪽 
 * 크기랑 같은 '수' 의 물고기를 먹을 때마다 1씩 증가 ( 1 증가하면 수 초기화 하면됨  ) */
public class Main {
	
	private static int dx[]= {-1,0,1,0};
	private static int dy[]= {0,-1,0,1};
	
	private static int N; 
	/*0:빈칸, 1~6: 물고기 크기, 9:상어위치 */
	private static int[][] map; 
	private static int time=0;
	private static int eat=0; //먹은 물고기 수 
	private static List<Fish> fish = new ArrayList<>(); 
	private static int sx, sy, ssize; //아기상어 위치 ,크기 
	
	static class Fish{
		int x;
		int y;
		int size;
		
		public Fish(int x, int y, int size) {
			this.x=x;
			this.y=y;
			this.size=size;
		}
	}
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int distance;
		
		public Point(int x, int y, int distance) {
			this.x=x;
			this.y=y;
			this.distance=distance;
		}

		@Override
		public int compareTo(Point o) {
			if(this.distance == o.distance) {
				if(this.x==o.x) {
					return this.y-o.y;
				}
				return this.x- o.x;
			}
			return this.distance-o.distance;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
	
		N=Integer.parseInt(br.readLine()); 
		map= new int[N][N];
		for(int i=0; i<N; i++) {
			String s =br.readLine(); 
			for(int j=0, c=0; j<N; j++, c+=2) {
				map[i][j] = s.charAt(c)-'0';
				//물고기라면, 
				if(map[i][j] >=1 && map[i][j]<=6) {
					fish.add(new Fish(i,j,map[i][j])); 
				}
				else if(map[i][j]==9) {
					sx=i; sy=j;
				}
			}
		}
		ssize=2; 
		start(sx, sy); 
		
	}//end of main 
	private static void start(int sx, int sy) {
		int sharkx= sx; int sharky=sy; 
		while(true) {
//			System.out.println("===============================");
//			System.out.println("시작!! 상어의 위치 :"+ sharkx+ " "+sharky);
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			int dis= eatFish(sharkx, sharky); //물고기를 먹을 수 있는 거리 
			//먹은 물고기가 있음  
			if(dis!=-1) {
				map[sharkx][sharky]=0; //상어가 있던 곳은 빈칸으로 
				sharkx= result[0]; sharky=result[1]; //상어 위치 이동 
				time+=dis; 
				eat++; //먹은 물고기 증가 
				
				if(eat==ssize) {
					ssize++;
					eat=0; 
				}
			}
			//먹은 물고기 없음 
			else break; 
//			
//				System.out.println("먹을 물고기 : "+sharkx+" "+sharky);
//				System.out.println("얼마나 멀리 있는지 : "+dis);
//				System.out.println("상어의 크기 : "+ssize);
			
		}
		
		System.out.println(time);
		
	}
	
	static int result[]= new int[2]; //바뀐 상어의 위치 
	//최소 거리에 있는 먹을 물고기 찾기 - bfs 
	private static int eatFish(int sx, int sy) {
//		System.out.println("탐색 할 때 상어의 위치 "+ sx +" "+sy);
		PriorityQueue<Point> que = new PriorityQueue<>();
		boolean visited[][] = new boolean[N][N];
		que.add(new Point(sx,sy,0)); //현재 상어의 위치 
		visited[sx][sy]= true; 
		while(!que.isEmpty()) {
			Point p = que.poll();
			int x= p.x;
			int y= p.y;
			int dist= p.distance;
			//물고기 등장 !! 
			if(map[x][y]>0 && map[x][y] < ssize) {
				map[x][y]=9; //거기로 상어가 이동 
				result[0]=x;
				result[1]=y;
				return dist;
			}
			
			for(int dir=0; dir<4; dir++) {
				int nx= x+dx[dir];
				int ny= y+dy[dir];
				
				//경계밖으로 나가거나, 상어보다 크기가 더 큰 물고기가 있다면, 
				if(isOut(nx, ny)|| visited[nx][ny] || map[nx][ny] > ssize) continue; 
				
				visited[nx][ny]= true;
				que.add(new Point(nx,ny,dist+1));
			}
		}
		//먹을 수 있는 물고기 없음 
		return -1; //갈 수 없는 곳 
		
	}
	// 경계 밖으로 나가는지 
	private static boolean isOut(int x, int y)
	{
		return x<0 || x>=N || y<0 || y>=N;
	}
}//end of class 
