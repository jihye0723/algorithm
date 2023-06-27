import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> map = new HashMap<>(); //이름, 배열번호 
        for(int i=0; i<enroll.length; i++){
            map.put(enroll[i], i); 
        }
        int[] answer = new int[enroll.length]; 
        for(int i=0; i<seller.length; i++){
            String name= seller[i]; 
            int price = amount[i] * 100; //name이 얻은 수익 
            while(price > 0){
                
                //name의 추천인 
                String reco_name = referral[map.get(name)]; 
                //추천인에게 넘어갈 금액 
                int up_price = (int)(price * 0.1); 
                if(up_price < 1) up_price =0; 
                
                answer[map.get(name)] += (price-up_price);
                if(reco_name.equals("-")) break; 
                
                name = reco_name; 
                price = up_price; 
 
            }
        }
         
        
        
        return answer;
    }
  
}
