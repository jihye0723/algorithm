import java.util.*;
import java.io.*;
	
public class Main {
	static class Node{
		int node;
		int time;
		
		public Node(int node, int time) {
			this.node= node;
			this.time= time;
		}

	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken()); //수빈이의 위치 
		int K= Integer.parseInt(st.nextToken()); //동생의 위치 
		
//		int time=0; 

		int res= findMin(N, K); 
		System.out.println(res);
		
	}
	private static int findMin(int N, int K) {		
		int[] visited = new int[100001]; //몇초에 방문했는지 
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		Queue<Node> que= new LinkedList<>();
		visited[N]=0; 
		que.add(new Node(N, 0)); 
		
		
		while(!que.isEmpty()) {
			Node n = que.poll();
			int node= n.node;
			int time= n.time;
			
			for(int next : new int[]{node*2, node+1, node-1}) {
				if(isOut(next)) continue; 
				
				if(next == node*2) {
					if(visited[next] > time) {
						visited[next]= time;
						que.add(new Node(next, visited[next])); 
					}
				}
				else {
					if(visited[next] > time+1) {
						visited[next] = time+1;
						que.add(new Node(next, visited[next])); 
					}
				}
				
			}
			
		}
		return visited[K]; 
	}
	static boolean isOut(int x) {
		return x<0 || x> 100000; 
	}
}//end of class 
