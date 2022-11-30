import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer =0;
        int[] counts = new int[10000001]; 
        for(int i=0; i<tangerine.length; i++){
            counts[tangerine[i]]++;
        }
         Integer[] count = Arrays.stream(counts).boxed().toArray(Integer[] :: new);
 
        Arrays.sort(count, Collections.reverseOrder());// [2,2,2,1,1]
            
        
        int index=0;
        while(k > 0){
            k-=count[index];
            answer++;
            index++;
        }
        return answer;
       
    } 
}
