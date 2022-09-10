import java.io.*;
import java.util.*;

public class Solution_BaekJoon_2056_작업_골드5 {
	
	static class Work{
		int num; //몇번 작업 ?
		int time; // 소요시간 
		int preCount; // 선행되어야할 작업 갯수 
		List<Integer> list ; //선행작업 목록
		
		public Work(int num, int time, int preCount, List<Integer> list) {
			super();
			this.num = num;
			this.time = time;
			this.preCount = preCount;
			this.list = list;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		Queue<Work> que = new LinkedList<>(); //수행해야 할 작업들

		int N = Integer.parseInt(br.readLine());
		int[] finished = new int[N+1]; //끝난시간 넣어주기 ( 0이 아니면 끝난거 ) 
		for(int i=1; i<=N; i++) {
			String s= br.readLine();
			st= new StringTokenizer(s, " "); 
			List<Integer> list = new ArrayList<>(); 
			
			int time= Integer.parseInt(st.nextToken()); 
			int pre = Integer.parseInt(st.nextToken()); 
			for(int p=0; p<pre; p++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			que.add(new Work(i,time,pre,list));
		}
		
		while(!que.isEmpty()) {
			Work now = que.poll();
			
			int num = now.num; 
			int time= now.time;
			int preCount = now.preCount; // 선행되어야할 갯수 
			if(preCount == 0) {
				// 선행되어야 할 것 없음 , 바로 수행 하면 됨 
				finished[num] += time; 
				continue;
			}
			List<Integer> list = now.list; // 선행되어야 할 수행 목록 
			boolean isAll = true; 
			int max=0; 
			//선행목록들이 다 수행되었는지 체크 isAll
			for(int i=0; i<list.size(); i++) {
				// finished 값이 0 이면, 아직 수행 다 안된 것 
				if(finished[list.get(i)]==0) {
					isAll = false;
					break; 
				}
				max=  Math.max(max, finished[list.get(i)]);
			}
			// 아직 선행이 다 안됬기 때문에, 조금 있다가 다시 수행해야 함 . 
			if(!isAll) {
				que.add(new Work(num, time, preCount, list)); 
			}
			// 선행이 다 됬음 
			else {
				finished[num] += (max + time); 
			}
			}
		
		int res = 0; 
		for(int i=0; i<finished.length; i++) {
			res = Math.max(res, finished[i]);
		}
		System.out.println(res);
	}
		
}
