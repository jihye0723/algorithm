package study;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_11403 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		
		int tree[][] = new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<n; j++) {
				tree[i][j] = Integer.parseInt(st.nextToken()); 			}
		}
		
		for(int a=0; a<n; a++) { // 경유지
			for(int i=0; i<n; i++) { // 출발지
				for(int j=0; j<n; j++) { // 도착지 
					if(tree[i][a] ==1 & tree[a][j] ==1) {
						tree[i][j] =1; 
					}
				}
			}	
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(tree[i][j]+" ");
			}
			System.out.println();
		}
		
		
		
	}//end of main 
	
}
