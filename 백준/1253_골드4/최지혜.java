import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int N= Integer.parseInt(br.readLine());
		int[] arr= new int[N];
		
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken()); 
		Arrays.sort(arr);
		int goodNum =0; 
		
		for(int i=0; i<N; i++) {
			int num = arr[i]; 
//			System.out.println(num);
			int left= 0; 
			int right= N-1;
			
			while(left<right) {
				//서로 다른 수의 합이니까 같은 수면 이동 
				if(i==left) left++;
				else if (i==right) right--;
				
				if(left==right) break; 
				
				int sum = arr[left] + arr[right]; 
				
				if(sum<num) left++;
				else if(sum>num) right--;
				else {
//					System.out.println(num+" "+arr[left]+" "+arr[right]);
					goodNum++;
					break; 
				}
			}
		}
		
		System.out.println(goodNum);
		

	}//end of main
}//end of class 
