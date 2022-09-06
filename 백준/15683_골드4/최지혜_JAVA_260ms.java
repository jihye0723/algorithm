import java.io.*;
import java.util.*;

public class Main {
	static int min = Integer.MAX_VALUE;
	static List<camera> list = new LinkedList<>();
	private static int[][] map;
	private static int r;
	private static int c; 
	static class camera{
		int type; //1~5 
		int x; int y;


		public camera(int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		} 
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		String s= br.readLine();
		st= new StringTokenizer(s, " ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		for(int i=0; i<r; i++) {
			s= br.readLine();
			st= new StringTokenizer(s, " ");
			for(int j=0; j<c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// cctv 인 부분은, 어떤 카메라인지와 좌표 저장 
				if(map[i][j]>=1 && map[i][j]<=5) {
					list.add(new camera(map[i][j], i, j)); 
				}
			}
		}
		/*-----------------end of input---------------------*/

		dfs(0, map);
		
		System.out.println(min);
		
		
	}// end of main 
	private static void dfs(int index, int[][] map) {
		if(index == list.size()) {
			int count = 0; 
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					if(map[i][j] ==0) count++;  
				}
			}
			min= Math.min(count, min);
			return ; 
		}
		
		camera ca= list.get(index);
		int x= ca.x;
		int y= ca.y;
		int type= ca.type;
		int[][] maps;
		switch(type) {
		// 한방향으로 가기 
		case 1:
			maps= copyMap(map);
			goLeft(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goRight(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goUp(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goDown(maps,x,y);
			dfs(index+1,maps);
			
			break;
			
		// 상 하 또는 좌 우 
		case 2:
			maps= copyMap(map);
			goLeft(maps,x,y);
			goRight(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goUp(maps,x,y);
			goDown(maps,x,y);
			dfs(index+1,maps);
			
			break;
		// (상,우) (우,하) (하,좌), (좌,상)	
		case 3:
			maps= copyMap(map);
			goUp(maps,x,y);
			goRight(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goRight(maps,x,y);
			goDown(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goDown(maps,x,y);
			goLeft(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goLeft(maps,x,y);
			goUp(maps,x,y);
			dfs(index+1,maps);
			
			break;
			
		//(좌,상,우) (상,우,하), (우,하,좌), (하,좌,상)	
		case 4:
			maps= copyMap(map);
			goLeft(maps,x,y);
			goUp(maps,x,y);
			goRight(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goUp(maps,x,y);
			goRight(maps,x,y);
			goDown(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goRight(maps,x,y);
			goDown(maps,x,y);
			goLeft(maps,x,y);
			dfs(index+1,maps);
			
			maps= copyMap(map);
			goDown(maps,x,y);
			goLeft(maps,x,y);
			goUp(maps,x,y);
			dfs(index+1,maps);
			
			break;
		
		//상 하 좌 우 
		case 5:
			maps= copyMap(map);
			goDown(maps,x,y);
			goLeft(maps,x,y);
			goUp(maps,x,y);
			goRight(maps,x,y);
			dfs(index+1,maps);
			
			break;
			
			
		}
		
	}
	private static void goLeft(int[][]maps, int x, int y) {
		for(int i=y-1; i>=0; i--) {
			if(maps[x][i] == 6) break; //벽 만났음 
			if(maps[x][i] !=0) continue; // 다른 cctv 
			maps[x][i]=7; //cctv 설치 
		}
	}
	private static void goRight(int[][]maps, int x, int y) {
		for(int i=y+1; i<c ; i++) {
			if(maps[x][i] == 6) break; //벽 만났음 
			if(maps[x][i] !=0) continue; // 다른 cctv 
			maps[x][i]=7; //cctv 설치 
		}
	}
	private static void goUp(int[][]maps, int x, int y) {
		for(int i=x-1; i>=0 ; i--) {
			if(maps[i][y] == 6) break; //벽 만났음 
			if(maps[i][y] !=0) continue; // 다른 cctv 
			maps[i][y]=7; //cctv 설치 
		}
	}
	private static void goDown(int[][]maps, int x, int y) {
		for(int i=x+1; i<r ; i++) {
			if(maps[i][y] == 6) break; //벽 만났음 
			if(maps[i][y] !=0) continue; // 다른 cctv 
			maps[i][y]=7; //cctv 설치 
		}
	}
	
	private static int[][] copyMap(int[][] map) {
		int map1[][]= new int[r][c];
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				map1[i][j] = map[i][j];
			}
		}
		return map1;
	}
}// end of class 
