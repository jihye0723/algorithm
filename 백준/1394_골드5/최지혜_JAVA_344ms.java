package study_17;

import java.util.*;

/*
 * 재귀로 했더니 메모리초과 
 * 반복문으로 변경 -,-*/
public class Solution_BaekJoon_1394 {
	private static Map<Character, Integer> map; 
	private static String word; 
//	private static int findKey(String key) {
//		if(key.length()==1) {
//			return map.get(key.charAt(0)); 
//		}
//		
//		return (findKey(key.substring(0, key.length()-1))*word.length() 
//				+ map.get(key.charAt(key.length()-1)))%900528; 
//	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		map = new HashMap<>();
		
		word = sc.next();
		String key = sc.next(); 
		
		for(int i=0; i<word.length(); i++) {
			map.put(word.charAt(i), i+1); 
		}
		
		int ans=0; 
		for(int i=0; i<key.length(); i++) {
			char now =key.charAt(i);
			ans=(ans*word.length() + map.get(now))%900528; 
		} 
		
		System.out.println(ans);
	}//end of main
}//end of class 
