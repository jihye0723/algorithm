import java.util.*;
import java.io.*;

//각 도시에서 갈 수 있는 면접장의 최소 거리 찾기 (다익스트라) - 시간초과 났음 
//면접장 기준에서 각 도시까지의 최단거리를 구해서, 그 중 가장 긴거 찾기 
//모든 면접장별로 모든 도시를 탐색x - 시간초과 
//면접장들을 모두 큐에 넣고 다익스트라 시작, 각 도시까지 더 가까운 면접장들 추릴 수 있음 

public class Main {
	static int N;
	static List<List<City>> list = new ArrayList<>();
	/*for dijkstra*/
	static PriorityQueue<City> que= new PriorityQueue<>();
	static boolean visited[];
	static long dis[]; 
	
//	static void makeEdge(int a, int b, int c) {
//		list.get(a).add(new City(b,c)); 
//	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " "); 
		N= Integer.parseInt(st.nextToken());//도시의 수 
		int M= Integer.parseInt(st.nextToken());//도로의 수 
		int K= Integer.parseInt(st.nextToken());//면접장의 수 
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>()); 
		}
				
		while(M-- > 0) {
			st= new StringTokenizer(br.readLine(), " ");
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c= Integer.parseInt(st.nextToken()); 
//			makeEdge(b,a,c); //면접장에서부터 거리를 측정하기 위해 역방향으로 넣기 
			list.get(b).add(new City(a,c)); 

		}
		
		/*다익스트라 노드탐색체크 , (면접장들로부터)최단거리 저장 */
		visited=new boolean[N+1]; 
		dis=new long[N+1]; 
		Arrays.fill(dis, Long.MAX_VALUE);
		
		st=new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<K; i++) {
			int arrive= Integer.parseInt(st.nextToken()); //면접장 
//			dis[arrive]=0; 
			que.add(new City(arrive, dis[arrive]=0)); 
		}
		dijkstra(); 
	}//end of main 
	
	static class City implements Comparable<City>{
		int num;
		long distance;
		
		public City(int num, long distance) {
			this.num=num;
			this.distance=distance; 
		}

		@Override
		public int compareTo(City o) {
			return (int)(this.distance- o.distance);
		}
		
	}
	//면접장에서 각 도시까지의 최단 거리 찾기 (main 함수에서 면접장들 다 que에 넣어왔음) 
	static void dijkstra() {	
		long max = Long.MIN_VALUE; 
		int city_num=0; 
		
		int next;
		long next_dis; 
		while(!que.isEmpty()) {
			City c = que.poll();
			int city = c.num;
			long distance=c.distance; 
//			System.out.println(city+" "+distance);
//			if(dis[city] < distance) continue; 
			if(visited[city]) continue;
			visited[city]=true;
			for(int i=0; i<list.get(city).size(); i++) {
				City now = list.get(city).get(i); 
				next= now.num; //지금꺼랑 연결되어있는도시 
				next_dis= now.distance+distance; //연결 거리 
				if(dis[next] > next_dis) {
					dis[next]=next_dis;
					que.add(new City(next, dis[next])); 
				}
			}
 		}
		
		for(int i=1; i<=N; i++) {
			if(max < dis[i]) {
				city_num = i;
				max=dis[i];
			}
		}
		System.out.println(city_num+"\n"+max);
	}//end of dijkstra
}
