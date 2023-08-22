import java.util.*;
import java.io.*;

class Solution {
    static List<ArrayList<int[]>> list= new ArrayList<>(); 
    static Set<Integer> isGate= new HashSet<>();
    static Set<Integer> isSummit = new HashSet<>(); 
    
    static boolean visited[][]; //방문체크 
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        for(int i=0; i<=n; i++){
            list.add(new ArrayList<>()); 
        }
        for(int i=0; i<gates.length; i++) isGate.add(gates[i]);
        for(int i=0; i<summits.length; i++) isSummit.add(summits[i]);
        
        for(int i=0; i<paths.length; i++){
            int a= paths[i][0];
            int b= paths[i][1];
            int c= paths[i][2];
            
            //a가 출입구일 경우 || b가 산봉우리일 경우 (a > b 경로만 저장)
            if(isGate.contains(a) || isSummit.contains(b)) {
                list.get(a).add(new int[]{b,c}); 
                continue;
            }
            
            //b가 출입구일 경우 || a가 산봉우리일 경우 (b > a 경로만 저장)
            if(isGate.contains(b) || isSummit.contains(a)){
                list.get(b).add(new int[]{a,c});
                continue; 
            }
            
            list.get(a).add(new int[]{b,c});
            list.get(b).add(new int[]{a,c}); 
        }
       
        return dijkstra(n, gates, summits);  
    }
    
    private static int[] dijkstra(int n, int[] gates, int[] summits){
        Queue<Integer> que = new LinkedList<>(); 
        int[] min_intesity = new int[n+1]; 
        Arrays.fill(min_intesity, Integer.MAX_VALUE); 
        
        //출발지 큐에 넣기 
        for(int i=0; i<gates.length; i++){
            min_intesity[gates[i]] =0;
            que.add(gates[i]); 
        }
        
        while(!que.isEmpty()){
            int from =que.poll();
            
            for(int[] next : list.get(from)){
                int to = next[0];
                int weight = next[1]; //to 지점까지 가는 경로 
                
                //(from까지의 경로중 최댓값 vs from > to) 중 더 큰값 = intesity 
                //더 큰값과 min_intestiy[to] 비교해서 더 작은값 
                if(min_intesity[to] >  Math.max(min_intesity[from], weight)){
                    //to까지의 intesity는 from 까지의 intesity와 from>to 비교 
                    //최소 intesity 구하는거기 때문에 저장되있는 값보다 작을 경우 갱신 
                    min_intesity[to] = Math.max(min_intesity[from], weight);
                    que.add(to); 
                }
                
            }
        }
        
        int min_summit= Integer.MAX_VALUE;
        int min_value= Integer.MAX_VALUE; 
        
        Arrays.sort(summits);// 산봉우리 정렬 
        
        for(int summit : summits){
            if(min_intesity[summit] < min_value){
                min_value= min_intesity[summit];
                min_summit = summit; 
            }
        }
        
        return new int[]{min_summit, min_value}; 
        
    }
}
