import java.io.*;
import java.util.*;

public class Solution {
	static int N,M,C;
	static int[][] map; 
	static int [] max_cnt; //각 줄에서 채취할 수 있는 최대 양 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T = Integer.parseInt(br.readLine());//테스트케이스의 갯수 
		for(int tc=1; tc<=T; tc++) {
			st= new StringTokenizer(br.readLine(), " "); 
			N= Integer.parseInt(st.nextToken()); //벌꿀의 크기 
			M= Integer.parseInt(st.nextToken()); //한사람이 채취할 수 있는 갯수 
			C= Integer.parseInt(st.nextToken()); //한 사람이 채취할 수 있는 양 
			
			map = new int[N][N];
			max_cnt = new int[N]; 
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " "); 
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			getMax(); 

			Arrays.sort(max_cnt);
			int dif= max_cnt[N-1] + max_cnt[N-2]; //서로 다른줄에서 골랐을 경우의 최댓값 
			sb.append("#").append(tc).append(" "); 
			if(N>= M*2) {
				getSameMax(); 
				Arrays.sort(max_cnt);
				dif = Math.max(dif, max_cnt[N-1]); 
			}

			sb.append(dif).append("\n"); 

			//같은줄에 두명의 벌꿀양봉자가 들어갈 수 있을 때 , 
			//같은줄에 두명 들어갔을 때의 최댓값도 구해서, 서로다른거랑 비교 
//			else {
//				getSameMax(); 
//				Arrays.sort(max_cnt);
//				dif = Math.max(dif, max_cnt[N-1]); 
//			}
			
			
//			for(int i=0; i<N; i++) {
//				System.out.println(max_cnt[i]);
//			}
			
		}//end of testcase for
		
		
		System.out.println(sb.substring(0,sb.length()-1));
	}//end of main
	
	//각 줄에서 최대 M개, 최대 c의 양으로 얻을 수 있는 최댓값 구하기 
	static int getComb_max; 
	static boolean isComb[];
	//두명이 같은줄에서 골랐을때의 최댓값 
	private static void getSameMax(){
		Arrays.fill(max_cnt, 0);
		for(int i=0; i<N; i++) {
			
			for(int a=0; a<=(N-2*M); a++) { //첫번째 업자 
				for(int b=a+M; b<=N-M; b++) { //두번째 업자 
					max_cnt[i] = Math.max(max_cnt[i], cntMax(i,a,b)); 
				}
			}
			
			
		}//각행에 대해 탐색 
	}//end of getSameMax
	
	//i번째 행에서 a~ , b~ 
	private static int cntMax(int i, int a, int b) {
		int[] first_select= new int[M]; 
		int[] second_select= new int[M]; 

		int first_sum =0; int second_sum=0;
		int sum=0; //둘의 합 (최대)
		//벌꿀업자들이 고를거 넣어주기 
		for(int m=0; m<M; m++) {
			first_select[m] = map[i][a++]; 
			second_select[m] = map[i][b++]; 
			
			first_sum += first_select[m]; 
			second_sum += second_select[m]; 
		}
		
		if(first_sum <= C) {
			for(int s: first_select) {
				sum+=Math.pow(s, 2); 
			}
		}
		else {
			int comb_max=0; 
			for(int cnt =M-1; cnt>=1; cnt--) {
				isComb = new boolean[M]; 
				getComb_max =0; 
				getComb(cnt, first_select, 0);
				comb_max= Math.max(getComb_max, comb_max);
			}
			sum += comb_max; 
		}
		
		if(second_sum <=C) {
			for(int s: second_select) {
				sum+=Math.pow(s, 2); 
			}
		}
		else {
			int comb_max=0; 
			for(int cnt =M-1; cnt>=1; cnt--) {
				isComb = new boolean[M]; 
				getComb_max =0; 
				getComb(cnt, second_select, 0);
				comb_max= Math.max(getComb_max, comb_max);
			}
			sum += comb_max; 
		}
		
		return sum; 
	}//end of cntMAX
	
	private static void getMax() {
		Arrays.fill(max_cnt, 0);
		int[] select; 
		int sum; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				select = new int[M]; //m개를 골라서 담기 
				sum=0; 
				for(int m=j, index=0; m<j+M; m++,index++) {
					select[index] = map[i][m];
					sum+=select[index]; 
				}
				//그냥 다 채취해도됨 
				if(sum <= C) {
					int res=0; 
					for(int s : select) {
						res += Math.pow(s, 2);
					}
					max_cnt[i] = Math.max(max_cnt[i], res); 
				}
				//M-1 ~ 1개 조합을 찾아서 최댓값 구해야함 
				else {
					int comb_max =0; 
					for(int cnt = M-1; cnt >= 1; cnt--) {
						isComb = new boolean[M]; 
						getComb_max =0; 
						getComb(cnt, select, 0);
						comb_max = Math.max(comb_max, getComb_max); 
					}
					max_cnt[i]= Math.max(max_cnt[i], comb_max); 
				}
			}
		}
	}//end of getMax
	
	//cnt개를 골랐을 때의 최ㄷ댓값 
	private static void getComb(int cnt, int[] select, int index) {
		if(cnt ==0 ) {
			int sum=0; 
			int issum=0; 
			for(int i=0; i<M; i++) {
				if(isComb[i]) {
					issum += select[i]; 
					sum += Math.pow(select[i], 2); 
				}
			}
			if(issum > C) return; 
			getComb_max = Math.max(getComb_max, sum); 
			return ; 
		}
		
		for(int i= index; i<M; i++) {
			isComb[i]= true; 
			getComb(cnt-1, select, i+1);
			isComb[i]= false; 
		}
	}
}//end of class 
