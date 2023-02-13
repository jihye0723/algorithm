import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in); 
		int N= sc.nextInt(); // 지원이가 만들 수 있는 길이 
		
		int dp[] = new int[N+1];
		if(N<3) {
			System.out.println(N);
			return; 
		}
	
		dp[1] = 1; // N=1일때는 0: 1개
		dp[2] = 2; // N=2일때는 00, 11 : 2개 		
		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-1]+dp[i-2])%15746; 
		}
		
		
		System.out.println(dp[N]);
	}
}
