import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2]; 
        
        int none_cnt=0; //지워진 숫자 
        int win_cnt=0; //당첨된 숫자 
        for(int num : lottos){
            if (num==0) none_cnt++; 
            
            for(int win : win_nums){
                if(num == win) win_cnt++; 
            }
        }
        
        answer[0] = getGrade(none_cnt+win_cnt);
        answer[1] = getGrade(win_cnt); 
      
        return answer;
    }
    static int getGrade(int n){
         switch(n){
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }
}
