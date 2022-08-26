import java.util.*;
import java.io.*;

public class Solution_BaekJoon_14888_연산자끼워넣기_실버1 {
	private static int[] num;
	private static int[] cal;
	private static int min = Integer.MAX_VALUE;
	private static int max = Integer.MIN_VALUE;
	private static int n;

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine()); // 수의 갯수 
		num = new int[n]; 
		String s=br.readLine();
		st= new StringTokenizer(s, " ");
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken()); 
		}
		
		//0 : 덧셈, 1 : 뺄셈, 2 : 곱셈, 3: 나눗셈
		cal = new int[4]; 
		
		String sa= br.readLine(); 
		st= new StringTokenizer(sa, " ");
		for(int i=0; i<4; i++) {
			cal[i] = Integer.parseInt(st.nextToken()); 
		}
		
		dfs(num[0], 1); 
		
		
		System.out.println(max);
		System.out.println(min);
		
	}// main

	private static void dfs(int value, int index) { // value : 현재까지의 계산 값 , index :현재 연산할 수
		if(index==n) {
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}
		
		for(int i=0; i<4; i++) {
			// 연산자 남아있으면, 
			if(cal[i]>0) {
				cal[i]--; 
				switch(i) {
				case 0: dfs(value+num[index], index+1); break;
				case 1: dfs(value-num[index], index+1); break;
				case 2: dfs(value*num[index], index+1); break;
				case 3: dfs(value/num[index], index+1); break;
				}
				
				//재귀호출 다음엔, 다음 탐색을 위해 
				cal[i]++; 
						
			}
		}
		
	}
}
