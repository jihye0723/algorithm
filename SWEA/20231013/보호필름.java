import java.io.*;
import java.util.*;

public class Solution {
	static int D, W, K;
	static int map[][]; 
	static int copy[][]; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T = Integer.parseInt(br.readLine());//테스트케이스의 갯수 
		for(int tc=1; tc<=T; tc++) {
			st= new StringTokenizer(br.readLine(), " "); 
			D= Integer.parseInt(st.nextToken()); //보호필름의 두께 (세로길이) 
			W= Integer.parseInt(st.nextToken()); //가로길이 
			K= Integer.parseInt(st.nextToken()); //k는 최대 d 
			
			map = new int[D][W];
			copy = new int[D][W]; 
			for(int i=0; i<D; i++) {
				st= new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					map[i][j] = copy[i][j]=Integer.parseInt(st.nextToken()); 
				}
			}
			min= Integer.MAX_VALUE; 
			if(isPass()) min=0; 
			else simulate(0,0); 
			
			simulate(0,0); 
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
			
					
		}//end of testcase for	
		
		System.out.println(sb.substring(0, sb.length()-1));
	}//end of main
	
	private static int min ;
	private static void simulate(int cnt, int layer) {
		//최솟값 구하는 거기 때문에 필요없음 
		if(cnt >= min) return; 
		
		if(layer == D) {
			if(isPass()) {
				min = Math.min(min, cnt);				
			}
			return; 
		}
		
		//아무것도 넣지 않음 
		simulate(cnt, layer+1); 
		
		//0주입 
		for(int c=0; c<W; c++) copy[layer][c] = 0; 
		simulate(cnt+1, layer+1); 
		
		//1주입 
		for(int c=0; c<W; c++) copy[layer][c]=1; 
		simulate(cnt+1, layer+1); 
		
		//백트래킹  
		for(int c=0; c<W; c++) copy[layer][c] = map[layer][c]; 
		

	}//end of simulate
	
	//지금 보호필름의 형태로 통과 가능한지
	private static boolean isPass() {
		for(int c=0; c<W; c++) {
			boolean flag=false; 
			int val = copy[0][c]; 
			int cnt =1; 
			
			for(int r=1; r<D; r++) {
				if(copy[r][c] == val) {
					cnt++; 
				}
				else {
					cnt=1;
					val=copy[r][c]; 
				}
			
			
				if(cnt == K) {
					flag= true; 
					break; 
				}
			}
			
			if(!flag) return false;
		}
		return true; 
	}//end of isPass
	
}//end of class d
