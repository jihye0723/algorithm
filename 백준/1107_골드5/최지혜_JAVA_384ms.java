package study;

import java.util.*;
import java.io.*;
/*
 * +/- 만으로 채널 바꾸기 vs 채널 눌러서 +/- 하기 
 * */
public class Solution_BaekJoon_1107 {
	static int min ; 
	static int N; 
	static boolean[] wrong= new boolean[10]; // 고장난 채널 = true 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		
		
		N= Integer.parseInt(br.readLine()); // 목표 채널 
		int M= Integer.parseInt(br.readLine()); // 고장난 버튼 갯수 

		// M>0 일 경우에만 입력 받기 
		// 고장난 번호는 wrong = true
		if(M > 0 ) {
			StringTokenizer st= new StringTokenizer(br.readLine(), " ");
			for(int i=0; i< M; i++) {
				wrong[Integer.parseInt(st.nextToken())] = true; 
			}
		}
		
		// 목표채널이 100일 경우 0 출력 후 종료 
		if(N == 100) {
			System.out.println(0);
			return; 
		}
		
		// +나 - 만으로 채널을 바꿀 때 
		int gap = Math.abs(N-100); 
		// +/- 눌렀을 때만의 이동 횟수를 min값으로 설정해둔다. (이후에 탐색하며 갱신 ) 
		min = gap; 
		
		getAllChannel(); 
		
		System.out.println(min);
		
	} //end of main  
	
	//가능한 숫자로 1~6자리의 채널 중 최솟값 갱신 
	private static void getAllChannel() {
		for(int ch=0; ch<=999999; ch++) {
			String str_ch = ""+ch; 
			// 고장난 숫자를 포함하고 있지 않을 경우, 
			if(isvalid(str_ch)) {
				// 해당 채널을 눌르기 위한 숫자 ( 채널 길이 ) + 목표 숫자까지의 +/- 횟수 
				int count = str_ch.length() + Math.abs(N-ch); 
				min = Math.min(min, count); 
			}
		} // end of ch for 
	}
	
	// ch : 채널이 고장난 숫자를 포함하고 있는지 
	private static boolean isvalid(String str_ch) {
		boolean valid = true; 
		//9999 0~4
		for(int i=0; i<str_ch.length(); i++) {
			// 고장난 채널사용
			if(wrong[str_ch.charAt(i)-'0']) {
				valid = false; 
				break; 
			}
		}
		return valid; 
	}
}
