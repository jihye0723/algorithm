package study_17;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_2473 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); 
		long arr[] = new long[N];
		long ans[] = new long[3]; 
		
		StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken()); 
		}
		
		Arrays.sort(arr);
		
		long min = 3000000000L; 
		boolean isEnd = false; 
		//a : 하나 선택 
		for(int a=0; a<N-2; a++) {
			
			int left = a+1; 
			int right = N-1;
			while(left < right) {
				long sum = arr[a]+arr[left]+arr[right];
				long absSum = Math.abs(sum); 
				if(min > absSum) {
					min=absSum; 
					ans[0]=arr[a];
					ans[1]=arr[left];
					ans[2]=arr[right];
					
					if(sum == 0) {
						isEnd =true;
						break; 
					}
				}
				if(sum<0) left++;
				else right--;
			}//end of while
			
			if(isEnd) break; 
		}//end of for
		System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
	}//end of main 
}
