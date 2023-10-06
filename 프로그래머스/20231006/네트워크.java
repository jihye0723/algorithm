//bfs로 탐색 
class Solution {
    boolean[] select;  //네트워크에 포함되었는지 확인하기 위한 변수
    public int solution(int n, int[][] computers) {
        int answer = 0;
        select= new boolean[n]; 
        for(int i=0; i<n; i++){
            if(!select[i]) {
                answer++; 
                dfs(i,n, computers);
            }
        }
        return answer;
    }
    private void dfs(int i,int n,int[][] computers){
        select[i]= true; 
        for(int j=0; j<n; j++){
            //아직 네트워크에 포함 안됬고, 현재랑 연결 되어있으면 
            if(!select[j] && computers[i][j]==1) dfs(j,n,computers);
        }
    }
}
