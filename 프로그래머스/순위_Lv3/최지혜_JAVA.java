import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] player = new int[n][n];
        
        for(int i=0; i<results.length; i++){
            int win = results[i][0]-1;
            int lose= results[i][1]-1;
            
            //이겼을 경우 : 1, 반대는 졌으니까 : -1
            player[win][lose] = 1; 
            player[lose][win] = -1; 
        }
        
        for(int a=0; a<n; a++){ //경유지 
            for(int b=0; b<n; b++){ //출발지
                for(int c=0; c<n; c++){ //도착지
                    if(player[b][a]== 1 && player[a][c]==1) player[b][c] =1; 
                    if(player[b][a]==-1 && player[a][c]==-1) player[b][c]=-1;
                }
            }
        }//플로이드 와샬
        
        int result=0; 
        //이중에 순위를 확실히 알려면, 채워진 값이 자기자신을 제외한 n-1개 여야함 
        for(int i=0; i<n; i++){
            int cnt=0; 
            for(int j=0; j<n; j++){
                if(player[i][j] !=0 ) cnt++; 
            }
            //순위를 알 수 있음 
            if(cnt == n-1) {
                result++; 
            }
        }
         
        return result; 
    }
}
