import java.util.*; 
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int a_index =0; 
        for(int i=0; i<B.length; i++){
            //b가 이김 
            if(B[i] > A[a_index]){
                answer++; 
                a_index++; 
            }
        }
        
        return answer;
    }
}
