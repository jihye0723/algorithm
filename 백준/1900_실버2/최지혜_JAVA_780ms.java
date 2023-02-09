package study;

import java.io.*;
import java.util.*;

public class Solution_BaekJoon_1900_1 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //선수의 수 
		int[][] player = new int [N][4];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			player[i][0] = Integer.parseInt(st.nextToken());
			player[i][1] = Integer.parseInt(st.nextToken());	
			player[i][2] = i+1; // 선수 번호 
		}// -------------- input ----------------

		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				int power1 = player[i][0] + player[i][1] * player[j][0]; 
				int power2 = player[j][0] + player[j][1] * player[i][0]; 
				
				if(power1 > power2) player[i][3] ++;
				else player[j][3]++; 
			
			}
		}
		
		Arrays.sort(player, (p1, p2) ->{
			return p2[3] - p1[3]; 
		});
		
		for(int i=0; i<N; i++) {
			System.out.println(player[i][2]);
		}
		
	} // end of main 
	
}// end of class 
