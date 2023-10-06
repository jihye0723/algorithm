//전체일을 더해서 , n만큼 빼기 
//전체일의 갯수만틈 나누기 (최대한 작은 값들로 꾸려져야함 ) 만약 7이면 2,2,2 (+1)
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder()); 
        for(int work : works) que.add(work); 
        
        while(n > 0){
            int big = que.poll(); 
            big--; n--; 
            
            if(big > 0) que.add(big); //0이면 넣을 필요 없음 (일 다함)
            if(que.isEmpty()) break; // 큐가 비면 끝내기 
        }
        
        while(!que.isEmpty()){
            answer += Math.pow(que.poll(), 2); 
        }
        
        return answer;
    }
}

