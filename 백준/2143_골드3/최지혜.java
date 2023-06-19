import java.util.*;
import java.io.*;
	
public class Main {
	static ArrayList<Integer> a_sum = new ArrayList<>();
	static ArrayList<Integer> b_sum = new ArrayList<>(); 
	static int T; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		T= Integer.parseInt(br.readLine()); //만들어야 하는 수 
		
		int n = Integer.parseInt(br.readLine());
		int A[] = new int[n];
		StringTokenizer st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<n; i++) {
			A[i] =Integer.parseInt(st.nextToken()); 
		}
		
		int m= Integer.parseInt(br.readLine()); 
		int B[] = new int[m]; 
		st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<m; i++) {
			B[i] = Integer.parseInt(st.nextToken()); 
		}
		
		//가능한 A의 부배열 합 구하기
		int sum=0; 
		for(int i=0; i<n; i++) {
			sum=A[i]; 
			a_sum.add(sum); 
			for(int j=i+1; j<n; j++) {
				sum+=A[j];
				a_sum.add(sum); 
			}
		}
		
		//가능한 B의 부배열 합 구하기 
		for(int i=0; i<m; i++) {
			sum=B[i];
			b_sum.add(sum);
			for(int j=i+1; j<m; j++) {
				sum+=B[j];
				b_sum.add(sum); 
			}
		}
		
		Collections.sort(a_sum);
		Collections.sort(b_sum);
		
		find();
		System.out.println(cnt);
 	}//end of main 
	static long cnt=0; 
	private static void find() {
		for(int i=0; i<a_sum.size(); i++) {
			int sum = a_sum.get(i); 
			
			//b리스트에서 T-sum 인 수의 갯수를 찾아야함 
			int find_num = T-sum;
			cnt += findB(find_num); 
		}
	}
	
	//b리스트에서 find_num 의 갯수 구하기
	private static int findB(int find_num) {
		//find_num이 가장 첫번째로 나오는 인덱스 
		int first=lowerBound(find_num);
		//찾는 수가 b리스트에 없음 
		if(first==-1) return 0; 
		//find_num보다 큰 수가 나오는 인덱스 
		int end=upperBound(find_num); 
		
		return end-first; 
	}
	//b리스트에서 찾고자 하는 수가 가장 처음 나오는 인덱스 반환 (없을 경우 -1) 
	private static int lowerBound(int find_num) {
		int left=0; 
		int right = b_sum.size();
		
		while(left<right) {
			int mid = (left+right)/2;
			
			//가장 앞의 인덱스를 찾아야하니까 같을 경우에도 right 를 mid로 
			if(b_sum.get(mid) >= find_num) right=mid;
			else left= mid+1; 
			
		}
		if(left >= b_sum.size() || b_sum.get(left) != find_num) return -1;
		else return left;
 	}
	//b리스트에서 찾고자 하는 수보다 큰 수가 처음 나오는 수 반환 
	private static int upperBound(int find_num) {
		int left=0;
		int right=b_sum.size();
		
		while(left<right) {
			int mid =(left+right)/2;
			
			if(b_sum.get(mid) > find_num) right=mid; 
			else left=mid+1; //찾고자 하는 수가 같으면 더 큰 수 찾아야 하므로 left 변경 
		}
		
		return left; 
	}
}//end of class 
