class Solution {
    boolean[][] isWater; 
    int[][] dp; 
    //(n,m위치 찾으면 됨 )
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        isWater = new boolean[n][m]; 
        dp = new int[n][m];
        for(int i=0; i<puddles.length; i++){
            int x= puddles[i][1]-1;
            int y= puddles[i][0]-1; 
            //바뀌어서 주어짐 
            
            isWater[x][y]= true; //물이 있음
        }
        dp[0][0]=1; 
        //위 가로줄부터채우기
        for(int i=1; i<m; i++){
            //물이 있는 곳이라면, 
            if(isWater[0][i]) break;
            
            dp[0][i] = dp[0][i-1]; 
        }
        //왼쪽 세로줄 채우기 
        for(int i=1; i<n; i++){
            if(isWater[i][0]) break; 
            
            dp[i][0] = dp[i-1][0]; 
        }
        
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(isWater[i][j]) continue; 
                
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000007; 
            }
        }
        return dp[n-1][m-1];
    }
}
