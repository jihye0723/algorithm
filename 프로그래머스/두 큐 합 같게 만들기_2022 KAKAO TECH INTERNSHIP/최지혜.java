import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
       
        long sum1=0; long total_sum=0; 
        
        for(int i=0; i<queue1.length; i++){
            que1.add(queue1[i]); 
            sum1 += queue1[i]; 
            que2.add(queue2[i]); 
            total_sum+= queue1[i]; 
            total_sum+= queue2[i];
        }
        
        if(total_sum%2 == 1) return -1; //총합이 홀수면 -1리턴 
        
        total_sum /=2; //총합의 절반 = sum1이 만들어져야 하는 값 
        int limit = (que1.size() + que2.size())*2; 
        int cnt=0; //몇번 움직였는지 
        while(sum1 != total_sum){
            if(cnt > limit) return -1; 
            
            //que1에서 que2로 하나 보냄 
            if(sum1 > total_sum){
                int go =que1.poll(); 
                que2.add(go);
                sum1-= go;
            }
            //que2에서 que1로 하나 보냄 
            else{
                int go =que2.poll();
                que1.add(go);
                sum1+=go; 
            }
            cnt++; 
        }
        return cnt; 
     
    }//end of solution 
}
