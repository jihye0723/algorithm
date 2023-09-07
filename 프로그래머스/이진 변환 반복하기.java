class Solution {
    static int delete=0; 
    static int change=0; 
    public int[] solution(String s) {
        int[] answer = new int[2];
        while(!s.equals("1")){
            change++; 
            s= deleteZero(s); //모든 0제거하기 
            s= changeBin(s.length()); //s의 길이를 2진법으로 변환 
        }
        answer[0]= change; answer[1]= delete; 
        return answer;
    }
    public String deleteZero(String s){
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '0') delete++; 
        }
        
        s= s.replace("0", ""); 
        return s; 
    }
    
    public String changeBin(int num){
        //몫이 1이면 종료 , 
        String change ="";
        while(num!=1){
            change = num%2+change; 
            num/=2;
        }           
        change = num +change;
        return change; 
    }
}
