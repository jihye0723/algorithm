import java.util.*;
import java.io.*;

public class Main {
	
	private static int N,M; //격자의 크기 
	private static int[][] map; //백준이 운전할 지도 1은 벽 
	private static int bx, by; //백준 처음 시작 위치 
	private static int gas; //가스 
	private static List<int[]> arrive = new ArrayList<>(); //각 승객들의 도착지 저장 (출발점-2)가 인덱스 
	
	//손님 정보 
	static class Point implements Comparable<Point>{
		//승객이 있는 곳 
		int x;
		int y;
		int dis; //택시기사로부터의 거리 
		int gas;//각 위치에서의 가스 양 
		
		public Point(int x, int y ,int dis, int gas) {
			this.x=x;
			this.y=y;
			this.dis=dis;
			this.gas= gas;
		}

		@Override
		public int compareTo(Point o) {
			if(this.dis==o.dis) {
				if(this.x==o.x) {
					return this.y-o.y;
				}
				return this.x-o.x;
			}
			return this.dis - o.dis; 
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		N= Integer.parseInt(st.nextToken()); 
		M= Integer.parseInt(st.nextToken());//태워야 하는 손님의 수 
		gas= Integer.parseInt(st.nextToken());//이거 int에 저장해도 되나 ? 
		
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken()); 
			}
		}
		
		st=new StringTokenizer(br.readLine(), " ");
		bx= Integer.parseInt(st.nextToken())-1;
		by= Integer.parseInt(st.nextToken())-1; //백준의 현재 위치 
		
		int index=2; 
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			//승객들의 출발지를 2~ 저장. 모든 출발지는 서로 다르다 했으므로 
			int x= Integer.parseInt(st.nextToken())-1;
			int y= Integer.parseInt(st.nextToken())-1; 
			map[x][y] = index++; 
			int ax=Integer.parseInt(st.nextToken())-1; 
			int ay=Integer.parseInt(st.nextToken())-1; 
			arrive.add(new int[] {ax, ay}); 	
		}

		simulate();
	}//end of main 
	private static void simulate() {

		boolean wrong= false; 
		//승객 한명 처리 
		 while(M-->0) {
			 //승객 데리러 가기 (몇번 승객 만났는지 리턴)
			 int cusnum= dfsUp(bx,by); 
			 //태울 수 있는 승객 없거나, 가스 다씀 
			 if(cusnum == -1) {
				 wrong=true;
				 break; 
			 }
			 //태운 승객의 도착지 
			 int ax= arrive.get(cusnum-2)[0];
			 int ay= arrive.get(cusnum-2)[1]; 
			 //승객 내려주러 간다. 
			 int res=dfsDown(bx,by,ax,ay); 
			 if(res == -1) {
				 wrong=true;
				 break; 
			 }
			 
		 }
		 
		 if(wrong) System.out.println(-1);
		 else System.out.println(gas);
	}
	
	private static int dx[]= {-1,1,0,0};
	private static int dy[]= {0,0,-1,1};
	
	/*가장 가까운 거리에 있는 승객 찾으러 가기 ,어떤 승객 만났는지 리턴 */
	private static int dfsUp(int x, int y) {
		PriorityQueue<Point> que= new PriorityQueue<>();
		boolean visited[][] = new boolean[N][N]; 
		
		visited[x][y] = true; 
		que.add(new Point(x,y,0,gas)); 
		
		while(!que.isEmpty()) {
			Point c= que.poll();
			int cx = c.x;
			int cy = c.y;
			int cdis=c.dis;
			int cgas =c.gas;
			
			//if(cgas<1) {
				//return -1; //가스 없어 
			//}
			//승객 만나면, 
			if(map[cx][cy] > 1) {
				gas=cgas; 
				int ans=map[cx][cy];
				map[cx][cy]=0; //해당 위치 0으로 손님 태웠음 
				bx=cx; by=cy; 
				return ans;
			}
			
			for(int dir=0; dir<4; dir++) {
				int nx= cx+dx[dir];
				int ny= cy+dy[dir];
				
				//경계, 방문, 벽
				if(isOut(nx,ny) || visited[nx][ny] || map[nx][ny]==1) continue; 
				
				visited[nx][ny]=true;
				que.add(new Point(nx,ny,cdis+1,cgas-1)); 
			}
			
		}
		return -1; 
	}
	
	/*승객 도착지에 내려주기 현재 위치, 도착 위치*/
	private static int dfsDown(int x, int y, int ax, int ay) {
		PriorityQueue<Point> que= new PriorityQueue<>();
		boolean visited[][] = new boolean[N][N]; 
		
		visited[x][y] = true; 
		que.add(new Point(x,y,0,gas)); 
		
		while(!que.isEmpty()) {
			Point c= que.poll();
			int cx= c.x;
			int cy= c.y;
			int cdis= c.dis;
			int cgas =c.gas;

			//도착지에 갔을 경우 
			if(cx==ax && cy==ay) {
				if(cgas<0) return -1; 
				gas=(cgas+(cdis*2)); 
				bx=cx; by=cy; // 백준 위치 옮겨주기 
				return 0; 
			}

			
			for(int dir=0; dir<4; dir++) {
				int nx= cx+dx[dir];
				int ny= cy+dy[dir];
				
				//경계, 방문, 벽
				if(isOut(nx,ny) || visited[nx][ny] || map[nx][ny]==1) continue; 
				
				visited[nx][ny]=true;
				que.add(new Point(nx,ny,cdis+1,cgas-1)); 
			}
		}
		return -1;
	}
	
	//경게밖?
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>=N; 
	}
}//end of class 
