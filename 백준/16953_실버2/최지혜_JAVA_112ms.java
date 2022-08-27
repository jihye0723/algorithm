import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int a= sc.nextInt();
		int b= sc.nextInt();
		
		int res = getCount(a, b, 0);
		
		System.out.println(res);
		
		
	}
	
	//몇번인지 카운트 세기 
	private static int getCount(int a, int b, int count) {
		if(b == a) {
			return (count+1);
		}
		if(a>b) return -1;
		//제일 뒤의 수가 1이면, 1빼주기 
		if(b%10 == 1 ) {
			return getCount(a,b/10,count+1);
		}
		//2의 배수이면 2로 나누기 
		else if(b%2==0){
			return getCount(a,b/2,count+1);
		}
	
		return -1; 
	}
}
