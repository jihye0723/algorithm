package study;

import java.util.*;
import java.io.*;

public class Solution_BaekJoon_1141 {
	public static void main(String[] args) throws Exception{
		Set<String> set = new HashSet<String>(); 
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		
		int N= Integer.parseInt(br.readLine()); // 단어의 갯수 
		String[] words = new String[N];
		
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}
		
		// 길이순으로 정렬
		Arrays.sort(words, (o1,o2)->{
			return o2.length() - o1.length();
		});
		
		// SET 이기 때문에 중복된 문자열은 들어가지 X 
		for(int i=0; i<N; i++) {
			String word= words[i];
			
			if(set.size() ==0 ) {
				set.add(word);
				continue; 
			}
			// 해당 문자열을 set 에 넣을 수 있는지 여부 
			boolean flag= true;
			// 해당 문자열을 접두어로 가지는 문자열이 있을 경우, 
			for(String s : set) {
				if(s.indexOf(word) == 0) {
					flag= false;
					break; 
				}
			}
			
			if(flag) set.add(word); 
		}
	
		System.out.println(set.size());
		
	}//end of main 
}//end of class 

