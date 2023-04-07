import java.util.*;
import java.io.*;
/*
 *조합 : 최대 M개라고는 말하지만, 걍 M개 찾으면 된다.
 *각 집에서 최소 위치에 있는 치킨집 거리를 찾을 거기 때문에 멀리 있으면 선택안됨*/
public class Main {
	private static int N;
	private static int map[][];
	private static List<int[]> home; 
	private static List<int[]> chicken; //치킨집 정보 
	private static int min= Integer.MAX_VALUE; //도시 치킨 거리의 최솟값 
	
	private static boolean selected[]; //치킨집 조합에 선택되는지 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine(), " ");
		N= Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken()); //폐업시키지 않을 치킨집  갯수
		map=new int[N][N]; //도시 
		
		home= new ArrayList<>(); 
		chicken=new ArrayList<>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine() , " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if(map[i][j]==1) home.add(new int[] {i,j}); 
				else if(map[i][j]==2) chicken.add(new int[] {i,j});
			}
		}
		selected= new boolean[chicken.size()]; 
		
		combi(0, M); 
		System.out.println(min);
		
	}//end of main 
	
	//주어진 치킨집 중에 M개 구하기 
	private static void combi(int index, int M) {
		//주어진 치킨집 최대 갯수만큼 뽑은 것 
		if(M==0) {
			//처음에 bfs 썼는데 그럴 필요 없음 
			//각 좌표 주어진 걸로 계산 
			int res= calDis(); 
			min = Math.min(res, min); 
		}
		
		for(int i=index; i<chicken.size(); i++) {
			selected[i]=true;
			combi(i+1, M-1);
			selected[i]=false; 
		}
	}
	//선택된 M개의 치킨집과 집들과의 최소거리 구하기 (selected에 선택된 치킨집 있음) 
	private static int calDis() {
		int min_dis=0; 
		for(int i=0; i<home.size(); i++) {
			int hx= home.get(i)[0];
			int hy= home.get(i)[1]; 
//			System.out.println("탐색하는 집 : "+hx+" "+hy);
			int hmin= Integer.MAX_VALUE; //해당 집에서 치킨집까지의 최소거리 
			for(int j=0; j<selected.length; j++) {
				//선택된 치킨집들 
				if(selected[j]) {
					int cx= chicken.get(j)[0];
					int cy= chicken.get(j)[1];
					//집과 치킨집 사이의 거리 
					int dis= Math.abs(cx-hx)+Math.abs(cy-hy);
//					System.out.println("탐색하는 치킨 : "+cx+" "+cy+" "+dis );
					hmin = Math.min(hmin, dis); 
				}
			}
			min_dis += hmin; 
		}
		return min_dis; 
	}
	
}//end of class
