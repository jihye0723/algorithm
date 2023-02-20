package study;

import java.util.*;
import java.io.*;

// 알파벳을 암호로 하면 1~26까지 나올 수 있음 
/*
 * DP 사용 !! 
 * 앞에서부터 시작하면서 길이가 1일떄는 1 
 * 길이 2인거 까지도 26 이하면, 2개 / 26보다 크면 1개 
 * 길이 3인거 부터는, 마지막 두글자가 26 이하면 DP[i-2] + DP[i-1] 
 * 그렇지 않으면 , DP[i-1] */
public class Solution_BaekJoon_2011 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input= sc.next();
		// 가장 첫번째 문자가 0이면 안됨 
		if(input.charAt(0) =='0') {
			System.out.println(0);
			return; 
		}
		int len = input.length();
		int[] dp = new int[len+1]; 
		dp[0]=dp[1]=1;
			
		boolean isWrong = false; 
		for(int i=2; i< dp.length; i++) {
			int now= input.charAt(i-1)-'0'; // 현재 숫자 
			int pre= input.charAt(i-2)-'0'; // 앞의 숫자
			// 현재 숫자가 0일 때
			if(now == 0) {
				//10 또는 20 일 때 
				if(pre == 1 || pre==2) {
					dp[i] = dp[i-2]%1000000 ;
				}
				// 암호 불가능 
				else break; 
			}
					
			else {
				// 앞의 숫자가 0일때, 
				if(pre == 0) {
					dp[i] = dp[i-1]%1000000; 
				}
				else {
					int num = pre*10 + now; // 현재 두자리수 
					if(num <= 26) {
						dp[i] = dp[i-2]%1000000 + dp[i-1]%1000000;
					}
					else dp[i] = dp[i-1]%1000000; 
				}
			}
				
		}
			
		if(!isWrong) System.out.println(dp[len]%1000000);	
	
	}
		
}
