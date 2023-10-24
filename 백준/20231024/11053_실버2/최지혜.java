import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n= Integer.parseInt(br.readLine());
		int[] arr= new int[n]; 
		int[] dp = new int[n]; 
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) arr[i]= Integer.parseInt(st.nextToken()); 
		
		dp[0]=1;
		
		int max; 
		for(int i=1; i<n; i++) {
			max=0; 
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) max=Math.max(dp[j], max); 
			}
			dp[i]= max+1; 
		}
		max= 0; 
		for(int i=0; i<n; i++) {
			max =Math.max(max, dp[i]); 
		}
		System.out.println(max);
	}//end of main
}//end of class 
