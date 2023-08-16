import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][3];
		int[] dp_max = new int[3];
		int[] dp_min = new int[3];
	
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
	
		for(int i=0; i<3; i++) {
			dp_max[i]= dp_min[i] = map[0][i]; 
		}
		
		for(int i=1; i<N; i++) {
			int before_0 = dp_max[0] , before_2 = dp_max[2]; 
			dp_max[0] = Math.max(dp_max[0], dp_max[1]) +map[i][0]; 
			dp_max[2] = Math.max(dp_max[1], dp_max[2]) +map[i][2]; 
			dp_max[1] = Math.max(before_0, Math.max(dp_max[1], before_2)) +map[i][1]; 
			
			before_0 = dp_min[0] ; before_2 = dp_min[2]; 
			dp_min[0] = Math.min(dp_min[0], dp_min[1]) +map[i][0]; 
			dp_min[2] = Math.min(dp_min[1], dp_min[2]) +map[i][2]; 
			dp_min[1] = Math.min(before_0, Math.min(dp_min[1], before_2)) +map[i][1]; 
		}
		
		int max= Math.max(dp_max[0], Math.max(dp_max[1], dp_max[2]));
		int min= Math.min(dp_min[0], Math.min(dp_min[1], dp_min[2]));
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
