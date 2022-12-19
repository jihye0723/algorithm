import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        // ex ) brown:10 , yellow:2 
        int[] answer = new int[2];
        
        int count = brown + yellow ; // 전체 격자의 갯수:12
        
        List<int[]> list= new LinkedList<>(); 
        // 곱해서 count 가 될 수 있는 쌍 구하기 
        for(int i=1; i<count/2; i++ ){
            if(count % i == 0 ){
                // i : 세로길이 
                int n = count/i ; // 가로길이  
                if(n < i) break; // 가로가 세로보다 짧아지면 그만 
                list.add(new int[]{n, i}); //{12,1} , {6,2}, {4,3}
            }
        }
        
        // 가로*2 + (세로-2)*2 = brown 이 되는거 찾아야한다.  
        for(int li=0; li<list.size(); li++){
            int[] get =  list.get(li); 
            int width = get[0]; //가로
            int length=  get[1];  // 세로 
            
            int isbrown = width*2 + (length-2)*2;
            if(isbrown == brown) {
                answer[0] = width ; 
                answer[1] = length; 
                break; 
            }
        }
        
        
        
        return answer;
        
    }
}
