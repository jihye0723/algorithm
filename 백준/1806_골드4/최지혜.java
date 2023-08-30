import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken()); 
		int S= Integer.parseInt(st.nextToken());
		
		int arr[] = new int[N+1]; 
		
		st= new StringTokenizer(br.readLine() , " ");  
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 	
		}

		int left=0; 
		int right=0;
		int sum=0;
		int min = Integer.MAX_VALUE; 
		int length =0; 
		while(right <= N) {
			if(sum >= S) {
				sum -= arr[left ++]; 
				length = right -left +1;
				if (min > length) min = length; 
			}
			else if(sum < S) {
				sum += arr[right++]; 
			}
		}
		
		System.out.println((min) == Integer.MAX_VALUE ? 0 : min);
	}//end of main
}//end of class 
