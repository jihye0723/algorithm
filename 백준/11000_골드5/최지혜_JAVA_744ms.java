import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		int[][] classes = new int[n][2]; // [시작시간][종료시간]
		
		for(int i =0; i<n; i++){
			String s = br.readLine();
			StringTokenizer st= new StringTokenizer(s, " ");
			
			classes[i][0] = Integer.parseInt(st.nextToken()); 
			classes[i][1] = Integer.parseInt(st.nextToken()); 
			
		}
		
		// 시작시간을 오름차순으로 정렬 ( 시작시간이 같다면 종료시간 오름차순 ) 
		Arrays.sort(classes, (s1,s2)->{
			if(s1[0]==s2[0]) return s1[1]-s2[1]; 
			return s1[0]-s2[0]; 
		});
		
		
		PriorityQueue<Integer> lectroom = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			int start= classes[i][0];
			int end = classes[i][1]; 
			
			// 제일 처음강의 > 종료시간 넣기 
			if(lectroom.isEmpty()) lectroom.add(end); 
			
			else {
				int beforeend = lectroom.peek();
				
				// 같은 강의실에서 들을 수 있는 경우, 
				if(start >= beforeend) {
					// 해당 강의실에 강의 종료시간을 갱신 
					lectroom.poll();
					lectroom.add(end); 
				}
				// 같은 강의실에서 들을 수 없는 경우, 
				else { 
					// 우선순위큐이기 때문에, 가장 앞의 강의에 이어지지 않는다면, 다음거도 x 
					lectroom.add(end);
				}
			}
		}
	
		System.out.println(lectroom.size());
	}
}
