import java.util.*;
import java.io.*;

public class Main {
	static int[][] time; 
	static boolean[] visited;
	static int n; 
	static int min= Integer.MAX_VALUE; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()); //행성의 갯수 
		int start= Integer.parseInt(st.nextToken()); //시작 행성 
		
		time= new int[n][n];
		visited= new boolean[n]; 
		for(int i=0; i<n; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<n; j++) {
				time[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for(int a=0; a<n; a++) { //경유지
			for(int i=0; i<n; i++) { //출발지
				for(int j=0; j<n; j++) { //도착지
					time[i][j] =Math.min(time[i][j], time[i][a]+time[a][j]);
				}
			}
		}
		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(time[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		visited[start]= true; 
		dfs(start, n-1, 0); 
		System.out.println(min);
	}//end of main
	
	private static void dfs(int node, int cnt, int total_time) {
		if(cnt==0) {
			min = Math.min(min, total_time); 
			return; 
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i]= true; 
				dfs(i, cnt-1, total_time +time[node][i]); 
				visited[i]=false; 
			}
		}
	}
}//end of class 
