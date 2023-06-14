import java.util.*;
import java.io.*;
	
public class Main {
	//대기 중인  회의 
	static class Ready implements Comparable<Ready>{
		int start; 
		int end;
		
		public Ready(int start, int end) {
			this.start= start;
			this.end= end; 
		}

		//시작시간 기준 오름차순 정렬 
		@Override
		public int compareTo(Ready o) {
			return this.start - o.start; 
		}
	
	}
	//진행 중인 회의 
	static class Meet implements Comparable<Meet>{
		int start; 
		int end;
		
		public Meet(int start, int end) {
			this.start = start;
			this.end= end ;
		}
		
		//종료시간 기준으로 오름차순 정렬 
		@Override
		public int compareTo(Meet o) {
			return this.end - o.end; 
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		int N= Integer.parseInt(br.readLine()); //회의  갯수 
		PriorityQueue<Ready> ready = new PriorityQueue<>();
		PriorityQueue<Meet> meet= new PriorityQueue<>();
 		StringTokenizer st;
 		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			int a = Integer.parseInt(st.nextToken()); //회의 시작시간
			int b = Integer.parseInt(st.nextToken()); //회의 종료시간 
			ready.add(new Ready(a,b)); 
		}
 		
 		while(!ready.isEmpty()) {
 			Ready now = ready.poll(); 
 			int start= now.start;
 			int end= now.end;
 			
 				//현재 진행중인 가장 빨리 끝나는 회의 뒤에 할 수 있을 때 
 			if(!meet.isEmpty() && meet.peek().end <= start) {
 				meet.poll();
 			}
 			meet.add(new Meet(start, end)); 
 			
 		}
 				
 		System.out.println(meet.size());
		
	}//end of main 
}//end of class 
