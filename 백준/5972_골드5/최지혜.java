import java.util.*;
import java.io.*;

public class Main {

	static int N; 
	static List<ArrayList<Node>> list = new ArrayList<>(); 
	static class Node implements Comparable<Node>{
		int n;
		int cost; 
		
		public Node(int n, int cost) {
			this.n= n;
			this.cost= cost; 
		}

		@Override
		public int compareTo(Node o) {
			return this.cost- o.cost;
		}
	
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>()); 
		}
		
		while(M-- >0) {
			st= new StringTokenizer(br.readLine(), " ");
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c= Integer.parseInt(st.nextToken());
		
			list.get(a).add(new Node(b,c)); //a에서 b로 가는데 c만큼 걸림 
			list.get(b).add(new Node(a,c)); //b에서 a로 가는데 c만큼 걸림 
		}
		
		int res= dijkstra(1); //현서는 1에서 출발 
		System.out.println(res);
		
	}//end of main 
	
	static int dijkstra(int start) {
		int[] min_cost= new int[N+1]; //각 지점까지의 최소 비용 
		Arrays.fill(min_cost, Integer.MAX_VALUE);
		
		min_cost[start] =0; //출발지까지는 0 
		
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.add(new Node(start,0));
		
		while(!que.isEmpty()) {
			Node a =que.poll();
			
			int n =a.n; int cost=a.cost;
			
			if(n==N) return cost; 
			
			for(Node l : list.get(n)) {
				int next= l.n; //이동할 수 있는 곳 
				int ncost = l.cost; //이동 비용 
				
				if(min_cost[next] > cost + ncost) {
					min_cost[next] = cost+ncost;
					que.add(new Node(next, min_cost[next])); 
				}
			}
			
		}
		
		return -1;
	}
}//end of class 
