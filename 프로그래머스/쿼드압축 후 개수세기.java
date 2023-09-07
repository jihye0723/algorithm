class Solution {
    static int[] answer;
    private boolean isSame(int[][] arr, int x, int y, int len, int val){
        for(int i=x; i<x+len; i++){
            for(int j=y; j<y+len; j++){
                if(arr[i][j] != val) return false;
            }
        }
        return true; 
    }
    private void quad(int[][] arr, int x, int y, int len){
        //만약에 영역 안의 수가 모두다 같으면, 
        if(isSame(arr, x, y, len, arr[x][y])){
            answer[arr[x][y]]++;
            return; 
        }
        
        len/=2; 
        quad(arr, x, y, len); 
        quad(arr, x+len, y, len);
        quad(arr, x, y+len, len);
        quad(arr, x+len, y+len, len); 
    }
    public int[] solution(int[][] arr) {
        answer = new int[2]; 
        //매개변수 : 시작(x,y), 한변의 길이 
        quad(arr, 0,0, arr.length); 
        return answer;
    }
}
