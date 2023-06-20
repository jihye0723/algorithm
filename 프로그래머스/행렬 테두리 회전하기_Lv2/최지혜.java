class Solution {
    static int[][] map; 
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        int num =1; 
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j]= num++; 
            }
        }
        int[] answer= new int[queries.length];
        for(int i=0; i<queries.length; i++){
            int sx= queries[i][0]-1;
            int sy= queries[i][1]-1;
            int ex= queries[i][2]-1;
            int ey= queries[i][3]-1; 
            
            int res= Rotate(sx, sy, ex, ey); 
            answer[i] = res; 
        }
        // for(int i=0; i<rows; i++){
        //     for(int j=0; j<columns; j++){
        //         System.out.print(map[i][j]+" ");
        //     }
        //     System.out.println(); 
        // }
        
        return answer;
    }
    
    //테두리 회전시키는 메소드 
    private static int Rotate(int sx, int sy, int ex, int ey){
        int min =Integer.MAX_VALUE; 
        int num = map[sx][sy]; 
        //사각형의 왼쪽변 위로 한칸씩 올리기 
        for(int i=sx; i<ex; i++){ // 1~3
            map[i][sy] = map[i+1][sy]; 
            min = Math.min(min, map[i][sy]); 
        }
        
        //사각형의 바닥 변 왼쪽으로 한칸씩 
        for(int i=sy; i<ey; i++){
            map[ex][i]= map[ex][i+1]; 
            min = Math.min(min, map[ex][i]); 

        }
        
        //사각형의 오른변 밑으로 한칸씩 내리기 
        for(int i=ex; i>sx; i--){
            map[i][ey] = map[i-1][ey]; 
            min = Math.min(min, map[i][ey]); 
        }
        
        //사각형의 위쪽 변 오른쪽으로 한칸씩 
        for(int i=ey; i>sy; i--){
            map[sx][i]= map[sx][i-1]; 
            min = Math.min(min, map[sx][i]); 
        }
        
        map[sx][sy+1]= num; 
        min = Math.min(min, num); 
        
        return min; 
    }
}
