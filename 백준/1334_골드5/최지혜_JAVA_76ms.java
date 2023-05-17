package study_17;

import java.io.*;

public class Solution_BaekJoon_1334 {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		
		String N = br.readLine();
		String res=N; //결과 
		//한자리 수일 때는 그냥 하나 더해주기, 만약 9라면 10이 아니라, 11로 
		if(N.length()==1) {
			int nInt=Integer.parseInt(N); 
			if(nInt==9) res=(nInt+2)+"";
			else res=(nInt+1) +""; 
		}
		//한자리 수보다 더 많을 때 > 홀수 짝수 자리로 나누어서 생각하기 
		else {
			int mid_index=N.length()/2;// 가운데 수 인덱스 
			int mid=0; //가운데 수 
			String front="";//가운데 수를 기준으로 앞 쪽 문자열 
			String back="" ;//앞쪽을 그대로 바꾼 문자열 
			//N의 자릿수가 홀수 일 경우 
			if(N.length()%2==1) {
				mid = N.charAt(mid_index)-'0';
				front = N.substring(0, mid_index);
				back = swap(front); 
				res = front+mid+back; //팰린드롬으로 바꾼 수 
				
				//바꾼 수가 더 작거나 같음 > 더 큰 팰린드롬 수로 바꿔야 함 
				if(res.compareTo(N) <= 0) {
					mid+=1;
					if(mid == 10) {
						int now_front = front.length(); 
						front = increaseFront(front); 
						res= front+0+swap(front.substring(0, now_front)); 
					}
					else {
						res=front+mid+back; 
					}
				}
				
				
			}
			//N의 자릿수가 짝수일 경우 
			else {
				front = N.substring(0,mid_index); 
				back = swap(front); 
				
				res=front+back; 
				
				//바꾼 수가 더 작거나 같음 > 더 큰 팰린드롬 수로 바꿔야 함 
				if(res.compareTo(N) <= 0) {
					int now_front = front.length(); 
					front = increaseFront(front); 
					res = front+swap(front.substring(0,now_front)); 
				}
					
			}
		}
		
		System.out.println(res);
	}//end of main
	//거울 방향을 뒤집기 
	private static String swap(String front) {
		String back = ""; 
		for(int i=front.length()-1; i>=0; i--) {
			back+=front.charAt(i); 
		}
		return back; 
	}
	//더 큰 팰린드롬 수를 찾기 위해 현재 front 값에 1 더하기 
	private static String increaseFront(String front) {
		StringBuilder sb = new StringBuilder(front); 
		for(int i=front.length()-1; i>=0; i--) {
			int now = sb.charAt(i)-'0';
			now+=1; 
			if(now==10) {
				sb.setCharAt(i, '0'); //현재 위치는 0으로 바꾸고 다음턴 
				if(i==0) sb.insert(0, '1'); 
			}else {
				sb.setCharAt(i, (char)(now+'0'));
				break;
			}
		}
		return sb.toString(); 
	}
}//end of class 
