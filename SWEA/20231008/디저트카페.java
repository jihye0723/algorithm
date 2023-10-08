import java.io.*;
import java.util.*;
/*
 * 숫자 : 디저트의 종류 
 * 서로 대각선 방향으로 이동 가능
 * 사각형 모양을 그리며 다시 출발한 곳으로 돌아와야됨 
 * 사각형 모양 (=마주보고 있는 변의 길이가 같아야 함) 
 * 같은 번호가 있으면 안된다. 까다롭네.. 
 * 무조건 사각형!이 만들어져야함 (직선이나 점 안됨) 
 * 디저트를 가장 많이 먹을 수 있는 경로 찾아서, 디저트 수 출력 
 * 만약 없다면 -1  
 */
/*
 * 가능한 시작점 후보 
 * 젤 위 , 젤 아래 줄 뺴고 
 * 오른쪽에서 2줄 빼고*/
public class Solution {
	static int N;
	static int[][] map; 
	static int di[]= {-1,1,1,-1};
	static int dj[]= {1,1,-1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T= Integer.parseInt(br.readLine());//테스트케이스의 갯수
		for(int tc=1; tc<=T; tc++) {
			N= Integer.parseInt(br.readLine());
			map= new int[N][N]; 
			for(int i=0; i<N; i++) {
				st= new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			max= -1;
			start(); 
			sb.append("#").append(tc).append(" ");
			sb.append(max).append("\n");
		}//end of testcase 
		System.out.println(sb.substring(0, sb.length()-1));
	}//end of main
	
	private static void start() {
		//가능한 출발점의 후보 
		for(int i=1; i<N-1; i++) {
			for(int j=0; j<N-2; j++) {
				makeRect(i,j); 
			}
		}
	}//end of start
	
	static Set<Integer> set;
	static int max;
	//i,j에서 시작하여 가능한 직사각형 만들기 
	private static void makeRect(int i,int j) {
		//최소 만들 수 있는 변의길이 1 부터 최대 i까지 
		for(int len_1 = 1; len_1 <= i; len_1++) {
			for(int len_2 = 1; len_2 < (N-i); len_2++ ) {
				set= new HashSet<>(); 
//				System.out.println("시작점 : "+i+","+j+" ,가로길이 :"+len_1+", 세로길이 :"+len_2);
				dfs(i,j,len_1, len_2, len_1, len_2, 0, 0, set);
//				System.out.println(max);
			}
		}
	}//end of makeRect
	
	//위치, 가로길이, 세로길이, 얼마나 탐색햇는지, 몇번째 변 탐색 중인지,  합, 현재 탐색하고 있는 수 
	private static void dfs(int i, int j, int len_1, int len_2, int l1, int l2, int cnt, int sum, Set<Integer> set) {
		//첫번째 세번쨰 변 탐색중인데, l1 이 0이면 방향 바꿔야함 
		if(l1 ==0) dfs(i,j,len_1, len_2, len_1, l2, cnt+1, sum, set); 
		//두번째 네번째 변 탐색중인데, l2 이 0이면 방향 바꿔야함 
		if(l2 ==0) {
			//마지막 변이었음
			if(cnt==3) {
				max= Math.max(sum, max); 
			}
			dfs(i,j,len_1,len_2,l1,len_2, cnt+1,sum,set); 
		}
		//가로 변 탐색 중 
		if(cnt==0 || cnt==2) {
			int ni = i+di[cnt];
			int nj = j+dj[cnt];
			
			//경계 밖을 벗어나거나, 이미 같은 것이 포함 되어 있다면 > 만들 수 없는 사각형 
			if(isOut(ni, nj) || set.contains(map[ni][nj])) return; 
		
			set.add(map[ni][nj]); 
			sum+=1; 
			dfs(ni, nj, len_1, len_2, l1-1, l2, cnt, sum, set); 

		}
		
		//세로 변 탐색 중 
		if(cnt==1 || cnt==3) {
			int ni = i+di[cnt];
			int nj = j+dj[cnt];
			
			if(isOut(ni,nj) || set.contains(map[ni][nj])) return;
			
			set.add(map[ni][nj]); sum+=1;
			dfs(ni,nj,len_1, len_2, l1, l2-1, cnt, sum, set); 

		}	
	}//end of dfs
	
	private static boolean isOut(int i, int j) {
		return i<0 || i>=N|| j<0 ||j>=N; 
	}
}//end of class 
