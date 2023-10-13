import java.io.*;
import java.util.*;

public class Solution {
	static int N,M; 
	static int[][] map; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T = Integer.parseInt(br.readLine());//테스트케이스의 갯수 
		for(int tc=1; tc<=T; tc++) {
			int max=0; 
			st= new StringTokenizer(br.readLine(), " "); 
			N= Integer.parseInt(st.nextToken()) ;//도시의 크기 
			M= Integer.parseInt(st.nextToken()) ;//집이 지불하는 비용 
			
			map= new int[N][N]; 
			for(int i=0; i<N; i++) {
				st= new StringTokenizer(br.readLine(), " "); 
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			int cost; 
			for(int k=1; k<=N*2; k++) {
				cost = getCost(k); //k로 하는데 운영비용 
				for(int a=0; a<N; a++) {
					for(int b=0; b<N; b++) {
						int house = getArea(a,b,k); //서비스 받는 집의 갯수 
						//손해를 보지 않는 경우 
						if((house * M - cost) >=0) {
							max= Math.max(max, house); 
						}
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(max).append("\n"); 
		}//end of testcase for
		
		
		System.out.println(sb.substring(0,sb.length()-1));
	}//end of main
	
	//가장 큰 이익을 보는거 찾는게 아니라 
	//손해만 안보면 됨, 손해 안보면서 가장 많은 집에 서비스를 제공할 때의 서비스 받는 집의 갯수 출력 
	
	
	//map에서 k만큼 주어진 만큼 영역 확인하기 
	//x,y : 영역의 가장 중앙 
	private static int getArea(int x, int y, int k) {
		int house=0; //영역안에 있는 집의 갯수 
		
		//가운데줄 확인 
		house+=checkOneLine(x,y,--k); 
		
		//가운데줄을 기준으로 왼쪽 영역 
		for(int ny = 1, k_cnt = k-1; ny <= k; ny++, k_cnt--) {
			if(isOut(x,y-ny)) break; 
			
			house+=checkOneLine(x, y-ny, k_cnt); 
		}
		
		//가운데줄을 기준으로 오른쪽 영역
		for(int ny=1, k_cnt =k-1; ny<=k; ny++, k_cnt--) {
			if(isOut(x, y+ny)) break;
			
			house+=checkOneLine(x, y+ny, k_cnt); 
		}
		
		
		return house; 
		
	}//end of getArea
	
	//(x,y) 기준으로 위아래로 cnt개씩 체크하기 
	private static int checkOneLine(int x, int y, int cnt) {
		int oneLine=0; 
		if(map[x][y]==1) oneLine++; 
		//윗줄 먼저 
		for(int i=1; i<=cnt; i++) {
			if(isOut(x-i, y)) break; //한번 영역 밖으로 나가면 다음거 다 나감 
			if(map[x-i][y]==1) oneLine++; 
		}
		//아랫줄 
		for(int i=1; i<=cnt; i++) {
			if(isOut(x+i,y)) break;
			if( map[x+i][y]==1) oneLine++; 
		}

		return oneLine; 
	}//end of checkOneLine
	
	//k일때의 운영비용 
	private static int getCost(int k) {
		return k*k + (k-1)*(k-1); 
	}
	private static boolean isOut(int x, int y) {
		return x<0 || x>=N || y<0 || y>= N; 
	}
}//end of class 
