import java.util.*;
import java.io.*;

public class Main {
	static List<ArrayList<Integer>> child = new ArrayList<>(); // 자식 노드 저장 
	static int root; //루트 노드 
	static int erase;
	static int cnt=0;
	static int parent[]; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		int N= Integer.parseInt(br.readLine()); //노드의 갯수 
		for(int i=0; i<N; i++) child.add(new ArrayList<>()); 
		parent = new int [N]; 
		
		st= new StringTokenizer(br.readLine()); 
		for(int i=0; i<N; i++) {
			int p = Integer.parseInt(st.nextToken()); //현재 i노드의 부모 
			parent[i] = p; 
			if(p == -1) {
				root = i; //루트노드 
				continue;
			}
			child.get(p).add(i); 
		}
		
		erase= Integer.parseInt(br.readLine()); //지울 노드 
		if(parent[erase] == -1) {
			System.out.println(0);
			return; 
		}
		child.get(parent[erase]).remove(child.get(parent[erase]).indexOf(erase)); 
		child.get(erase).clear(); 
		
		dfs(root);
		System.out.println(cnt);
	}//end of main
	
	private static void dfs(int node) {
		if(child.get(node).size() == 0) {
			cnt++;
			return; 
		}
		else {
			for(int next: child.get(node)) {
				dfs(next);		
			}
		}
		
	}
}//end of class 
