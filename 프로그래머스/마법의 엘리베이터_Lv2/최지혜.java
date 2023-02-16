class Solution {
    public int solution(int storey) {
        int answer=0;
        
        String str= storey+""; 
        
        int[] arr= new int[str.length()];
        for(int i=0; i<arr.length; i++){
            arr[i] = str.charAt(i)-'0'; 
        }
        
        for(int i=arr.length-1; i>=0; i--){
            //5보다 작을때 
            if(arr[i] < 5){
                answer+=arr[i]; 
            }
            //5보다 클때
            else if(arr[i]>5){
                answer += (10-arr[i]); 
                if(i==0) answer+=1; 
                else arr[i-1] += 1; 
            }
            //5일때
            else {
                answer += arr[i]; 
                if(i==0) {
                    break; 
                }
                if(arr[i-1] >= 5){
                    arr[i-1]+=1; 
                }
            }
        }
        
            
        return answer;
    }//end of main 
}
