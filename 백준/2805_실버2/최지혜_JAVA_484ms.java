import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken()); // 나무의 갯수 
		int M= Integer.parseInt(st.nextToken()); // 필요한 나무 길이 
		
		int tree[] = new int[N]; 
		
		long min=0; 
		long max=0; 
		
		st= new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<tree.length; i++) {
			tree[i] = Integer.parseInt(st.nextToken()); //나무의 높이 
			
			if (tree[i] > max ) max= tree[i]; 
		}
		
		while(max>=min) {	
			
			long mid = (max+min)/2;		
			long sum =0; 
			
			for(int t : tree) {
				if(t>mid) sum+= t-mid; 
			}

			//  너무 많이 잘랐음, 높이 높여도 됨 
			if(sum >= M ) {
				min = mid+1; 
			}
			else {
				max= mid-1; 
			}
		 
		} // end of while 
		
		System.out.println(max);
	}
}
