import java.util.*;
import java.io.*;
	
public class Main {	
	static ArrayList<ArrayList<int[]>> list = new ArrayList<>(); 
	static int N; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		
		N= Integer.parseInt(st.nextToken()); //N개의 동영상 
		int Q= Integer.parseInt(st.nextToken()); //존의 질문 갯수 
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<int[]>()); 
		}
		for(int i=0; i<N-1; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			int c= Integer.parseInt(st.nextToken());
			
			list.get(a).add(new int[] {b,c});
			list.get(b).add(new int[] {a,c}); 
			
		}
		StringBuilder sb= new StringBuilder(); 
		while(Q--> 0) {
			st= new StringTokenizer(br.readLine(), " ");
			int k =Integer.parseInt(st.nextToken()); //유사도가 k이상인 
			int v =Integer.parseInt(st.nextToken()); //보고있는 영상 
			int res= findmin(k,v); 
			sb.append(res).append("\n"); 
		}
		
		System.out.println(sb.substring(0, sb.length()-1));
		
		
	}//end of main 
	
	private static int findmin(int k , int v) {
		boolean[] visited = new boolean[N+1];
		int count=0; 
		Queue<Integer> que = new LinkedList<>();
		que.add(v); 
		visited[v]=true; 
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for(int i=0; i<list.get(now).size(); i++) {
				int next= list.get(now).get(i)[0]; //연결되어 있는 다음 노드 
				int weight = list.get(now).get(i)[1]; //유사도 (가중치) 
				if(weight >=k && !visited[next]) {
					visited[next]= true; 
					que.add(next); 
					count++; 
				}
				
				
			}
		}
		return count; 
	}
}//end of class 
