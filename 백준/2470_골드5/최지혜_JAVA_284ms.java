import java.util.*;
import java.io.*;

/*
 * 산성:양의정수 / 알칼리:음의정수
 * 산성만 주어졌을 때는 작은수 두개 더하는거 
 * 알칼리만 주어졌을 때는 큰 수 두개 더하는거 가 이득  */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		
		int answer[] = new int[2]; // 출력할 결과값 
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		Arrays.sort(arr);
		
		
		int min = Integer.MAX_VALUE; //두 용액의 합의 절댓값의 최소값 
		int left=0;
		int right=N-1;
		
		while(left<right) {
			int sum = arr[left]+arr[right]; 
			
			if(min>Math.abs(sum)) {
				answer[0]=arr[left];
				answer[1]=arr[right];
				//0이면 걍 바로 출력 
				if(sum==0) {
					break;
				}
				min = Math.abs(sum); 
			}
			
			if(sum<0) left++;
			else right--;
		 
		}
		System.out.println(answer[0]+" "+answer[1]);
		
	}//end of main 
}//end of class 
