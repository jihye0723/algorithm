import java.util.*;
import java.io.*;
	
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		
		int M= Integer.parseInt(st.nextToken());
		int N= Integer.parseInt(st.nextToken());
		
		char map[][] = new char[M+1][N+1]; 
		
		int K= Integer.parseInt(br.readLine()); 
		
		for(int i=1; i<=M; i++) {
			String s = br.readLine(); 
			for(int j=1; j<=N; j++) {
				map[i][j]= s.charAt(j-1); 
			}
		}
		
		int[][] jungle = new int[M+1][N+1]; 
		int[][] ocean = new int[M+1][N+1];
		int[][] ice = new int[M+1][N+1]; 
		
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=N; j++) {
				switch(map[i][j]) {
				case 'J':
					jungle[i][j]= jungle[i-1][j] + jungle[i][j-1] + 1 - jungle[i-1][j-1]; 
					ocean[i][j] = ocean[i-1][j] + ocean[i][j-1] - ocean[i-1][j-1]; 
					ice[i][j] = ice[i-1][j] + ice[i][j-1] - ice[i-1][j-1]; 
					break;
					
				case 'O':
					ocean[i][j] = ocean[i-1][j] + ocean[i][j-1] +1 - ocean[i-1][j-1]; 
					jungle[i][j]= jungle[i-1][j] + jungle[i][j-1] - jungle[i-1][j-1]; 
					ice[i][j] = ice[i-1][j] + ice[i][j-1] - ice[i-1][j-1]; 
					break;
					
				case 'I':
					ice[i][j] = ice[i-1][j] + ice[i][j-1] +1 - ice[i-1][j-1]; 
					ocean[i][j] = ocean[i-1][j] + ocean[i][j-1] - ocean[i-1][j-1]; 
					jungle[i][j]= jungle[i-1][j] + jungle[i][j-1] - jungle[i-1][j-1]; 
					break;
					
				}
			}
		}
		
		StringBuilder sb= new StringBuilder(); 
		int jungle_cnt=0;
		int ocean_cnt=0;
		int ice_cnt=0; 
		while(K-- > 0) {
			st =new StringTokenizer(br.readLine(), " ");
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c= Integer.parseInt(st.nextToken());
			int d= Integer.parseInt(st.nextToken());
			
			jungle_cnt = jungle[c][d] - jungle[c][b-1] - jungle[a-1][d] + jungle[a-1][b-1]; 
			ocean_cnt = ocean[c][d] - ocean[c][b-1] - ocean[a-1][d] + ocean[a-1][b-1]; 
			ice_cnt = ice[c][d] - ice[c][b-1] - ice[a-1][d] + ice[a-1][b-1]; 
			
			sb.append(jungle_cnt).append(" ").append(ocean_cnt).append(" ").append(ice_cnt).append("\n"); 
		}
		
		System.out.println(sb.substring(0, sb.length()-1));
		
		
//		for(int i=0; i<=M; i++) {
//			for(int j=0; j<=N; j++) {
//				System.out.print(jungle[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("--------------");
//		for(int i=0; i<=M; i++) {
//			for(int j=0; j<=N; j++) {
//				System.out.print(ocean[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("--------------");
//		for(int i=0; i<=M; i++) {
//			for(int j=0; j<=N; j++) {
//				System.out.print(ice[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("--------------");
		
		
	}//end of main 
}//end of class 
