package study_17;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_15658 {
	static int num[]; 
	static int math_cnt[] = new int[4]; // +,-,*,%
	static int max=Integer.MIN_VALUE;
	static int min=Integer.MAX_VALUE; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		int N= Integer.parseInt(br.readLine()); //수의 갯수 
		num= new int[N]; 
	
		st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) {
			num[i]= Integer.parseInt(st.nextToken()); 
		}
		
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<4; i++) {
			math_cnt[i]=Integer.parseInt(st.nextToken()); 
		}
		
		search(num[0], 1);
		System.out.println(max+"\n"+min);

	}//end of main
	
	/*모든 경우의 수 다 해보기*/
	static void search(int res, int index) {
		if(index==num.length) {
//			System.out.println(res);
			if(max<res) max= res;
			if(min>res) min=res;
			return; 
		}
		
		for(int i=0; i<4; i++) {
			if(math_cnt[i] > 0) {
				math_cnt[i]--;
				switch(i) {
				case 0:
					search(res+num[index], index+1);
					break;
				case 1:
					search(res-num[index], index+1);
					break;
				case 2:
					search(res*num[index], index+1);
					break; 
				case 3:
					search(res/num[index], index+1); 
					break;
				}
				math_cnt[i]++;
			}
		}
		
	}
	
}//end of class 
