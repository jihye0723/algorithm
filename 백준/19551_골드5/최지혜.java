import java.util.*;
import java.io.*;
	
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int N= Integer.parseInt(st.nextToken());//연병장의 크기 
		int M= Integer.parseInt(st.nextToken());//조교의 수 
		
		int height[]= new int[N];
		st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) {
			height[i] = Integer.parseInt(st.nextToken()); 
		}
		
		int change[] = new int[N+1];
		
		while(M-->0) {
			st= new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken()); 
			
			change[a]+=c; 
			change[b+1] += c*(-1); 
		}
		
		for(int i=1; i<N; i++) {
			change[i] += change[i-1]; 
		}
		
		
		for(int i=0; i<N; i++) {
			System.out.print((height[i] + change[i])+" ");
		}
		
	}
}//end of class 
