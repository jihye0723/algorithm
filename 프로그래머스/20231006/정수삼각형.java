class Solution {
    public int solution(int[][] triangle) {
        int answer = Integer.MIN_VALUE;
        int size = triangle.length; 
        int[][] dp = new int[size][size]; 
        dp[0][0] = triangle[0][0]; 
        
        for(int i=1; i<triangle.length; i++){
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            for(int j=1; j<triangle[i].length; j++){
                dp[i][j] = Math.max(dp[i-1][j-1] , dp[i-1][j]) + triangle[i][j]; 
            }
        }
        
        for(int j=0; j<triangle[size-1].length; j++){
            answer= Math.max(answer, dp[size-1][j]); 
        }
        return answer;
    }
}
