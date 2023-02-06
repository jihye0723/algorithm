import java.io.*;

public class Solution_BaekJoon_9251 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		String s1= br.readLine();
		String s2= br.readLine();
		
		int n1 = s1.length(); // s1의 길이 
		int n2 = s2.length(); // s2의 길이 
		
		int dp[][] = new int[n1+1][n2+1];
		
		
		for(int i=1; i<=n1; i++) {
			for(int j=1; j<=n2; j++) {
				if(s1.charAt(i-1) != s2.charAt(j-1)) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				else {
					dp[i][j] = dp[i-1][j-1]+1;
				}
			}
		}
		
		System.out.println(dp[n1][n2]);
		
	}// end of main 
}// end of class 
