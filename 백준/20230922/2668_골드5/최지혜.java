import java.util.*;
import java.io.*;

public class Main {
	static List<Integer> list = new ArrayList<>(); 
	static boolean[] visited; 
	static boolean[] isSelect; //집합에 포함된 숫자  
	static int cnt = 0; // 현재 집합에 포함된 숫자 
	static int[] arr; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		StringTokenizer st; 
		
		int N= Integer.parseInt(br.readLine());
		
		arr = new int[N]; 
		visited = new boolean[N]; 
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine())-1;
		
		for(int i=0; i<N; i++) {
			find(i,arr[i]);
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for(int i : list) System.out.println(i+1);
	}
	private static void find(int start, int num) {
		if(start == num) {
			list.add(num); 
			return; 
		}
		
		int next= arr[num]; 
		if(!visited[next]) {
			visited[next]= true;
			find(start, next); 
			visited[next]=false; 
		}
	}
	
}//end of class 
