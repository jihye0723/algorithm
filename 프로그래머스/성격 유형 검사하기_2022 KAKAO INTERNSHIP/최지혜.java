import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>(); 
        
        for(int i=0; i<survey.length; i++){
            String ques= survey[i]; //앞:비동의, 뒤:동의 
            int ans= choices[i]; //답변 
            
            if(ans==4) continue;
            int score=0; 
            char type=' '; 
            if (ans<4){
                score= 4-ans;
                type= ques.charAt(0); 
            }
            else {
                score = ans-4; 
                type= ques.charAt(1); 
            }
            //----------------------------
            // if(map.containsKey(type)){
            //         map.put(type, map.get(type)+score); 
            // }
            // else{
            //         map.put(type, score); 
            // }
            map.put(type, map.getOrDefault(type, 0)+score); 
            
        }
        StringBuilder sb= new StringBuilder();
        sb.append(map.getOrDefault('R',0) >= map.getOrDefault('T', 0) ? 'R' : 'T')
            .append(map.getOrDefault('C',0) >= map.getOrDefault('F', 0) ? 'C' : 'F')
            .append(map.getOrDefault('J',0) >= map.getOrDefault('M', 0) ? 'J' : 'M')
            .append(map.getOrDefault('A',0) >= map.getOrDefault('N', 0) ? 'A' : 'N'); 
        
        return sb.toString();
    }
}
