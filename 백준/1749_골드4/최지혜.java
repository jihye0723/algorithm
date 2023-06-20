import java.util.*;
import java.io.*;
	
public class Main {
	static int max=Integer.MIN_VALUE;
	static int[][] sum; 
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken()); 
		int map[][] = new int[N+1][M+1];
		sum = new int[N+1][M+1]; //합 더하기 
		
		for(int i=1; i<=N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				max= Math.max(max, map[i][j]); 
			}
		}
		
		//누적합 구하기 		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				sum[i][j] = sum[i-1][j] + sum[i][j-1] + map[i][j] - sum[i-1][j-1]; 
			}
		}
		
		
		//시작점, 끝점을 모든 경우의 수로 하여 탐색 
		for(int sx= 1; sx<=N; sx++) {
			for (int sy=1; sy<=M; sy++) {
				for(int ex= sx; ex<=N; ex++) {
					for(int ey=sy; ey<=M; ey++) {
						int result= findSum(sx, sy, ex, ey);
						max= Math.max(max, result); 
					}
				}
			}
		}
		
		System.out.println(max);
		
	}//end of main 
	
	//(sx, sy) ~ (ex, ey) 구간합 구하기 
	private static int findSum(int sx, int sy, int ex, int ey) {
		return sum[ex][ey] - sum[sx-1][ey]- sum[ex][sy-1]+ sum[sx-1][sy-1]; 
	}
}//end of class 
