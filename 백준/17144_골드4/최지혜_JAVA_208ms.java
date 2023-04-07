import java.util.*;
import java.io.*;

public class Main {
	private static int R,C; 
	private static int[][] room; 
	private static int[] air; 
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		
		R= Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken()); 
		int T= Integer.parseInt(st.nextToken()); 
		
		room= new int[R][C]; 
		air= new int[2]; 
		int index=0; 
		for(int i=0; i<R; i++) {
			st= new StringTokenizer(br.readLine(), " "); 
			for(int j=0; j<C; j++) {
				room[i][j]= Integer.parseInt(st.nextToken()); 
				if(room[i][j]==-1) {
					air[index++]=i;
				}
			}
		}
		simulate(T); 
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(room[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(getSum());
	
	}//end of main
	
	private static int dx[]= {0,-1,0,1};
	private static int dy[]= {1,0,-1,0};
	
	//1초동안 일어나는 일 , T초후의 상태 리턴 
	private static void simulate(int T) {
		while(T-- >0) {
			//1.미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서  일어난다. 
			spread(); 
			//2.공기청정기가 작동한다. 
			work(); 
		}//T초동안 반복 
	}//end of simulate 

	/*미세먼지 확산, 미세먼지가 있는 모든칸에서 동시에  */
	private static void spread() {
		int copy[][]= new int[R][C];
		//기존 미세먼지 값 기억하고 있어야 함 
		for(int i=0; i<R; i++) {
			copy[i]= room[i].clone();
		}
		
		//한칸씩 미세먼지 퍼뜨리기 시작 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				//미세먼지가 있을 경우 
				if(copy[i][j]>0) {
					int go= copy[i][j]/5; //확산되는 양 
					//사방으로 퍼트리기 시작 
					for(int dir=0; dir<4; dir++) {
						int ni = i+dx[dir];
						int nj = j+dy[dir]; 
						//경계밖으로 나갔거나 공기청정기가 있을 경우
						if(isOut(ni,nj) || copy[ni][nj]==-1) continue; 
						//먼지가 퍼질 수 있는 곳이라면, 
						room[ni][nj] += go; //퍼지는 곳엔 늘어나고 
						room[i][j] -= go; //현재 위치는 줄어들고 
					}
				}
			}
		}//end for	
	}
	
	/*공기청정기 작동, 위쪽에 있는건 반시계 방향 아래쪽은 시계방향 */
	private static void work() {
		//위쪽 공기청정기 
		int fx= air[0];
		start(fx, 0, 1); 
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(room[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		//두번째
		int sx=air[1];
		start(sx,0,-1); 
	
	}
	
	/*공기 청정기 작동 시작 */
	private static void start(int x, int y, int d) {
		int dir=0;
		int startx=x; int starty=y; //공기청정기 위치 
		
		int now=0; 
		int next; 
		while(true) {
			int nx =x+dx[dir];
			int ny =y+dy[dir]; 
			
			//다음께 시작하는거면 
			if(nx==startx && ny==starty) {
				break; 
			}
			
			//경계 만나면 , 방향 바꾸고 다시 
			if(isOut(nx,ny)) {
				dir+=d;
				if(dir<0) dir=3; 
				continue; 
			}
			
			next= room[nx][ny]; 
			room[nx][ny] =now; 
			
			now=next; 
			x=nx; y=ny; 
			
		}
		
		
	}
	
	/*남아있는 미세먼지의 양 출력*/
	private static int getSum() {
		int sum=0; 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(room[i][j] < 1) continue; 
				sum +=room[i][j]; 
			}
		}
		return sum; 
	}
	//x,y가 경계밖으로 나가는 지확인 
	private static boolean isOut(int x, int y) {
		return x<0 || x>=R || y<0 || y>=C; 
	}
}//end of class
