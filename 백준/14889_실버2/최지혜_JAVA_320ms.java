package samsung;

import java.io.*;
import java.util.*;

public class 실버2_스타트와링크 {
	static int N;
	static boolean[] isTeam; 
	static int[][] map; 
	static int Min = Integer.MAX_VALUE;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		N= Integer.parseInt(br.readLine()); // 몇명 
		isTeam = new boolean[N]; //T인팀과, F인팀으로 나누기 
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		} // end of input
		
		makeTeam(0, 0); 
		System.out.println(Min);
		
	}

	private static void makeTeam(int i , int count) {
		if(count==N/2) {
			// 각팀의 능력치 계산해서 차이값 계산 
			getCount();	
			return;
		}
		
		for(int n=i; n<N; n++) {
			isTeam[n]=true; 
			makeTeam(n+1, count+1);
			isTeam[n]=false;
				
		}
		
	}
	
	private static void getCount() {
		int start_team=0;
		int link_team=0;
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(isTeam[i] && isTeam[j]) {
					start_team+=map[i][j];
					start_team+=map[j][i];
				}
				else if(!isTeam[i] && !isTeam[j]) {
					link_team+=map[i][j];
					link_team+=map[j][i];
				}
			}
		}
		
		int diff= Math.abs(start_team -link_team); 
		
		if(diff==0) {
			System.out.println(diff);
			System.exit(0);
		}
		Min = Math.min(Min, diff); 
		
		
	}
}
