import java.io.*;
import java.util.*;
public class Solution_BaekJoon_1717_집합의표현_골드4 {
	private static int[] parent;

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); 
		st= new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 0~n 의 자연수 
		int m = Integer.parseInt(st.nextToken()); // m개의 연산 
		parent = new int[n+1]; 
		makeSet(n); // 각 집합 만들기 
		while(m-- >0) {
			st = new StringTokenizer(br.readLine(), " "); // (0 a b) (1 a b)
			int type= Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 
			
			if(type==0) {
				unionSet(a,b);
			}
			
			else {
				int res = isUnion(a,b);
				if(res==1) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			}	
		}
		System.out.println(sb);
		
		
	}
	private static void makeSet(int n) {
		for(int i=0; i<=n; i++) {
			parent[i] = i;
		}
	}
	//해당 값의 부모 찾기 2 > 1 > 0 일때 결국 0 반환 하도록 
	private static int findParent(int x) {
		if(parent[x] == x) return x; 
		return parent[x]=findParent(parent[x]); 
		//return findParent(parent[x]) < 시간 초과 났었음 ㅣㅣ. 왜 ? 뭐가다른건데 
	}
	//a 와 b 중 더 작은 값으로 합치기 (1, 2 면 2 의 부모를 1로 ) 
	private static void unionSet(int a , int b) {
		a = findParent(a);
		b = findParent(b);
		if(a < b) parent[b]=a;  
		else  parent[a] = b;
	}
	// 둘의 부모가 같으면 1 
	private static int isUnion(int a, int b) {
		a= findParent(a);
		b= findParent(b);
		
		if(a==b) return 1;
		else return 0;
	}
}
