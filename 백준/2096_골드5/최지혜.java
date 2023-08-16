import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] map; 
	static int[][] dp_max; 
	static int[][] dp_min; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][3];
		dp_max = new int[N+1][3];
		
		dp_min = new int[N+1][3];
	
		for(int i=1; i<=N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
//		for(int i=0; i<map.length; i++) {
//			for(int j=0; j<map[i].length; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<3; j++) {
				if(j==0) dp_max[i][j]= Math.max(dp_max[i-1][j], dp_max[i-1][j+1])+map[i][j]; 	
				
				else if(j==1) {
					dp_max[i][j]= Math.max(dp_max[i-1][j-1], Math.max(dp_max[i-1][j], dp_max[i-1][j+1]))+map[i][j];
				}
				
				else dp_max[i][j]=Math.max(dp_max[i-1][j-1], dp_max[i-1][j])+map[i][j]; 
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<3; j++) {
				if(j==0) dp_min[i][j]= Math.min(dp_min[i-1][j], dp_min[i-1][j+1])+map[i][j]; 	
				
				else if(j==1) {
					dp_min[i][j]= Math.min(dp_min[i-1][j-1], Math.min(dp_min[i-1][j], dp_min[i-1][j+1]))+map[i][j];
				}
				
				else dp_min[i][j]=Math.min(dp_min[i-1][j-1], dp_min[i-1][j])+map[i][j]; 
			}
		}
		
		int max= Math.max(dp_max[N][0], Math.max(dp_max[N][1], dp_max[N][2]));
		int min= Math.min(dp_min[N][0], Math.min(dp_min[N][1], dp_min[N][2]));
		System.out.println(max+" "+min);
//		for(int i=0; i<dp_max.length; i++) {
//			for(int j=0; j<dp_max[i].length; j++) {
//				System.out.print(dp_max[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//
//		
//		for(int i=0; i<dp_max.length; i++) {
//			for(int j=0; j<dp_max[i].length; j++) {
//				System.out.print(dp_min[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
	}//end of main 
}//end of class 
