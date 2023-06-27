import java.util.*;
import java.io.*;
	
public class Main {
	static int N; 
	static int apple_sum[][]; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine()); //과수원의 크기 
		int[][] apple = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " "); 
			for(int j=1; j<=N; j++) {
				apple[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		//누적합 먼저 구하기 
		apple_sum = new int[N+1][N+1]; 
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				apple_sum[i][j] = apple_sum[i-1][j] + apple_sum[i][j-1] + apple[i][j]- apple_sum[i-1][j-1];  
			}
		}
		
//		for(int i=0; i<=N; i++) {
//			for(int j=0; j<=N; j++) {
//				System.out.print(apple_sum[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		int max= Integer.MIN_VALUE; 
		//시작점 (i,j)
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				int res = check(i,j); 
//				System.out.println("시작점 : ("+i+","+j+") : "+ res);
				max = Math.max(max, check(i,j)); 
			}
		}
		
		System.out.println(max);
		
	}//end of main 
	private static int check(int i, int j) {
		//시작점 i,j 에서 시작해서 정사각형 
		int sum_max=Integer.MIN_VALUE; 
		int length =0; 
		while(!isOut(i+length, j+length)) {
			
			int sum = apple_sum[i+length][j+length] - apple_sum[i-1][j+length] 
					- apple_sum[i+length][j-1] + apple_sum[i-1][j-1]; 
//			System.out.println("끝점 : ("+(i+length)+","+(j+length)+") : "+sum);
			sum_max = Math.max(sum, sum_max); 
			length ++; 
		}
		
		return sum_max;
	}
	//끝점이 범위를 벗어나는지 확인 
	private static boolean isOut(int x, int y) {
		return x>N || y> N; 
	}
}//end of class 
