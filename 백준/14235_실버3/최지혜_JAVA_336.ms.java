import java.util.*;
import java.io.*;
public class Solution_BaekJoon_14235_크리스마스선물 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ; 
		StringBuilder sb = new StringBuilder(); 
		int N = Integer.parseInt(br.readLine()); 
		
		// 정수 내림차순 ( 가장 큰 것이 젤 먼저 나오도록 ) 
		PriorityQueue<Integer> que= new PriorityQueue<>(Collections.reverseOrder());
		
		while(N-- > 0) {
			String s = br.readLine();
			st = new StringTokenizer(s, " ");
			
			int a = Integer.parseInt(st.nextToken()); 
			// 아이들을 만난 경우, 정렬한 선물 중 가장 큰 것을 준다 . 
			if(a==0) {
				if(que.isEmpty()) sb.append(-1).append("\n");
				//선물 중 가장 큰것 
				else {
					sb.append(que.poll()).append("\n");
				}
			}
			// 거점지 > 선물 충전 
			else {
				for(int i=0; i<a; i++) {
					que.add(Integer.parseInt(st.nextToken()));
				}
			}
		
		}
		System.out.println(sb);
		
	}
}
