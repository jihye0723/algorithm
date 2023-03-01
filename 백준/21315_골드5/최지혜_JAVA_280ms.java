package study_17;

import java.util.*;
/*
 * K는 N의 값에 따라 최댓값이 달라지지만, 
 * N의 최댓값이 1000이므로, 최대 9까지밖에 안된다. 
 * */
public class Solution_BaekJoon_21315 {
	private static List<Integer> card;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		List<Integer> result=  new LinkedList<>(); // 섞였을 때 결과  
		
		int N= sc.nextInt(); // 카드의 갯수 
		for(int i=0; i<N; i++) {
			result.add(sc.nextInt()); 
		}
//		System.out.println(result.toString());
		//가능한 k 찾기 
		int i=1; 
		while(Math.pow(2, i) < N) {
			i++;
		}
		

		//가능한 k 순열 경우의 수 해보기 
		for(int k1=1; k1<i; k1++) {
			for(int k2=1; k2<i; k2++) {
				card= new LinkedList<>(); 
				for(int c = 1; c<=N; c++) card.add(c); 
				// 1 2 3 4 5
				mixcard(k1); 
//				System.out.println(card.toString());
				mixcard(k2); 
//				System.out.println(card.toString());

				
				if(Arrays.equals(card.toArray(), result.toArray())) {
					System.out.println(k1 +" "+k2);
					break; 
				}
			}
		}
	}//end of main	
	
	// 주어진 k로 카드 섞기 
	private static void mixcard(int k){
		List<Integer> mix= new LinkedList<>(); //다 섞였을때의 카드 
		List<Integer> leftList = card; // 섞일 카드 
		
		while(k >= 0) {
			int cnt= (int)(Math.pow(2, k--)); 
			int left_size = leftList.size(); 
			mix.addAll(0,leftList.subList(0, left_size-cnt));
			leftList = leftList.subList(left_size-cnt, left_size);
//			card= leftList;
//			k--; 
		}
		mix.addAll(0, leftList); 
		card = mix;
	}
}//end of class 
