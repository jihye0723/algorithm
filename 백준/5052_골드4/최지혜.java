import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//		StringTokenizer st; 
			
		int tc= Integer.parseInt(br.readLine()); //테스트케이스의 갯수
		boolean flag; 
		while(tc-- >0) {
			flag= true; 
			int n= Integer.parseInt(br.readLine()); 
			String phone[] = new String[n];
			for(int i=0; i<n; i++) {
				phone[i] = br.readLine(); 
			}
			
			Arrays.sort(phone);
			

			for(int i=0; i<n-1; i++) {
					if(phone[i+1].startsWith(phone[i])) {
						flag= false; 
						break; 
					}
			}
			
			System.out.println(flag ? "YES" : "NO");
		}
	
	}
}//end of class 
