import java.util.*;

class Solution {
    StringTokenizer st; 
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> ans = new ArrayList<>(); 
        Map<String, Integer> map = new HashMap<>(); 
        
        for(int i=0; i<terms.length; i++){
            st = new StringTokenizer(terms[i], " "); 
            map.put(st.nextToken(), Integer.parseInt(st.nextToken())); 
        }
        
        int tyear= Integer.parseInt(today.substring(0,4)); 
        int tmonth = Integer.parseInt(today.substring(5,7));
        int tday = Integer.parseInt(today.substring(8,10)); 
        
        int today_num = (tyear*12*28) + (tmonth*28) + tday; 
        for(int i=0; i<privacies.length; i++){
            st = new StringTokenizer(privacies[i], " "); 
            String date = st.nextToken(); 
            int time = map.get(st.nextToken()); //유효기간 몇달인지 
            System.out.println(date); 
            int year = Integer.parseInt(date.substring(0,4)); 
            int month = Integer.parseInt(date.substring(5,7));
            int day = Integer.parseInt(date.substring(8,10)); 
             System.out.println(year +" "+ month+" "+day); 

            int num = (year*12*28) + (month *28) + day ; 
            num += (time * 28); 
            System.out.println(today_num +" "+ num); 
            //오늘 > 유효기간 
            if(today_num >= num) ans.add(i+1); 
           
            
        }
        int[] answer = new int[ans.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = ans.get(i); 
        }
        return answer;
    }

}
