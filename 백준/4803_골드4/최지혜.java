import java.util.*;
import java.io.*;
	
public class Main {
	static boolean[] isConnected; //각 노드가 트리에 포함되어있는지 확인(순환 있는지 체크하기 위해) 
	static List<ArrayList<Integer>> list; 
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		StringBuilder sb= new StringBuilder(); 

		int testcase =1; 
		int n , m; //정점, 간선의 갯수 
		int tree; //트리 갯수 
		while(true) {
			tree=0; 
			st= new StringTokenizer(br.readLine(), " "); 
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());                                        
			isConnected =new boolean[n+1]; 
			
			list = new ArrayList<>();
			for(int i=0; i<=n; i++) {
				list.add(new ArrayList<>()); 
			}
			
			//종료 조건 
			if(n==0 && m==0) break; 
			
			//간선 정보 
			while(m-- >0) {
				st= new StringTokenizer(br.readLine(), " ");
				//a와 b사이에 경로가 있음을 의미 
				int a= Integer.parseInt(st.nextToken()); 
				int b= Integer.parseInt(st.nextToken());
				list.get(a).add(b);
				list.get(b).add(a);
			}
			
			//각 노드에서 dfs로 트리 만들 수 있는지 탐색 
			for(int i=1; i<=n; i++) {
				if(isConnected[i]) continue; 
				nodeCnt =0; 
				edgeCnt =0; 
				dfs(i); 
				
				//트리의 조건 : 정점이 n개일때 간선은 n-1개 
				//리스트에 양방향으로 저장했으므로 간선 갯수/2 
				if(nodeCnt-1 == (edgeCnt)/2) tree ++;  
			}
			
			sb.append("Case ").append(testcase++).append(": ");
			//트리 갯수 출력 0
			if(tree==0) sb.append("No trees.");
			else if(tree==1) sb.append("There is one tree."); 
			else sb.append("A forest of "+tree+" trees."); 
			
			sb.append("\n"); 
		}
		
		System.out.println(sb.substring(0, sb.length()-1));
	}//end of main
	
	static int nodeCnt; 
	static int edgeCnt; 
	//node로 부터 시작된 트리가 만들어 졌다면 1, 트리 불가능하면 0
	private static void dfs(int node) {
		isConnected[node] = true;
		nodeCnt++; 
		edgeCnt += list.get(node).size();
		for(int next : list.get(node)) {
			if(isConnected[next]) continue; //이미 트리에 포함 
			dfs(next); 
		}
	}
	
}//end of class 
