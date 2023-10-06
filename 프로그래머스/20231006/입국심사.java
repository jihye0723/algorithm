import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left= 0; 
        Arrays.sort(times); 
        long right = (long)times[times.length-1] * n; //최악의 경우 걸리는 최대 시간 
        System.out.println(left+" "+right); 
        while(left<=right){
            long mid = (left+right)/2; 
            
            long person=0; 
            //현재시간으로 입국심사 할 수 있는 사람의 최대 수 
            for(int time : times){
                person += mid/time; 
            }
            //더 줄여볼 수 있다 
            if(person >= n) {
                right = mid-1; 
                answer = mid; //일단 가능한 경우다 더 줄일 수도 있음 ! 
            } 
            else left = mid+1; 
        }
        return answer;
    }
}
