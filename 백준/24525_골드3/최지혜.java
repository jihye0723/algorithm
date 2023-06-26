import java.util.*;
import java.io.*;
	
public class Main {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		String s =br.readLine();
		//S : 2, K : -1 , 나머지 : 0 
		int[] sum = new int[s.length()+1]; 
		int[] sk_cnt = new int[s.length()+1]; //각 위치에서 s,k의 갯수 
		int[] index = new int[300001]; 
		Arrays.fill(index, -1);
		
		int cnt =0; 
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i); 
			if(c== 'S') {
				sum[i+1]= 2;
				cnt++; 
			}
			else if(c=='K') {
				sum[i+1]=-1;
				cnt++; 
			}
			else sum[i+1]= 0; 
			
			sk_cnt[i+1]= cnt;
			sum[i+1] +=sum[i];
			
			if(index[sum[i]+100000]==-1) index[sum[i]+100000] = i; 
		}

		int max_length = -1; 
		for(int i=1; i<sum.length; i++) {			
			//num의 값을 가지고 있는 가장 최소 인덱스 찾기 (같은 값이어야 0이됨 구간합이 0이됨) 
			int min_index= index[sum[i]+100000]; 
			if(min_index == -1) continue; 
			
			//중간에 s,k 가 존재하는지 확인 (하나라도 존재하면 됨) 
			if(sk_cnt[i]-sk_cnt[min_index] >0) 
				max_length = Math.max(max_length, (i-min_index)); 
		}
		
		System.out.println(max_length);		
	}//end of main 
}//end of class 
