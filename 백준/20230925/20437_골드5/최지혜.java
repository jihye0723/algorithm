import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		int[] alpa = new int[26]; 
		int N = Integer.parseInt(br.readLine()); //테스트의 갯수 
		while(N-- >0) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			String s= br.readLine();
			char[] arr= s.toCharArray();
			for(int i=0; i<s.length(); i++) {
				alpa[arr[i]-'a'] ++; //문자열에 포함되어 있는 각 알파벳의 갯수 세기 
			}
			int k = Integer.parseInt(br.readLine()); 
			
			if(k==1) {
				sb.append("1 1").append("\n");
				continue; 
			}
			
			for(int i=0; i<s.length()-1; i++) {
				//해당 알파벳이 문자열 내에 n개보다 적으면 탐색 해볼 필요  x 
				char ch= arr[i]; 
				if(alpa[ch-'a'] >= k) {
					int cnt = 1; //부분문자열에서 문자가 나온횟수 
					for(int j=i+1; j<s.length(); j++) {
						if(arr[j] == ch) cnt ++; 
						if(cnt == k) {
							int len = j- i+1; //길이 
							min = Math.min(len, min);
							max = Math.max(len, max);
							alpa[ch-'a']--; //다음 탐색에서는 방금 탐색한 문자 포함 x 
							break; 
						}
					}
				}
			}
				
			if(min ==  Integer.MAX_VALUE) {
				sb.append("-1");
			}
			else sb.append(min+" "+max); 
			sb.append("\n");

		}
		
		System.out.println(sb.substring(0, sb.length()-1));
		
		
		
		
		
		

	}//end of main
}//end of class 
