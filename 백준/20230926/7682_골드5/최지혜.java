import java.util.*;
import java.io.*;

public class Main {
	static char[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		StringTokenizer st; 
		
		StringBuilder sb= new StringBuilder();
		String s;
		int isempty ; //빈칸의 갯수 
		int x_cnt;
		int o_cnt; 
		map = new char[3][3]; 
		while(true) {
			isempty =0; x_cnt=0; o_cnt=0;
			s=br.readLine(); 
			if(s.equals("end")) {
				break; 
			}
			int index=0; 
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					map[i][j] = s.charAt(index++); 
					if(map[i][j] =='.') isempty++; 
					else if(map[i][j] == 'X') x_cnt ++;
					else o_cnt ++; 
				}
			}
			
			//빈칸이 하나도 없을 경우 x갯수 == o갯수+1
			//1. 아무도 빙고 못채우고 끝남 
			//2. 마지막에 x가 빙고만듦 
			if(isempty ==0) {
				    //x갯수 == o갯수+1                둘다 빙고 못만들고 끝남                                        x가 마지막에 빙고 만듬                                   
				if((x_cnt == o_cnt+1) && ((!isBingo('X') & !isBingo('O')) || (isBingo('X') & !isBingo('O')))) sb.append("valid"); 
				else sb.append("invalid"); 
			}
			
			//빈칸이 있을 경우 
			else {
				//1. x갯수가 o보다 하나 더 많으면 : x가 빙고 , o는 빙고 x 
				if((x_cnt == o_cnt+1) && (isBingo('X') & !isBingo('O'))) sb.append("valid"); 
				//2. x갯수와 o갯수가 같으면 : o가 빙고 , x는 빙고 아님 
				else if((x_cnt == o_cnt) && (isBingo('O') & !isBingo('X'))) sb.append("valid"); 
				else sb.append("invalid");
			}
			
			sb.append("\n"); 
		}
		
		System.out.println(sb.substring(0, sb.length()-1));
		

	}//end of main

	//c문자로 빙고가 만들어지는 여부 반환 
	private static boolean isBingo(char c) {
		//세로 빙고 먼저 확인 
		for(int j=0; j<3; j++) {
			if(map[0][j] == c) {
				int i; 
				for(i=1; i<3; i++) {
					//빙고 x 
					if(map[i][j] != c) break; 

				}
				if(i==3) return true; 
			}
		}
		
		//가로 빙고 먼저 확인 
		for(int i=0; i<3; i++) {
			if(map[i][0] == c) {
				int j; 
				for(j=1; j<3; j++) {
					//빙고 x 
					if(map[i][j] != c) break; 

				}
				if(j==3) return true; 
			}
		}
		
		//대각선 빙고 확인 
		if(map[1][1] == c) {
			if((map[0][0]==c) && (map[2][2]==c)) return true; 
			else if ((map[2][0] == c) && (map[0][2] ==c)) return true; 
		}
		
		return false; 
	}
}//end of class 
