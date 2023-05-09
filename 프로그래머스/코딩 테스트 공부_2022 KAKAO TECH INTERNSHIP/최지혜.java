import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        //달성해야 하는 알고력, 코딩력 찾기 
        int max_alp=0; int max_cop=0; 
        for(int i=0; i<problems.length; i++){
            max_alp=Math.max(max_alp, problems[i][0]);
            max_cop=Math.max(max_cop, problems[i][1]); 
        }
        
        if(alp >= max_alp && cop >= max_cop) return 0; 
        
        if(alp > max_alp) alp=max_alp; 
        else if(cop > max_cop) cop=max_cop;
        
        //dp[max_alp][max_cop] 값 찾으면 됨 
        int[][] dp = new int[max_alp+2][max_cop+2]; 
        
        for(int i=0; i<dp.length; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE); 
        }
        
        dp[alp][cop]=0;
        
        for(int i=alp; i<=max_alp; i++){
            for(int j=cop; j<=max_cop; j++){
                dp[i+1][j]= Math.min(dp[i][j]+1, dp[i+1][j]); 
                
                dp[i][j+1]= Math.min(dp[i][j]+1, dp[i][j+1]); 
                
                for(int[] pro : problems){
                    
                    //현재 알고력과 코딩력으로 풀 수 있는 문제가 있을때
                    if(i>=pro[0] && j>=pro[1]){
                        if(pro[2]+i >max_alp && pro[3]+j >max_cop){
                            dp[max_alp][max_cop] = Math.min(dp[max_alp][max_cop], dp[i][j]+pro[4]);
                        }
                        else if(pro[2]+i > max_alp){
                            dp[max_alp][j+pro[3]] = Math.min(dp[max_alp][j+pro[3]], dp[i][j]+pro[4]);
                        }
                        else if(pro[3]+j > max_cop) {
                            dp[i+pro[2]][max_cop] = Math.min(dp[i+pro[2]][max_cop], dp[i][j]+pro[4]);
                        }
                        else {
                            dp[i+pro[2]][j+pro[3]]= Math.min(dp[i+pro[2]][j+pro[3]], dp[i][j]+pro[4]);
                        }
                    }
                }
            }
        }
        
        
        
        
        return dp[max_alp][max_cop]; 
    }
}
