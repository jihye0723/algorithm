import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		int n= Integer.parseInt(st.nextToken());
		int k= Integer.parseInt(st.nextToken()); 
		
		int coin[] = new int[n]; 
 		int dp[] = new int[k+1]; 
 		
 		for(int i=0; i<n; i++) {
 			coin[i] = Integer.parseInt(br.readLine()); 
 		}
		
 		Arrays.fill(dp,10001);
 		dp[0] =0; 
 		
 		for(int i=0; i<=k; i++) {
 			for(int j=0; j<n; j++) {
 				if(coin[j] <= i) {
 					dp[i] = Math.min(dp[i-coin[j]] +1, dp[i]); 
 				}
 			}
 		}
 		
// 		for(int i=0; i<=k; i++) System.out.println(dp[i]);
 		System.out.println((dp[k] > 10001)? -1 : dp[k]);
	}//end of main
}//end of class 
