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
		
 		
 		dp[0] =1;
 		
 		for(int i=0; i<n; i++) {
 			int num = coin[i]; //(1원만 썼을 때), (1,2)원일떄, (1,2,5)원일때 
 			for(int j=1; j<=k; j++) {
 				if(j>=num) dp[j] += dp[j-num]; 
 			}
 		}
 		
 		System.out.println(dp[k]);
	}//end of main
}//end of class 
