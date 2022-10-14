import java.util.*;
import java.io.*;

/*
 *앞에 있는 것의 2번과 , 다음것의 6번이 맞닿는다. 
  01234567
  10101111
  01111101
  11001110 
  00000010
  */
public class Main {
	public static void main(String args[]) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map = new int[4][8];
		
		for(int i=0; i<4; i++) {
			String s= br.readLine(); 
			for(int j=0; j<8; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		
		//map[0] : 1번 톱니바퀴
		//map[1] : 2번
		//map[2] : 3번
		//map[3] : 4번

		int count= Integer.parseInt(br.readLine()); // 회전 횟수 
		while(count-->0) {
			int[] dir = new int[4]; 
			int[][] meet= new int[4][2]; 
			for(int i=0; i<4; i++) {
				meet[i][0] = map[i][2];
				meet[i][1] = map[i][6]; 
			}
			StringTokenizer st= new StringTokenizer(br.readLine(), " ");
			int num= Integer.parseInt(st.nextToken())-1; // 회전시킨 톱니바퀴 배열 번호 
			dir[num]= Integer.parseInt(st.nextToken()); // 회전 방향 ( 1 : 시계 / -1 : 반시계 ) 
			//해당 톱니바퀴 기준 왼쪽 
			int nnum=num;
			while(nnum>0) {
				if(meet[nnum][1] == meet[nnum-1][0]) break; 
				dir[nnum-1] = dir[nnum]*-1; 
				nnum--; 
			}
			while(num<3) {
				if(meet[num][0] == meet[num+1][1]) break; 
				dir[num+1] = dir[num] * -1; 
				num++;
			}
			for(int i=0; i<4; i++) {
				if(dir[i]==-1) goLeft(map[i]);
				if(dir[i]==1) goRight(map[i]);
			}
			
//			for(int i=0; i<4; i++) {
//				for(int j=0; j<8; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.print(" "+dir[i]);
//				System.out.println();
//			}
//			System.out.println();
		}
		
		int res=0;
		for(int i=0; i<4; i++) {
			//12시가 S극인 상황 
			if(map[i][0]==1) res+=(Math.pow(2, i)); 
		}
		System.out.println(res);
		
		
		
		
	}// end of main 
	
	// 시계방향 회전 
	private static void goRight(int[] array) {
		int a= array[7]; 
		 for(int i=7; i>0; i-- ) {
			 array[i]= array[i-1];
		 }
		 array[0] = a; 
	}
	
	// 반시계방향 회전 
	private static void goLeft(int[] array) {
		int a= array[0]; 
		 for(int i=0; i<7; i++ ) {
			 array[i]= array[i+1];
		 }
		 array[7] = a; 
	}
}
