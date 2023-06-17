import java.util.*;
import java.io.*;
	
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		int testcase= Integer.parseInt(br.readLine());//테스트케이스의 갯수 
		
		while(testcase-- >0) {
			st= new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());//초기값 
			int b = Integer.parseInt(st.nextToken());//목표값 
			
			
			System.out.println(bfs(a,b));
		}
	
	}//end of main 
	
	static class Command implements Comparable<Command>{
		int a; 
		
		String command; //명령 조합 
		
		public Command(int a, String command) {
			this.a= a;
			this.command = command; 
		}

		@Override
		public int compareTo(Command o) {
			return this.command.length() - o.command.length(); 
		}	
	}
	
	private static String bfs(int a, int b) {
		boolean[] visited= new boolean[10000]; 
		PriorityQueue<Command> que = new PriorityQueue<>();

		que.add(new Command(a,""));
		visited[a] = true; 
		
		while(!que.isEmpty()) {
			Command c= que.poll();
			int word= c.a;
			String cmd = c.command;
			
			if(word==b) return cmd; 
			
			int D= commandD(word);
			int S= commandS(word);
			int L= commandL(word);
			int R= commandR(word);
			
			if(!visited[D]) {
				visited[D]= true; 
				que.add(new Command(D, cmd+"D"));
			}
			if(!visited[S]) {
				visited[S]= true; 
				que.add(new Command(S, cmd+"S"));
			}
			if(!visited[L]) {
				visited[L]=true;
				que.add(new Command(L, cmd+"L"));
			}
			if(!visited[R]) {
				visited[R]= true;
				que.add(new Command(R, cmd+"R"));
			}
			
		}
		return ""; 
	}
	
	private static int commandD(int a) {
		a *=2; 
		if(a > 9999) a %= 10000; 
		
		return a; 
	}
	private static int commandS(int a) {
		if(a==0) a=9999;
		else a-=1;
		
		return a;
	}
	//1234 -> 2341
	//16 -> 160
	private static int commandL(int a) {
		int front= a%1000; 
		int back = a/1000;
		
		return front*10 + back; 
	}
	//1234 -> 4123
	//16-> 6001
	private static int commandR(int a) {
		int front = a%10;
		int back = a/10;
		
		return front*1000 + back; 
	}
}//end of class 
