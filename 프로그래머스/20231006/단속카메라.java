import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2)->{
            //도착지점이 같으면 출발지점 오름차순 
            if(o1[1] == o2[1]) return o1[0] - o2[0]; 
            return o1[1]-o2[1]; 
        });
            
        answer++; 
        int camera= routes[0][1]; //첫번째 지점의 도착지에 카메라 설치 
        for(int i=1; i<routes.length; i++){
            if(routes[i][0] > camera){
                answer++;
                camera = routes[i][1]; 
            }
        }
        return answer;
    }
}
