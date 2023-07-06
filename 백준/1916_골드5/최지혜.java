import java.util.*;
import java.io.*;
	
public class Main {
	static int[][] cost; 
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //도시의 갯수 
		int M = Integer.parseInt(br.readLine()); //버스의 갯수 
		
		cost = new int[N][N]; 
		for(int i=0; i<N; i++) {
			Arrays.fill(cost[i], -1); 
		}
		
		while(M-- >0) {
			st= new StringTokenizer(br.readLine(), " ");
			int a= Integer.parseInt(st.nextToken())-1;
			int b= Integer.parseInt(st.nextToken())-1;
			int c= Integer.parseInt(st.nextToken());
			
			if(cost[a][b] != -1) {
				cost[a][b] = Math.min(cost[a][b], c); 
			}
			else cost[a][b] = c;
			
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int start= Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1; 
		
		int res= dijkstra(start, end); 
		System.out.println(res);

	}
	static class Node implements Comparable<Node>{
		int city;
		int dis;
		
		public Node(int city, int dis) {
			this.city=city;
			this.dis=dis;
		}

		@Override
		public int compareTo(Node o) {
			return this.dis- o.dis;
		}
	}
	static int dijkstra(int start, int end) {
		int[] min= new int[N]; //출발지에서 각 도시까지의 최소비용저장
		Arrays.fill(min, Integer.MAX_VALUE) ;
		boolean[] check= new boolean[N];
		
		PriorityQueue<Node> que= new PriorityQueue<>();
		que.add(new Node(start,0)); 
		
		while(!que.isEmpty()) {
			Node n = que.poll();
			int city = n.city;
			int dis = n.dis;
			
			if(check[city]) continue; 
			
			check[city] =true;
			for(int i=0; i<N; i++) {
				if(cost[city][i] != -1) {
					if(min[i] > cost[city][i] + dis) {
						min[i] = cost[city][i] +dis; 
						que.add(new Node(i, min[i])); 
					}
				}
			}
			
		}
		return min[end]; 
//		for(int i=0; i<N; i++) {
//			System.out.print(min[i]+" ");
//		}
//		System.out.println();
		
	}
}//end of class 
