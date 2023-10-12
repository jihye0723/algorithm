import java.io.*;
import java.util.*;
/*
 * 고객은 도착한 순서대로 1번부터 번호 부여받음  (일찍도착할 수록 번호 작음) 
 * 접수 : 여러고객이 있을 경우 고객번호 낮은 순(먼저 온 순) 
 *     : 빈 창구가 여러개일 경우, 고객은 창구번호가 작은곳으로 간다. 
 * 정비 : 먼저 기다리고 있는 고객 먼저 (고객번호랑은 상관 없음) 
 *    : 두명이상이 동시에 정비창구에 도착했을 경우, 접수 창구번호 작은 순 
 *    : 빈 창구가 여러개일 경우 정비 창구번호 작은곳 */ 
public class Solution {
	//접수를 기다리고 있는 고객 
	static class Customer implements Comparable<Customer>{
		int num; //고객 번호 
		int time; //도착 시간 
		public Customer(int num, int time) {
			this.num = num;
			this.time = time; 
		}
		@Override
		public int compareTo(Customer o) {
			return this.num - o.num; 
		}
	}
	
	//정비를 기다리고 있는 고객 
	static class Customer_next implements Comparable<Customer_next>{
		int num; //고객 번호 
		int regist_num ;//접수 창구 번호 
		int time; //대기 시작 시간 
		
		public Customer_next(int num, int regist_num, int time) {
			this.num = num;
			 this.regist_num = regist_num;
			 this.time= time; 
		}
		
		@Override
		public int compareTo(Customer_next o) {
			if(this.time == o.time) {
				return this.regist_num - o.regist_num; 
			}
			return this.time - o.time; 
		}
		
	}
	//사용중인 창구 정보 
	static class Room implements Comparable<Room>{
		int num; //몇번 창구인지 
		int cus_num; //몇번 고객이 사용중인지 
		int end_time; //종료시간이 언제인지 
		
		public Room(int num, int cus_num, int end_time) {
			this.num = num;
			this.cus_num =cus_num;
			this.end_time = end_time; 
		}

		@Override
		public int compareTo(Room o) {
			if(this.end_time == o.end_time) {
				return this.num - o.num; //얘가 먼저 나가야함 만약 같을 경우 (정비가 접수 창구번호 순이라서)
			}
			return this.end_time - o.end_time; 
		}
	}
	static int N,M,K;
	static int a,b; 
	static int[] nTime; 
	static int[] mTime; 
	static boolean isEnd[]; 
	static Set<Integer> set; 
	static PriorityQueue<Customer> regist_wait; //접수 대기 고객 
	static PriorityQueue<Customer_next> fix_wait; //정비 대기 고객 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스의 갯수 
		for(int tc=1; tc<=T; tc++) {
			st= new StringTokenizer(br.readLine(), " ");
			//접수창구, 정비창구, 고객번호 다 0부터 시작 
			 N= Integer.parseInt(st.nextToken());//접수창구의 개수 
			 M= Integer.parseInt(st.nextToken());//정비창구의 갯수 
			 K= Integer.parseInt(st.nextToken()); //고객의 수 
			//지갑 두고간 고객의 이용 정보 
			 a= Integer.parseInt(st.nextToken())-1; 
			b= Integer.parseInt(st.nextToken())-1; 
			
			isEnd = new boolean[K];//각 고객의 접수 , 정비가 끝났는지  
			nTime= new int[N]; 
			mTime= new int[M]; 
			not_used_regist =new PriorityQueue<>();
			not_used_fix =new PriorityQueue<>();
			used_regist= new PriorityQueue<>();
			used_fix= new PriorityQueue<>();
			set = new HashSet<>(); 
			
			//n과 m은 무조건 1개 이상 주어짐 
			st= new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				nTime[i] = Integer.parseInt(st.nextToken()); 
				not_used_regist.add(i); 
			}
			
