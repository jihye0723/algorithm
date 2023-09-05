import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int num[];
	static List<ArrayList<Integer>> graph = new ArrayList<>(); 
	static boolean select[]; //선택된 구역
	static int min = 1000; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		N = Integer.parseInt(br.readLine()); //구역의 갯수 
		num = new int[N];
		select= new boolean[N]; 
		for(int i=0; i<=N; i++) graph.add(new ArrayList<>()); 
		
		st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			int n = Integer.parseInt(st.nextToken()); 
			while(n-- >0) {
				int next= Integer.parseInt(st.nextToken())-1; 
				graph.get(i).add(next);
			}
		}
		for(int i=1; i<=N/2; i++) {
			select(0, i); //N개 중에 i개 고르기 
		}
		
		if(min == 1000) System.out.println(-1);
		else System.out.println(min);
 	
	}//end of main
	
	private static void select(int index, int cnt) {
		//가능한지, 인구차이 몇인지 찾으러 ㄱㄱ 
		if(cnt==0) {
			min = Math.min(min, calSub(select)); 
			
			return; 
		}
		
		for(int i=index; i<N; i++) {
			select[i] = true; 
			select(i+1, cnt-1); 
			select[i] = false; 
		}
	}
	
	private static int calSub(boolean[] select) {
		List<Integer> first= new ArrayList<>(); 
		List<Integer> second= new ArrayList<>(); 
		int first_sum=0;
		int second_sum=0; 
		for(int i=0; i<N; i++) {
			if(select[i]) {
				first.add(i);
				first_sum += num[i]; 
			}
			else {
				second.add(i); 
				second_sum += num[i]; 
			}
		}
		
		//두 구역 다 서로 다 연결되어있어야 함 
		if(isConnected(first) && isConnected(second)) {
			return Math.abs(first_sum - second_sum); 
		}
		//하나라도 연결 안되어있으면 
		else return 1000;  
	}
	private static boolean isConnected(List<Integer> list) {
		Queue<Integer> que = new LinkedList<>();
		boolean visited[] = new boolean [N];
		visited[list.get(0)] = true;
		que.add(list.get(0)); 
		
		int count =1; //리스트에 있는 곳 다 갔는지 확인하기 위한 변수 
		while(!que.isEmpty()) {
			int node= que.poll();
			for(int next : graph.get(node)) {
				//같은 구역에 있는 곳이고 아직 방문안한 곳이면, 
				if(!visited[next] && list.contains(next)) {
					visited[next] = true; 
					que.add(next); 
					count ++; 
				}
			}
		}
		
		if(count != list.size()) return false;  
		return true; 
	}
}//end of class 
