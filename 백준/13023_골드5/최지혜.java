import java.util.*;
import java.io.*;
	
public class Main {
	
	static boolean checked[] ; 
	static ArrayList<List<Integer>> list = new ArrayList<>() ; 
	static int N; 
	static int result; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //사람의 수 
		int M = Integer.parseInt(st.nextToken()); //관계의 수 
		for(int i=0; i<N; i++) list.add(new ArrayList<>()); 
		
		while(M-- >0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		for(int i=0; i<N; i++) {			
			checked= new boolean[N]; 
			checked[i] = true; 
			dfs(i,0); //i를 처음 A로 두어 친구관계 성립하는지 확인 
			if(result == 1 ) {
				break; 
			}
		}
		
		System.out.println(result);
	}//end of main 
	
	private static void dfs(int i, int cnt) {		
		if(cnt == 4) {
			result = 1;
			return; 
		}
		
		for(int j : list.get(i)) {
			if(!checked[j]) {
				checked[j] = true; 
				dfs(j, cnt+1);
				checked[j] = false;
			}
		}
		
	}
}//end of class 
