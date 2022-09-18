import java.util.*;
import java.io.*;

public class Solution_BaekJoon_1766_문제집_골드2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder(); 
		StringTokenizer st ;
		
		String s= br.readLine();
		st= new StringTokenizer(s, " ");
		int N= Integer.parseInt(st.nextToken());
		int M= Integer.parseInt(st.nextToken());
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<Integer>()); 
		}
		
		int[] count = new int[N+1]; //이전 연결 차수 
		while(M-->0) {
			String s1= br.readLine();
			st= new StringTokenizer(s1, " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 
			list.get(a).add(b);
			count[b]++;
		}
	
		PriorityQueue<Integer> que = new PriorityQueue<>(); 
		for(int i=1; i<=N; i++) {
			if(count[i]==0) {
				que.add(i);
				count[i]=-1;
			}
		}
		
		while(!que.isEmpty()) {
			int now = que.poll(); // 더 작은 값 나오겠지 ? 
			sb.append(now).append(" ");
			
			List<Integer> nli = list.get(now);
			if(!nli.isEmpty()) {
				int size = nli.size();
				for(int i=0; i<size; i++) {
					count[nli.get(i)]--;
				}
			}
			
			for(int i=1; i<=N; i++) {
				if(count[i]==0) {
					que.add(i);
					count[i]=-1;
				}
			}
		}

		System.out.println(sb);
	}
}
