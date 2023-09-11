import java.util.*;
import java.io.*;

public class Main {
	static int power[]; //각 칸의 내구도 
	static int robot[]; //각 '컨베이너'벨트위의 로봇
	static int N, K; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		N= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken()); 
		
		power= new int[N*2]; 
		robot = new int[N]; 
		
		st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<power.length; i++) {
			power[i]= Integer.parseInt(st.nextToken()); 
		}
		
		start(); 
	}
	
	private static void start() {
		int time =0;
		
		while(!isEnd()) {
			Rotate();
			
			Move();
			Up(); 
///*			System.out.print("내구도 : ");
//			for(int i=0; i<N*2; i++) {
//				System.out.print(power[i]+" ");
//			}
//			System.out.println();
//			System.out.print("로봇 : ");
//			for(int i=0; i<N; i++) {
//				System.out.print(robot[i]+" ");
//			}
//			System.out.println();*/
			time++; 
		}
		System.out.println(time);
	}
	private static void Rotate() {
		int tmp = power[N*2 -1]; 
		
		//로봇 옮기기 
		for(int i=N-1; i>0; i--) {
			robot[i] = robot[i-1];
		}
		robot[0]=0; 
		robot[N-1]=0; 
		//벨트 옮기기 
		for(int i= N*2-1; i>0; i--) {
			power[i] = power[i-1];
		}
		
		power[0] = tmp; 
	}
	
	private static void Move() {
		//N-1번칸은 내리는 위치이기 때문에 무조건 로봇이 없음 
		for(int i = N-2; i >=0; i--) {
			//이동하려는 칸에 로봇이 없고, 내구도 1이상이라면
			if(robot[i]>0 && power[i+1] > 0 && robot[i+1] == 0) {
				power[i+1] --; 
				if(i+1 == N-1) robot[i+1] = 0;
				else robot[i+1] =1;
				robot[i]=0; 
			}
		}
	}
	
	private static void Up() {
		if(power[0] > 0) {
			power[0]--; 
			robot[0] ++; 
		}
	}
	//내구도가 0인 칸의 갯수가 K개 이상이라면 과정 종료 
	private static boolean isEnd() {
		int cnt =0; 
		for(int i=0; i<power.length; i++) {
			if(power[i]==0) cnt++; 
			if(cnt == K) return true; 
		}
		
		return false; 
	}
}//end of class 
