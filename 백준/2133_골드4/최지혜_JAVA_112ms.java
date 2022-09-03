import java.util.Scanner;

public class Solution_BaekJoon_2133_타일채우기_골드4 {
	public static void main(String[] args) {
		int[] dp = new int[31]; // 30까지의 dp 값 저장 
		Scanner sc = new Scanner(System.in);
		
		int N= sc.nextInt();
		
		dp[2]= 3; 
		dp[4] = dp[2] *3+ 2;
		
		for(int i=4; i<=30; i++) {
			if(i%2==1) {
				dp[i]=0; 
				continue;
			}
			dp[i] = dp[i-2] * 3 + 2; 
			for(int j=i-4; j>0; j-=2) {
				dp[i]+=dp[j] * 2;				
			}
 		}
		
		System.out.println(dp[N]);

	}
}
