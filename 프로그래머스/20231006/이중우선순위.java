import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2]; 
        LinkedList<Integer> que = new LinkedList<>(); 
        
        StringTokenizer st; 
        for(int i=0; i<operations.length; i++){
            String cmd = operations[i]; //명령 
            st = new StringTokenizer(cmd, " ");
            String a = st.nextToken(); 
            int num = Integer.parseInt(st.nextToken()); 
            //숫자 넣기 
            if(a.equals("I")){
                que.add(num); 
                Collections.sort(que); 
            }
            else{
                if(!que.isEmpty()){
                    //최댓값 : 가장 뒤에 있는 값 
                    if(num == 1) que.pollLast(); 
                    else que.poll(); 
                }
            }
        }
        if(que.isEmpty()) answer[0] = answer[1]= 0; 
        else {
            answer[0] = que.pollLast(); 
            answer[1] = que.poll();
        }
        return answer;
    }
}
