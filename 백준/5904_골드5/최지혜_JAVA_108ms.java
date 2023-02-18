import java.util.*;
import java.io.*;

// 수열  S(K)= S(k-1) + [m+o(k+2개)] + S(k-1) 
// 길이  L(K)= 2*(L(k-1)) + k+3

public class Main {
	private static char ans; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		int N= sc.nextInt(); 
		solve(N, 0, 0); 
		System.out.println(ans);
		
	}//end of main
	private static void solve(int N, int k, int pre_len) {
		int now_len = 2 * pre_len + k + 3; 
		
		// 현재 수열에서 찾을 수 있다면, 
		if(N <= now_len) {
			if(N == pre_len +1) {
				ans ='m'; 
				return;
			}
			else if ((N > pre_len +1) && (N <=pre_len + k+3)) {
				ans='o'; 
				return; 
			}
			// S(k-1) 부분에서 찾으면 됨 
			// S(k-1) + mo... + S(k-1) 앞뒤 똑같으므로, 앞부분에서 탐색 
			else {
				solve(N-(k+3)-pre_len, 0,0);
			}
		}
		// 다음 수열에서 탐색 해야 함 
		else {
			solve(N, k+1, now_len); 
		}
	}
}// end of class 
