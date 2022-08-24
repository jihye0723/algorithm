import java.util.*;
import java.io.*;
/**
 * 서류등수를 기준으로 오름차순 정렬 
 * 서류등수가 1등인 사람은 무조건 뽑히는거고 , 
 * 이후부터는 직전 합격인 사람보다, 면접 등수가 높은 사람들 뽑히게  */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		
		int tc= Integer.parseInt(br.readLine()); 
		// 테스트 케이스 만큼 반복 
		for(int t=0; t<tc; t++) {
			int n = Integer.parseInt(br.readLine()); // 몇 명 ? 
			
			int grade[][] = new int[n][2]; // 서류등수 , 면접등수 저장 
			for(int i=0; i<n; i++) {
				String s = br.readLine();
				StringTokenizer st= new StringTokenizer(s, " ");
				grade[i][0] = Integer.parseInt(st.nextToken()); // 서류등수
				grade[i][1] = Integer.parseInt(st.nextToken()); // 면접등수 
			}
			
			Arrays.sort(grade, (s1, s2) -> {
				return s1[0] - s2[0];
			});
			
			int count=0;
			int score = grade[0][1];
			count++;
			
			for(int i=1; i<n; i++) {
				
				if(grade[i][1] < score) {
					count++;
					score = grade[i][1];
				}
			}
			sb.append(count).append("\n");
		}
		
		System.out.println(sb);
		
	}
}
