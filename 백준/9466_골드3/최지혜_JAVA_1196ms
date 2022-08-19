import java.io.*;
import java.util.*;

public class Main{
	private static boolean[] visited ; // 방문 체크 
	private static boolean[] loop ; // 해당 학생이 팀이 만들어 졌는지, ex ) 4-6-7-4 이면 loop[4] =true
	private static int[] team; 
	private static List<Integer> make; 
	private static int count; // 팀 만들어진 학생 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 갯수 
		for (int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine()); // 학생 수 N명
			team = new int[N+1];
			team[0] = 0; 
			loop = new boolean[N+1]; 
			
			StringTokenizer st= new StringTokenizer(br.readLine()," "); 
			for(int li=1; li<=N; li++) {
				team[li] = Integer.parseInt(st.nextToken());
			}
 			
			count = 0; 
			visited = new boolean[N+1]; // 방문 체크 
			for(int start=1; start<=N; start++) {
				// 방문기록 있거나, 이미 팀이 꾸려진 학생이면 탐색할 필요 x  
				if(visited[start] || loop[start]) continue;
	
				make = new LinkedList<Integer>(); 
				makeTeam(start); 
				
			}
			sb.append(N-count).append("\n");
			
		}// end of testcase 
		System.out.println(sb);
	 
	} //end of main 

	/*팀 만들기 중*/
	private static void makeTeam(int from) {
		// 방문 처리 
		visited[from] = true;
		make.add(from); 
		// 다음 사람 
		int to = team[from];
		
		//다음 사람이 방문 체크 되있다 -> 앞에서 이미 선택됬던 아이다 -> 순환이 발생했다 -> 팀 결성 
		if(visited[to]) {			
			//to-?-?-...-to : to를 기준으로 팀이 만들어진거임  , loop= true 처리 
			// 현재 담고 있는 리스트에 to 가 있으면 ,
			int index = make.indexOf(to); 
			//if(make.contains(to) ) {
			if( index != -1) {
				loop[to] = true;
				count+=(make.size() - index);		
				
			}
			
			return;
		}
		makeTeam(to);
	}

}

