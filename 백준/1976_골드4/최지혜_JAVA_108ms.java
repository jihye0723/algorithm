package study_17;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_1976 {
	static int[] parent; //각 노드의 부모 노드 
	//n의 최상단 부모 찾기 
	static int find(int n) {
		//루트 노드 일 경우, 
		if(n==parent[n]) return n; 
		return find(parent[n]); 
	}
	static void union(int a, int b) {
		a= find(a); 
		b= find(b); 
		
		//둘이 서로 다른 집합이라면
		if(a!=b) {
			if(a<b) {
				parent[b] = a;
			}
			else parent[a]= b;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //도시의 수 
		int M = Integer.parseInt(br.readLine()); //여행할 도시의 수 

		parent = new int[N+1]; 
		for(int i=1; i<=N; i++) {
			parent[i] = i; 
		}
		int[][] map = new int[N][N]; 
		for(int i=0; i<N; i++) {
			String s =br.readLine(); 
			for(int j=0,c=0; j<N; j++, c+=2) {
				map[i][j] = s.charAt(c)-'0'; 
			}
		}
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(map[i][j]==1) union(i+1, j+1);  
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
		int pre= find(Integer.parseInt(st.nextToken()));
		int next; 
		boolean isNo =false; 
		while(--M >0) {
			next= find(Integer.parseInt(st.nextToken())); 
			if(pre!=next) {
				System.out.println("NO");
				isNo= true; 
				break; 
			}
			pre= next; 
		}
		if(!isNo) System.out.println("YES");
//		for(int i=1; i<=N; i++) {
//			System.out.println(i+"의 부모 " +parent[i]);
//		}
	}//end of main 
}//end of class
