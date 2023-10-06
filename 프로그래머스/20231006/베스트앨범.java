import java.util.*;
class Solution {
    class Music implements Comparable<Music>{
        int n;
        int cnt; 
        
        public Music(int n, int cnt){
            this.n=n;
            this.cnt=cnt; 
        }
        
        @Override
        public int compareTo(Music o){
            return o.cnt - this.cnt; //내림차순 정렬 
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer= new ArrayList<>(); 
        Map<String, Integer> map = new HashMap<>(); 
        for(int i=0; i<genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0) +plays[i]); 
        }
        
        ArrayList<String> genre = new ArrayList<>(); //장르 재생곡 많은 순대로 저장 
        //map에 저장되있던 장르들을 내림차순으로 저장 
        while(map.size()>0){ 
            int max= 0; 
            String max_key =""; //현재 map에 있는 것중에 최대 value 를 가지고 있는거 
            for(String key : map.keySet()){
                if(max<map.get(key)){
                    max = map.get(key); 
                    max_key = key; 
                }
            }
            genre.add(max_key); 
            map.remove(max_key); 
        }
        //장르 많았던 것부터 하나 빼기 
        for(int i=0; i<genre.size(); i++){
            String gen = genre.get(i);
            PriorityQueue<Music> que = new PriorityQueue<>(); 
            for(int j=0; j<genres.length; j++){
                if(genres[j].equals(gen)){
                    que.add(new Music(j, plays[j])); 
                }
            }
            //해당 장르의 것 빼기 
            answer.add(que.poll().n); //노래 하나는 무조건 있음 
            if(!que.isEmpty()) answer.add(que.poll().n); //한곡 더 있으면 넣기 
        }
       
        int[] ans = new int[answer.size()]; 
        for(int i=0; i<ans.length; i++){
            ans[i] = answer.get(i); 
        }
        
        return ans; 
    }
}
