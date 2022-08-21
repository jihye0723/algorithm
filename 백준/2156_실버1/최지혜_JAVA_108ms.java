import java.io.*;
import java.util.*;
/*dp 로 풀자 
 * 포도주 갯수가 1개일때는 dp[1] 이상 값이 구해지지 않는다.
 * 그래서 계속 런타임 에러 발생햇음
 *  dp[0] 은 무조거 있음 
 **/
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()) ; // 포도주 잔의 갯수 
		int[] grape= new int[n]; //포도주 양 
		int[] dp = new int[n]; //각 위치까지의 포도주 최댓값 저장 
		
		for(int i=0; i<n; i++) {
			grape[i] = Integer.parseInt(br.readLine());
		}
		// [0] [1] [2] [3] [4] [5]
		//  6   10  13  9   8   1
		
		// 두잔까지는 다 먹는게 최댓값 
		dp[0] = grape[0]; 
//		dp[1] = grape[0]+grape[1]; 
//		//세잔째에는, xoo 에서 dp[n-3] 인덱스가 음수됨 -> 따로 정의해주기
//		//               oox                oxo               xoo  
//		dp[2] = Math.max(dp[1], Math.max(dp[0]+grape[2], grape[1]+grape[2]));
//		
		for(int i=1; i<n; i++) {
			if(i==1) {
				dp[1] = grape[0]+grape[1]; 
				continue ; // 밑에 코드 안가게 
			}
			if(i==2) {
				dp[2] = Math.max(dp[1], Math.max(dp[0]+grape[2], grape[1]+grape[2]));
				continue ;
			}
			//			       oox                   oxo                    xoo 
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2]+grape[i], dp[i-3]+grape[i-1]+grape[i]));
		}
		
		System.out.println(dp[n-1]);
	
		
	}// end of main 
}// end of class 
