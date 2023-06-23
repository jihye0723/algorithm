import java.util.*;
import java.io.*;
	
public class Main {
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); 
	static int N; 
	static boolean isCycle[]; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());//역의 갯수 		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<Integer>()); 
		}
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 

			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		isCycle = new boolean[N+1]; 
		for(int i= 1; i<=N; i++) {
			if(dfs(i,i,i)) break; //사이클 만나면 종료 
		}


		StringBuilder sb= new StringBuilder(); 
		for(int i=1; i<=N; i++) {
			if(isCycle[i]) 
				sb.append(0); 
			else sb.append(bfs(i)); 
			sb.append(" "); 
		
		}
		
		System.out.println(sb);
		
	}//end of main 
	
	static boolean dfs(int prev, int node, int start) {
		isCycle[node] = true; 
		
		for(int next: list.get(node)) {
			if(!isCycle[next]) {
				if(dfs(node, next, start)) return true; 
			}
			else {
				//이미 방문했던 곳인데, 시작점을 만났을 때, 바로 이전 정점이 아닐때 
				//next==prev 이면, 1-3-1 과 같이 바로 돌아온 경우 (순환 x ) 
				if(next != prev && next ==start) return true; 
			}
		}
		isCycle[node]=false; //사이클 못났으면 다시 false 로 바꿔주기 
		return false; 
	}
	
	static class Node implements Comparable<Node>{
		int node; 
		int dis; 
		public Node(int node, int dis) {
			this.node= node;
			this.dis= dis; 
		}
		@Override
		public int compareTo(Node o) {
			return this.dis -o.dis; 
		}
		
	}
	//i 노드에서 순환까지의 최소 거리 찾기 
	static int bfs(int node) {
		boolean visited[] = new boolean[N+1]; 
		PriorityQueue<Node> que =new PriorityQueue<>(); 
		que.add(new Node(node, 0)); 
		visited[node] = true; 
		
		while(!que.isEmpty()) {
			Node now_node = que.poll();
			int now= now_node.node;
			int dis= now_node.dis;
			
			//순환 만남 
			if(isCycle[now]) return dis; 
			
			for(int i=0; i<list.get(now).size(); i++) {
				int next= list.get(now).get(i); 
				
				if(visited[next]) continue; 
				
				visited[next] = true; 
				que.add(new Node(next, dis+1)); 
			}
		}
		return -1; 
	}
}//end of class 