			st= new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<M; i++) {
				mTime[i] = Integer.parseInt(st.nextToken()); 
				not_used_fix.add(i); 
			}
			
			regist_wait= new PriorityQueue<>();//접수 대기 고객
			fix_wait = new PriorityQueue<>(); 
			st= new StringTokenizer(br.readLine(), " "); 
			for(int i=0; i<K; i++) {
				regist_wait.add(new Customer(i, Integer.parseInt(st.nextToken()))); 
			}
			
			int res= start(); 
			sb.append("#").append(tc).append(" ").append(res).append("\n");
			
			//마지막에 고객번호 더할 때 1씩 더해줘야됨, 고객번호 0번부터 시작했음 ! 
		}//end of testcase for 
		System.out.println(sb.substring(0, sb.length()));
	

	}//end of main
	
	static PriorityQueue<Integer> not_used_regist ; //사용하고 있지 않은 접수 창구 번호 
	static PriorityQueue<Integer> not_used_fix ; //사용하고 있지 않은 정비 창구 번호 
	static PriorityQueue<Room> used_regist; //사용하고 있는 접수 창구 정보 
	static PriorityQueue<Room> used_fix; 
	private static int start() {
		int time = regist_wait.peek().time; //가장 먼저 도착한 고객 중심으로 시작 
		
		while(true) {
			//접수나 정비중 end_time == time인 사람들 종료시키기 
			endBefore(time);
			
			regist(time); //접수창구로 보낼 수 있는 고객 보내기 
			
			fix(time); //정비창구로 보낼 수 있는 고객 보내기 
			
			if(isEnd()) break;
			time++; 
		}
		
		
		if(set.isEmpty()) return -1;
		
		int sum=0; 
		for(int a : set) {
//			System.out.println(a);
			sum += (a+1); 
 		}
		return sum; 
	}//end of start 
	
	//각 큐에 들어있는 end_time==time일 경우 접수나 정비가 끝났다는 뜻 
	//1. 접수가 끝났을 경우 정비 대기 큐로 넣는다. 
	//2. 정비가 끝났을 경우 map에 정보 저장 
	private static void endBefore(int time) {
		//1, 접수 먼저 끝내기 
		Room r; 
		while(!used_regist.isEmpty()) {
			if(used_regist.peek().end_time == time) {
				r= used_regist.poll(); 
				not_used_regist.add(r.num); //다시 사용할 수 있는 접수 창구가 되었음 
				//접수번호가 a랑 같은거 있으면 
				if(r.num == a) {
					set.add(r.cus_num); //고객번호 넣기 
				}
				
				//해당 고객을 정비 대기 큐로 넣어야 함 
				//고객번호, 접수창구번호, 대기시간
				fix_wait.add(new Customer_next(r.cus_num, r.num, time)); 
			}
			//아직 끝날 수 있는 상담이 없음 
			else if (used_regist.peek().end_time > time) break; 
		}
		
		
		//2. 정비 끝낼 수 있으면 끝내기 
		while(!used_fix.isEmpty()) {
			if(used_fix.peek().end_time == time) {
				r= used_fix.poll(); 
				not_used_fix.add(r.num); 

				//접수창구가 같았던 고객만 생각하면 됨 
				if(set.contains(r.cus_num)) {
					//b랑 같으면 그냥 냅두고 
					
					//다르면 set에서 제거 
					if(r.num != b) {
//						System.out.println(r.cus_num+"이 사용한 정비 번호 " + r.num);
						set.remove(r.cus_num); 
					}
				}
				isEnd[r.cus_num] = true; 
			}
			else if (used_fix.peek().end_time > time) break; 
		}
	}//end of endBefore
	
	//현재 시각 : time , 접수 창구에 도착해 있고 보낼 수 있는 고객들 보내기 
	private static void regist(int time) {
		//접수 가능한 창구가 있을 동안 
		while(!not_used_regist.isEmpty()) {
			//기다리고 있는 사람이 없거나 (=모두 접수 했거나), 아직 도착한 사람이 없으면 break;  
			if(regist_wait.isEmpty() || regist_wait.peek().time > time) break; 
			
			Customer cus= regist_wait.poll(); //젤 앞에 기다리고 있는 사람 
			int regist_num = not_used_regist.poll(); //위 사람이 갈 창구 
			//창구번호, 고객 번호, 해당 창구 종료시간 
			used_regist.add(new Room(regist_num, cus.num, time+nTime[regist_num])); 
		}
	}//end of regist
	
	private static void fix(int time) {
		while(!not_used_fix.isEmpty()) {
			if(fix_wait.isEmpty() || fix_wait.peek().time > time) break; 
			
			Customer_next cus = fix_wait.poll(); //젤 앞 사람 
			int fix_num = not_used_fix.poll();
			
			used_fix.add(new Room(fix_num, cus.num, time+mTime[fix_num])); 
		}
	}//end of fix
	
	//모두가 끝났다는 뜻 
	private static boolean isEnd() {
		for(int i=0; i<K; i++) {
			if(!isEnd[i]) return false; 
		}
		return true; 
	}//end of isEnd
	
}//end of class 
