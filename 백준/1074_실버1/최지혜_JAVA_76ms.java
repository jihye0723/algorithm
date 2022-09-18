import java.io.*;
import java.util.*;

public class Main {
	
	private static int res;
	private static int size;

	public static void find(int r, int c , int size ) {
			if (size==1) return ; 
			size/=2; 
			int summore = (int) Math.pow(size, 2); // 이전 지나온 사분면에 대한 합 
			if(size > r ) { // 1,2 분면
				if(size> c ) { // 1 
					find(r,c,size);
				} 
				else // 2
				{
				res+=summore; //1 분면 크기 더하기 
				find(r,c-size,size); 
				}
			}
			else { 
				if(size> c ) { // 3 
				res+= summore*2;
				find(r-size ,c ,size);
				
				}
				else { // 4
				res+=summore * 3;
				find(r-size ,c-size ,size);

				}
			}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken()); // 몇제곱 ? 
		int r= Integer.parseInt(st.nextToken()); // 탐색 행 
		int c= Integer.parseInt(st.nextToken()); // 탐색 열 
		
		int size= (int) Math.pow(2,N); // 2의 n 승 
		res=0; 
		find(r, c, size );
		
		System.out.println(res);
		
	}
}
