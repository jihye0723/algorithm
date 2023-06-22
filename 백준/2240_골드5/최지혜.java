import java.util.*;
import java.io.*;
	
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		int T= Integer.parseInt(st.nextToken()); //t초 동안 자두 떨어짐 
		int W= Integer.parseInt(st.nextToken());// 자두의 최대 움직임 횟수 
		
		//t번째 자두가 떨어질때, 자두가 w만큼 이동했고, 1/2번째 나무에 서있을 때 
		int dp[][][] = new int[T+1][W+1][3]; 

		
		for(int i=1; i<=T; i++) {
			int tree = Integer.parseInt(br.readLine());
			for(int j=0; j<=W; j++) {
				//자두가 1번 나무에서 떨어진 경우
				if(tree==1) {
					//한번도 안움직인 것 : 1번에 있어야함 (초기위치 1) 
					if(j==0) {
						dp[i][j][1]= dp[i-1][j][1]+1; 
						continue; 
					}
					//자두(사람)가 1번 나무 아래 서있는 경우 (원래 1에 있던 경우, 2에서 이동한 경우) 
					dp[i][j][1]= Math.max(dp[i-1][j][1], dp[i-1][j-1][2])+1;
					//자두(사람)가 2번 나무 아래 서있는 경우 (1에서 이동한 경우, 원래 2에 있던경우 ) = 자두 못먹음 
					dp[i][j][2]= Math.max(dp[i-1][j-1][1], dp[i-1][j][2]);
				}
				//자두가 2번 나무에서 떨어진 경우 
				else {
					//1번에 있으므로 자두를 먹을 수 없음 
					if(j==0) {
						dp[i][j][1]= dp[i-1][j][1]; 
						continue; 
					}
					dp[i][j][1]= Math.max(dp[i-1][j][1], dp[i-1][j-1][2]); 
					dp[i][j][2]= Math.max(dp[i-1][j-1][1], dp[i-1][j][2])+1; 
				}
			}
			
		}
		
		int res=0; 
		for(int i=0; i<=W; i++) {
			res= Math.max(res, Math.max(dp[T][i][1], dp[T][i][2])); 
		}
		
		System.out.println(res);
		
	}//end of main 
}//end of class 
