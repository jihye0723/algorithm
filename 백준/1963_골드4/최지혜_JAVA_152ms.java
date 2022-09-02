import java.util.*;
import java.io.*;
/**
 * primary 배열에 소수인것은 값을 true 로 해서 저장 해둔다 .
 * 최소 횟수 : bfs 로 가자 
 * 탐색하면서, 1,2,3,4 자리 순으로 하나씩 수 바꾸기 
 * 만약 소수이면, 방문체크 하고 큐에넣어주기 
 *
 *  */
public class Solution_BaekJoon_1963_소수경로_골드4 {
	static boolean primary[];	
	static boolean visited[]; 
	
	static class Password implements Comparable<Password>{
		int pw; 
		int count;
		
		public Password(int pw, int count) {
			this.pw = pw;
			this.count = count;
		}
		
		public int get1() {
			return pw/1000;
		}
		public int get2() {
			return (pw/100)%10; 
		}
		public int get3() {
			return (pw/10)%10;
		}
		public int get4(){
			return pw%10;
		}
		@Override
		public int compareTo(Password o) {
			return this.count - o.count; 
		}
		
	}
	private static void isPrimary() {
		primary = new boolean[10000];
		//네자리수 1000~9999중에 소수인 수는 primary = true 
		for(int a = 1000; a<10000; a++) {
			int isflag= 0; // 소수가 아니면 1 로, 
			for(int i=2; i<=Math.sqrt(a); i++) {
				//하나라도 나누어지는게 있으면 소수x, 다음 a 로 
				if(a%i==0) {
					isflag=1;
					break;
				}
			}
			if(isflag==0) { 
				primary[a] = true;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		isPrimary();
		int TC = Integer.parseInt(br.readLine()); 
		for(int i=0; i<TC; i++){
			String s =br.readLine();
			st= new StringTokenizer(s, " ");
			int start= Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int res = bfs(start, end);
			
			if (res == -1) System.out.println("impossible");
			else System.out.println(res);
			
		}//end of testcase for 
	}
	private static int bfs(int start, int end) {	
		visited = new boolean[10000];
		PriorityQueue<Password> que  = new PriorityQueue<>();
		que.add(new Password(start, 0));
		visited[start] = true;
		while(!que.isEmpty()) {
			Password p = que.poll();
			int npw = p.pw;
			int ncount = p.count;
			
			if(npw == end) {
				return ncount;
			}
			
			//각 자릿수 변경 0|1|2|3
			for(int i=0; i<=9; i++) {
				// 첫번째 자리수 변경 ( 현재와 같으면 안됨 )
				if(i!=p.get1()) {
					if(i!=0) {
					int num[] = {i, p.get2(), p.get3(), p.get4()}; 
					int pwd = makePass(num); 
					//소수이고, 방문하지 않았던 곳이라면, 
					if(primary[pwd] && !visited[pwd]) {
						visited[pwd] = true;
						que.add(new Password(pwd, ncount+1));	
					}
					}
				 }
				// 두번째 자리수 변경 ( 현재와 같으면 안됨 )
				if(i!=p.get2()) {
					int num[] = {p.get1(), i, p.get3(), p.get4()};
					int pwd = makePass(num); 
					//소수이고, 방문하지 않았던 곳이라면, 
					if(primary[pwd] && !visited[pwd]) {
						visited[pwd] = true;
						que.add(new Password(pwd, ncount+1));	
					}					
				 }
				// 세번째 자리수 변경 ( 현재와 같으면 안됨 )
				if(i!=p.get3()) {
					int num[] = {p.get1(), p.get2(), i , p.get4()};
					int pwd = makePass(num); 
					//소수이고, 방문하지 않았던 곳이라면, 
					if(primary[pwd] && !visited[pwd]) {
						visited[pwd] = true;
						que.add(new Password(pwd, ncount+1));	
					}		
					
				 }
				// 첫번째 자리수 변경 ( 현재와 같으면 안됨 )
				if(i!=p.get4()) {
					int num[] = {p.get1(), p.get2(), p.get3(),i};
					int pwd = makePass(num); 
					//소수이고, 방문하지 않았던 곳이라면, 
					if(primary[pwd] && !visited[pwd]) {
						visited[pwd] = true;
						que.add(new Password(pwd, ncount+1));	
					}		
					
				 }
			}
			
		}
		return -1; 
	}
	
	// 각 자릿수를 가지고 4자리 비밀번호 정수를 만드는 함수 
	private static int makePass(int[] num) { 
		//num[0] , num[1], num[2], num[3]
		int pass=0; 
		for(int i=0; i<num.length; i++) {
			pass += num[i] * Math.pow(10, 3-i); 
			
		}
		return pass;
	}
}
