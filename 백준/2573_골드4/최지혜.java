import java.util.*;
import java.io.*;
	
public class Main {
	static int[][] map; 
	static int N,M; 
	static List<int[]> ice_list = new ArrayList<>(); 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " "); 
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken()); 
		
		map = new int[N][M]; 
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if(map[i][j] !=0) ice_list.add(new int[] {i,j}); 
			}
		}
		
		boolean res= check(); 
//		System.out.println(res);
		System.out.println(start()); 
	}
	
	static int dx[]= {-1,1,0,0};
	static int dy[]= {0,0,-1,1};
	static int time=0;
	static Queue<Integer> melt= new LinkedList<>(); //녹는양
	private static int start() {
		while(ice_list.size() >0) {
			//빙산이 분리되었는지 확인 
			boolean res = check(); 
			//분리되었으면 해당 시간 출력 
			if(!res) return time; 
			
			//빙산 녹기 시작 
			for(int i=ice_list.size()-1; i>=0; i--) {
				int x= ice_list.get(i)[0];
				int y= ice_list.get(i)[1]; 
				
				int cnt=0; 
				for(int d=0; d<4; d++) {
					int nx= x+dx[d];
					int ny= y+dy[d]; 
					if(map[nx][ny]==0) cnt ++; 
				}
				melt.add(cnt); 
			}
			
			for(int i=ice_list.size()-1; i>=0; i--) {
				int x= ice_list.get(i)[0];
				int y= ice_list.get(i)[1]; 
				
				int change=  map[x][y]- melt.poll();
				
				if(change <=0) {
					map[x][y]=0;
					ice_list.remove(i); 
				}
				else {
					map[x][y] = change; 
				}
					
			}
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			
//			System.out.println("남은 빙산 갯수 : "+ice_list.size());
			time++; 
		}
		
		return 0; 
	}
	
	//bfs로 탐색 
	private static boolean check() {
		boolean[][] checked = new boolean[N][M]; 
		Queue<int[]> que= new LinkedList<>();
		int check_num =0; 
		
		
		que.add(new int[] {ice_list.get(0)[0], ice_list.get(0)[1]});
		checked[ice_list.get(0)[0]][ice_list.get(0)[1]] = true;
		
		while(!que.isEmpty()) {
			int[] now=  que.poll();
			int x = now[0];
			int y = now[1];
			check_num ++; 
//			System.out.println(x+" "+y+ " "+check_num);
			
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d]; 
				
				if(checked[nx][ny]) continue; 
				
				checked[nx][ny] = true; 
				if(map[nx][ny]==0) continue; 				
				que.add(new int[] {nx,ny}); 
			}
		}
		
		if(check_num < ice_list.size()) return false; 
		 
		return true; 
	}
}//end of class 
