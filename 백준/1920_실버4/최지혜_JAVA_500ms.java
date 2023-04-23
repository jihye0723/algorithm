package study;

import java.io.*;
import java.util.*;

public class BaekJoon_Solution_1920 {
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int N=Integer.parseInt(br.readLine()); 
		int[] n = new int[N];
		st=new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++)
			n[i]= Integer.parseInt(st.nextToken()); 
		
		int M= Integer.parseInt(br.readLine());
		int[] m = new int[M]; 
		st= new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++) {
			m[i]= Integer.parseInt(st.nextToken()); 
		}
		
		Arrays.sort(n);
//		Arrays.sort(m);
		
		for(int i=0; i<M; i++) {
			int find= m[i]; //n에서 찾아야 할 수 
			find(find, n); 
		}
		
		sb.deleteCharAt(sb.length()-1);

		System.out.println(sb);
		
		
	}//end of main 
	
	static StringBuilder sb = new StringBuilder(); 
	static void find(int find, int n[]) {
		int left=0;
		int right= n.length-1;  
		
		while(left <= right) {
			int mid = (left+right)/2; 
			
			if(n[mid] < find) left =mid+1;
			else if(n[mid]>find) right=mid-1;
			else {
				sb.append(1).append("\n"); 
				return ; 
			}
		}
		sb.append(0).append("\n"); 
	}
}//end of class 
