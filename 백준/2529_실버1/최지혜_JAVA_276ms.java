package study;

import java.util.*;
import java.io.*;
/*
 * 모든 경우의 수 탐색하면서 완성되면 list 에 넣고, 
 * list 정렬해서 최소 최댓값  */
public class Solution_BaekJoon_2529 {
	private static boolean[] useNum = new boolean[10]; // 해당 숫자를 사용했는지 체크 
	private static List<String> list = new ArrayList<>(); 
	private static char[] arr;
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int k = sc.nextInt(); //부등호의 갯수 
		arr= new char[k];
		
		for(int i=0; i<k; i++) {
			arr[i] = sc.next().charAt(0); 
		}
		
		findNum(0, ""); 
		Collections.sort(list);
		System.out.println(list.get(list.size()-1)+"\n"+list.get(0));
	}//end of main 

	/*숫자 찾기 */
	private static void findNum(int index, String num ) {//arr_index : 몇번째 괄호, ans_index: 몇번째 수 
		//종료 조건 
		if(index > arr.length) {
			list.add(num); 
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(!useNum[i]) {
				if(index ==0 || isOk(Integer.parseInt(num.substring(num.length()-1)), i, arr[index-1]) ) {
					useNum[i] = true; 
					findNum(index+1, num+Integer.toString(i)); 
					useNum[i] = false; 
				}
					
			}
		}
		
	}//end of findNum
	
	//주어진 부등호, 숫자들이 가능한 조합인지 
	private static boolean isOk(int a, int b, char c) {
		if(c=='<') {
			return a<b; 
		}
		else 
			return a>b;
		
	}//end of isOk
	
}//end of class 

