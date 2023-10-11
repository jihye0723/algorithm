import java.io.*;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/*
 * P : 방안의 사람들 
 * S: 계단 입구 
 * 이동 완료 시간 : 모든 사람들이 게단을 내려가 아래층으로 이동을 완료한 시간 
 * 아래층 이동 : 계단 입구까지 이동 + 계단 내려가는 시간 
 * 계단위에는 동시에 3명만 올라가있을 수 있다. (이미 3명이 내려가고 있다면, 한명이 다 내려갈때까지 기다려야함)*/
 
/*10이하의 정수니까 가능한 경우의수를 다 따져봐야되나..?*/
public class Solution {
	static class Person implements Comparable<Person>{
		int num; 
		int time; //계단까지 걸리는 시간 
		
		public Person( int num, int time) {
			this.num = num; 
			this.time=time; 
		}

		@Override
		public int compareTo(Person o) {
			return this.time- o.time;
		}
	}
	static int N; 
	static int[][] map; 
	static boolean[] selected; //조합에 선택될 
	static List<int[]> stair; //계단 위치 저장 
	static List<int[]> people; //사람 위치 저장 
	static int min; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb= new StringBuilder(); 
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			stair = new ArrayList<>(); 
			people = new ArrayList<>(); 
			N= Integer.parseInt(br.readLine()); 
			
			map= new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine() ," "); 
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
					if(map[i][j]==1) {
						people.add(new int[] {i,j}); 
					}
					//계단 
					else if (map[i][j]>1) {
						stair.add(new int[] {i,j}); 
					}
				}
			}// end of input 
			
			min = Integer.MAX_VALUE;
			selected = new boolean[people.size()]; 
			for(int i=0; i<=people.size()/2; i++) {
				getComb(0,i); 
			}
			sb.append("#").append(tc).append(" ");
			sb.append(min).append("\n"); 
			
		}//end of testcase for 
		System.out.println(sb.substring(0, sb.length()-1));
	
	}//end of main
	
	//사람들 중에 cnt 명 나올 수 있는 조합 중 최대 
	private static void getComb(int index, int cnt) {
		//다 뽑았다면, 
		if(cnt==0) {
			//뽑은 사람들을 첫번재 게단에 보냈을 때 
			//뽑은 사람들을 두번째 계단에 보냈을 때 
			min = Math.min(min,Math.min(goToStair(true), goToStair(false))); 
			return ; 
		}
		
		for(int i=index; i<people.size(); i++) {
			selected[i]= true; 
			getComb(i+1, cnt-1); 
			selected[i]= false; 
		}
	}//end of getComg 
	static PriorityQueue<Person> first_stair =new PriorityQueue<>(); 
	static PriorityQueue<Person> second_stair=new PriorityQueue<>(); 
	//state인 사람들을 첫번째 게단으로 보내고, 나머지 두번재 계단으로 
	private static int goToStair(boolean state) {
		int dis=0; //계단-사람의 거리 
		for(int i=0; i<selected.length; i++) {
			//첫번째 계단으로 보내기 
			if(selected[i] == state) {
				//i사람에서 첫번째 계단까지의 위치 
				dis = Math.abs(stair.get(0)[0]- people.get(i)[0]) + Math.abs(stair.get(0)[1]-people.get(i)[1]); 
				first_stair.add(new Person(i, dis)); 
				
			}
			//두번째 게단으로 보내기 
			else {
				dis = Math.abs(stair.get(1)[0]- people.get(i)[0]) + Math.abs(stair.get(1)[1]-people.get(i)[1]); 
				second_stair.add(new Person(i, dis)); 
			}
		}// end of for 
		
		//게단 내려가기 
		return goDown(); 
		
	}//end of goToStair
	
	//게단 내려가기 
	private static int goDown() {
		//계단 높이 
		int first_height= map[stair.get(0)[0]][stair.get(0)[1]]; 
		int second_height= map[stair.get(1)[0]][stair.get(1)[1]]; 
		//계단내려가는중 남은시간 저장 
		Queue<Integer> first_down = new LinkedList<>();
		Queue<Integer> second_down= new LinkedList<>(); 
		
		int first= (first_stair.isEmpty()? Integer.MAX_VALUE : first_stair.peek().time); 
		int second= (second_stair.isEmpty()? Integer.MAX_VALUE: second_stair.peek().time); 
		//경과 시간 
		int time  =Math.min(first, second); 
		while(true) {
			//다 도착한 애들 끝내주기 
			while(!first_down.isEmpty()) {
				if(first_down.peek() >0) break;
				first_down.poll();
			}
			while(!second_down.isEmpty()) {
				if(second_down.peek()>0) break; 
				second_down.poll(); 
			}
			
			int f_size = first_down.size(); 
			int s_size= second_down.size();
			//계단에 있는 사람들 내려가는중 .. 
			while(f_size-- >0) {
				first_down.add(first_down.poll()-1); 
			}
			while(s_size-- >0) {
				second_down.add(second_down.poll()-1); 
			}
			
			//3명까지만 내려올 수 있음 
			while(first_down.size()<3) {
				if(!first_stair.isEmpty() && first_stair.peek().time <= time) {
					//진작에 도착해있었음 
					if(first_stair.poll().time < time) first_down.add(first_height-1); 
					//방금 도착 
					else first_down.add(first_height); 
				}
				else break; 
			}
			//2번계단도 마찬가지 
			while(second_down.size()<3) {
				if(!second_stair.isEmpty() && second_stair.peek().time <= time) {
					if(second_stair.poll().time < time) second_down.add(second_height-1);
					else second_down.add(second_height); 
				}
				else break; 
			}
			
			if(first_down.isEmpty() && second_down.isEmpty() && first_stair.isEmpty() && second_stair.isEmpty())
				break; 
			time++; 
		}
		
		return time;
	}//end of goDown 
	
}//end of class 
