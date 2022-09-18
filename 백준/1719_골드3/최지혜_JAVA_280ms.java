package study;

import java.io.*;
import java.util.*;

public class Solution_BaekJoon_1719_골드3_택배 {

	private static final int MAX = 123456789;

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		StringBuilder sb= new StringBuilder();
		
		String s = br.readLine();
		st= new StringTokenizer(s, " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int map[][] = new int[n+1][n+1];
		int ans[][] = new int[n+1][n+1]; // 최단 경로의 경유지 저장하는 
		/*최단경로를 구할거기 때문에, 경로가 없는 것은 최댓값으로 초기화*/
		for(int i=1; i<n+1; i++) {
			Arrays.fill(map[i], MAX);
		}

		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				ans[i][j]= j;
			}
		}
		
		while(m-- > 0) {
			String s1 = br.readLine();
			st = new StringTokenizer(s1, " ");
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[a][b]=map[b][a]=weight;
					 
		}
		
	
		for(int r=1; r<=n; r++) { //경유지
			for(int from=1; from<=n; from++) {//출발지
				for(int to=1; to<=n; to++) {// 도착지
					if(map[from][to] > map[from][r]+map[r][to]) {
						map[from][to] = map[from][r]+map[r][to];
						ans[from][to] = ans[from][r]; 
						// ans[from][to] = r;
					}
						
				}
				
			}
		}//end of 플로이드 와샬
		
		for(int a=1; a<=n; a++) {
			for(int b=1; b<=n; b++) {
				if(a==b) {
					sb.append("-").append(" ");
					continue;
				}
				// 경유하지 않는게 더 빠른 것 
				if(ans[a][b]==b) {
					sb.append(b).append(" ");
					continue;
				}
				sb.append(ans[a][b]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
