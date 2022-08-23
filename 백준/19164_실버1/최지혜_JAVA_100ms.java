import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n= Integer.parseInt(br.readLine()); // 카드의 갯수 
		int[] p = new int[n+1];
		int[] dp = new int[n+1]; // 각 카드 갯수를 구매할 때의 최솟값 저장 
		
		String s= br.readLine(); 
		StringTokenizer st= new StringTokenizer(s, " ");
		// 각 카드팩의 갯수 
		for(int i=1; i<=n; i++) {
			p[i]= Integer.parseInt(st.nextToken());
		}
		
		dp[1] = p[1] ; 

		for(int i=2; i<=n; i++) {
			dp[i] =  p[i];
			for(int j=1; j<i; j++) {
				int min = dp[i-j]+p[j];
				if(dp[i] > min) dp[i] = min;
				}
			}
		
		System.out.println(dp[n]);
	
		
	}
	
	
}
