import java.util.*;
import java.io.*;

public class Solution_SWEA_1859_D2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스
		for(int tc=1; tc<=T; tc++) {
			int N= Integer.parseInt(br.readLine()); 
			int[] pay = new int[N];
			StringTokenizer st= new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				pay[i] = Integer.parseInt(st.nextToken());
	
			}
			// 1 1 3 1 3 

			long sum=0; // 전체 이익 
			int start_index=0;
			int max_index=0;
			
			while(start_index < N) {
				int max= Integer.MIN_VALUE; 
				// 현재 값들 중 최댓값 찾기 
				for(int i=start_index; i<N; i++) {
					if(max < pay[i]) {
						max = pay[i]; 
						max_index=i;
					}
				}
				
				for(int i=start_index; i<max_index; i++) {
					sum += (max-pay[i]); 
				}
				start_index = max_index+1;
				
			} // end of while 

			
			System.out.println("#"+tc+" "+sum);
		}// end of testcase for 
	}
}
