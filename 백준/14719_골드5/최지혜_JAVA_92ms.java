package study_17;
import java.util.*;
import java.io.*;

public class Solution_BaekJoon_14719 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		int h= Integer.parseInt(st.nextToken());//세로길이 
		int w= Integer.parseInt(st.nextToken());//가로길이 
		
		int block[] = new int[w]; 
		
 		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<w; i++) {
			block[i]= Integer.parseInt(st.nextToken()); 
		}
		
		int total_result=0; 
		//첫번째랑 마지막칸에는 물 고이지 않음 
		for(int i=1; i<w-1; i++) {
			int now= block[i]; //현재 위치 블럭 
			int max_left=0; 
			int max_right=0; 
			
			//현재 블럭 기준 왼쪽 중 가장 높은 거 찾기 
			for(int left=0; left<i; left++) {
				max_left = Math.max(max_left, block[left]); 
			}
			
			//현재 블럭 기준 오른쪽 중 가장 높은 거 찾기 
			for(int right=i+1; right<w; right++) {
				max_right= Math.max(max_right, block[right]); 
			}
			
			if(max_left > now && max_right > now) {
				total_result += (Math.min(max_left, max_right) - now); 
			}
//			System.out.println(max_left+" "+max_right+" "+total_result);
		}
		System.out.println(total_result);
	}//end of main 
	
}
