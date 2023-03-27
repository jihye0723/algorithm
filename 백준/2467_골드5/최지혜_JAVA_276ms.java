package study_17;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_2467 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		int N= Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) {
			arr[i] =Integer.parseInt(st.nextToken()); 
		}
		
		int answer[] = new int [2];
		
		int min = Integer.MAX_VALUE; 
		int left=0; 
		int right=N-1;
		
		while(left<right) {
			int sum = arr[left] + arr[right];
			
			if(min> Math.abs(sum)) {
				min = Math.abs(sum);
				answer[0] = arr[left];
				answer[1] = arr[right];
				
				if(sum==0) break; 
			}
			
			if(sum < 0) left++;
			else right--; 
		}
		System.out.println(answer[0] + " "+answer[1]);
		
		
	}//end of main 
}//end of class 
