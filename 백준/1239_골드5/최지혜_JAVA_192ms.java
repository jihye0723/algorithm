import java.util.*;
import java.io.*;
	
public class Main {	
	static int N; 
	static boolean[] picked; 
	static int[] num; 
	static int[] permute_num; 
	static int max= Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()) ; 
		picked = new boolean[N]; 
		num = new int[N]; 
		permute_num = new int[N]; 
		StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
		boolean isend= false;
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken()); 
			//하나라도 50이면 무조건 1 
			if(num[i]==50) {
				System.out.println(1);
				isend= true; 
				break; 
			}
			//하나라도 50넘으면 선 그을 수 없음 
			if(num[i] > 50) {
				System.out.println(0);
				isend= true; 
				break; 
			}
		}
		
		if(!isend) {
			permute(0);  
			System.out.println(max);
		}
	
	}//end of main 
	
	//주어진 것들로 순열 찾기 
	private static void permute(int index) {
		if(index==N) {
			max = Math.max(max, find()); 
			return; 
		}
		
		for(int i=0; i<N; i++) {
			if(!picked[i]) {
				picked[i] = true; 
				permute_num[index] = num[i]; 
				permute(index+1);
				picked[i] = false; 
			}
		}
	}
	
	
	private static int find() {
		List<Integer> list= new ArrayList<>(); 
		int cnt=0; 
		list.add(0); 
		int next; 
		for(int i=1; i<N; i++) {
			next= list.get(i-1)+permute_num[i-1]; 
			list.add(next); 
		}
		
		for(int i=0; i<N; i++) {
			int now = list.get(i); 
			if (now>=50) break; 

			if(list.contains(now+50)) cnt++; 
			
		}
		return cnt; 
	}
}//end of class 
