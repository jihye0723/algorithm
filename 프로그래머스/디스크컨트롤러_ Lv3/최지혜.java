import java.util.*;

class Solution {
    class Work implements Comparable<Work>{
        int in_time; // 들어오는 시간 
        int work_time; // 소요 시간 
                
        public Work(int in_time, int work_time){
            this.in_time = in_time;
            this.work_time = work_time;
        }
        
        // 소요시간이 짧은 순서대로 넣기 
        @Override
        public int compareTo(Work o){
            return this.work_time - o.work_time;
        }        
        
    }
    
    // ------ main 구현 ------ 
    public int solution(int[][] jobs) {
        // 요청 시간 오름차순 정렬 
        Arrays.sort(jobs, (j1, j2)->{
            if(j1[0]==j2[0]){
                return j1[1]- j2[1]; 
            }
            return j1[0] - j2[0]; 
        });
        
        PriorityQueue<Work> que = new PriorityQueue<>();
        
        // 제일 앞에있는거 부터 큐에 삽입 
        que.add(new Work(jobs[0][0], jobs[0][1])); 
        
        int sum = 0 ; // 요청~종료시간의 합 
        
        int index =1; // 몇번째 작업 할 차례 ? 
        int end_time = jobs[0][0] ; 
        
        while(!que.isEmpty()){
            Work now = que.poll();
            
            end_time += now.work_time; // 현재 하고 있는 일이 끝나는 시간
            sum += (end_time - now.in_time); 
            
            //현재 일이 끝나기 전에, 시작하는 일이 있으면 que 에 넣기 
            while(index < jobs.length && jobs[index][0] <= end_time){
                que.add(new Work(jobs[index][0], jobs[index][1]));
                index++;    
            }
            
            // 아직 안한 작업이 있는데 큐가 비어있을 때  
            if(index < jobs.length && que.isEmpty()){
                que.add(new Work(jobs[index][0], jobs[index][1]));
                end_time = jobs[index][0]; 
                index++;
            }
            
        }
        
        return sum / jobs.length; 
    }
}
