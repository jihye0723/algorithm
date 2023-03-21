import java.util.*;

public class Main {
	public static void main(String[] args)  {
		Scanner sc= new Scanner(System.in);
		Queue<Integer> que = new LinkedList<>(); 
		StringTokenizer st= new StringTokenizer(sc.next(), "-");
		StringTokenizer st1; 
		
		while(st.hasMoreTokens()) {
			String s = st.nextToken(); // 55  50+40
			st1= new StringTokenizer(s, "+");
			int sum=0; 
			while(st1.hasMoreElements()) {
				sum+= Integer.parseInt(st1.nextToken()); 
			}
			que.add(sum); 
		}
		
		int res = que.poll(); 
		while(!que.isEmpty()) {
			res -= que.poll(); 
		}
		System.out.println(res);
		

	
	}//end of main 
}//end of class
