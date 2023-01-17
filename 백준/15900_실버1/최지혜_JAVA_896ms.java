package study;

import java.util.*;
import java.io.*;

/*
 * dfs로 깊이 우선 탐색 ( 1 부터 시작 ) 
 * 단말노드 : 이어진 노드가 1개 뿐이고, 그 노드가 부모노드이다.*/
public class BaekJoon_Solution_15900 {
	static ArrayList<Integer>[] list ; 
	static int res =0 ;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1]; // 1~N
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>(); 
		}
		
		// 간선 정보 
		for(int i=1; i<N; i++) {
			String s= br.readLine();
			StringTokenizer st= new StringTokenizer(s, " ");
			int a = Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a); 
		}
		
		//루트 노드인 1에서 부터 시작
		dfs(1, -1, 0); 
		
		System.out.println((res%2==0)?"No":"Yes");
		
	}
	
	private static void dfs(int node, int parent, int count) { // 현재 노드, 부모노드 , 해당 노드까지의 깊이
		
		//단말노드
		if(list[node].size()==1 && list[node].get(0)==parent) {
			res+=count; 
			return ; 
		}
		
		for(int val : list[node] ) {
			if( val == parent ) continue;
			dfs(val, node, count+1); 
		}
		
	}
}
