package study_17;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_11725 {
	static int parent[]; 
	static boolean visited[]; 
	static List<List<Integer>> list = new ArrayList<>();
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int N= Integer.parseInt(br.readLine()); //노드의 갯수 
		parent = new int[N+1];//각 노드의 부모 노드 적을 곳 
		visited=new boolean[N+1]; 
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		StringTokenizer st; 
		for(int i=0; i<N-1; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
	
		start(1); 
		
		for(int i=2; i<=N; i++) {
			System.out.println(parent[i]);
		}
		
	}//end of main
	

	private static void start(int index) {
		visited[index] = true;
		for(int i : list.get(index)) {
			if(!visited[i]) {
				parent[i] = index; 
				start(i); 
			}
		}
	}
}//end of class 
