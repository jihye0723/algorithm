import java.util.*;
import java.io.*;
	
public class Main {
	static int N, M; 
	static boolean checked[]; 
	static int num[]; //주어진 숫자 
	static int[] permuted; //순열로 선택된 숫자 
	static int[] up_permuted; //오름 순열로 선택된 숫자 
	static int[] dupli_permuted; //중복 순열로 선택된 숫자 
	static int[] dupli_up_permuted; //중복 비내림 순열로 선택된 숫자 
	
	static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out)); 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		
		st= new StringTokenizer(br.readLine(), " ");
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken()); 
		
		num = new int[N]; 
		checked = new boolean[N]; 
		st= new StringTokenizer(br.readLine(), " "); 
		for(int i=0; i<N; i++) num[i] = Integer.parseInt(st.nextToken()); 
		Arrays.sort(num);
		
		
		permuted = new int[M]; 
		permute(0); 
		bw.flush();
		
		up_permuted = new int[M]; 
		up_permute(0, 0); 
		bw.flush();
	
		dupli_permuted = new int[M]; 
		dupli_permute(0); 
		bw.flush();
		
		dupli_up_permuted = new int[M]; 
		dupli_up_permute(0,0); 
		bw.flush();
		
		
	}
	
	//길이가 M인 수열 찾기 
	private static void permute(int cnt) throws Exception{
		//M개 모두 선택했을 경우
		if(cnt==M) {
			for(int i=0; i<M; i++) {
				bw.write(permuted[i]+" ");
			}
			bw.newLine();
			return; 
		}
		
		for(int i=0; i<N; i++) {
			if(!checked[i]) {
				checked[i]=true;
				permuted[cnt] = num[i]; 
				permute(cnt+1); 
				
				checked[i]=false; //다음 순열을 위해 
			}
		}
	}
	
	//오름차순 순열 구하기 
	private static void up_permute(int index, int cnt) throws Exception{
		if(cnt==M) {
			for(int i=0; i<M; i++) {
				bw.write(up_permuted[i]+" ");
			}
			bw.newLine();
			return; 
		}
		
		for(int i=index; i<N; i++) {
			if(!checked[i]) {
				checked[i]=true;
				up_permuted[cnt]= num[i];
				up_permute(i+1, cnt+1); 
				
				checked[i]=false; 
			}
		}
	}
	
	//중복 순열 구하기 :checked 배열 필요없음 
	//System.out.prinln으로 했더니 시간초과, bufferedWriter 사용해서 한번에 출력 (static) 
	private static void dupli_permute(int cnt) throws IOException {
		if(cnt==M) {
			for(int i=0; i<M; i++) {
				bw.write(Integer.toString(dupli_permuted[i])+" "); 
			}
			bw.newLine();
			return; 
		}
		
		for(int i=0; i<N; i++) {
			dupli_permuted[cnt] = num[i];
			dupli_permute(cnt+1); 
		}
	}
	
	//중복 오름차순(비내림차순) 
	private static void dupli_up_permute(int index, int cnt) throws Exception{
		if(cnt==M) {
			for(int i=0; i<M; i++) {
				bw.write(dupli_up_permuted[i]+" "); 
			}
			bw.newLine(); 
			return; 
		}
		for(int i=index; i<N; i++) {
			dupli_up_permuted[cnt] =num[i]; 
			dupli_up_permute(i, cnt+1); 
		}
	}
}//end of class 
