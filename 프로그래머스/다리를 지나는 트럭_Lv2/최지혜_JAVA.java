import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0; // 총 소요시간
        Queue<Integer> que = new LinkedList<>();  // 다리 위에 있는 트럭
        int total_truck=0; //다리 위에 올라가있는 트럭의 총 무게 
       
        for(int i=0; i<truck_weights.length; i++){
            int truck = truck_weights[i]; // 이번에 올라갈 트럭 
            
            while(true){
                // 큐가 비어있다면, 다리에 올리고 무게, 시간 증가 
                if(que.isEmpty()){
                    que.add(truck);
                    total_truck +=truck; 
                    answer ++; 
                    break; 
                }
                // 큐가 비어있지 않을 때 
               else {
                   // 다리가 이미 꽉 차있을 때, 
                   if(que.size() == bridge_length){
                       total_truck -= que.poll(); // 다리의 젤 앞에 있는거 지나가게 ( 시간은 x )
                   }
                   // 다리에 올라갈 자리가 있을 때 
                   else {
                       // 올라가도 되는 무게일 때 
                       if(total_truck+truck <= weight){
                           que.add(truck);
                           total_truck+=truck;
                           answer++;
                           break; 
                       }
                       // 올라가면 안되는 무게일 떄 (제한무게 초과)
                       else{
                           que.add(0); //0을 넣어서 현재 있는 트럭이 앞으로 가게 
                           answer++;
                           //break; 
                       }
                   }
               }
                
            }
        }//end of for 
        
        //마지막 트럭이 방금 올라갔기 떄문에 , 다리를 건너기 위해 다리길이 만큼 소요됨  
        answer+=bridge_length; 
    
        
        
        
        return answer;
    }
}
