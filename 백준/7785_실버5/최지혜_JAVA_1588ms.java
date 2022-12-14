import java.io.*;
import java.util.*;

public class Solution_BaekJoon_7785 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st ;
		Set<String> set = new HashSet<>(); 
		
		int n = Integer.parseInt(br.readLine()); //출입기록 갯수 
		
		while(n-- >0) {
			String s = br.readLine(); 
			st= new StringTokenizer(s, " "); 
			
			String name = st.nextToken();
			String command = st.nextToken(); 
			
			if(command.equals("enter")) {
				set.add(name); 
			}
			
			else {
				set.remove(name); 
			}
			
		} // end of while
		
	
		ArrayList<String> list= new ArrayList<>(); 
		Iterator<String> iter = set.iterator();
	
		while(iter.hasNext()) {
			list.add(iter.next()); 
		}
		Collections.sort(list);
	
		
		for(int i=list.size()-1; i>=0; i--) {
			System.out.println(list.get(i));
		}
		
	}
}
