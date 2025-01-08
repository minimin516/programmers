class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int pro[][] = new int[board.length+1][board[0].length+1];
        for(int i = 0; i< skill.length; ++i){
            int ca = skill[i][0];
            int starty = skill[i][1];
            int startx = skill[i][2];
            int endy = skill[i][3];
            int endx = skill[i][4];
            int power = skill[i][5];
            
            if(ca == 1){
                pro[starty][startx] -= power;
                pro[starty][endx+1] += power;
                pro[endy+1][startx] += power;
                pro[endy+1][endx+1] -= power;
            } else{
                pro[starty][startx] += power;
                pro[starty][endx+1] -= power;
                pro[endy+1][startx] -= power;
                pro[endy+1][endx+1] += power;
            }
        }
        for(int y = 1; y <pro.length; ++y){
            for(int x = 0; x<pro[0].length; ++x){
                pro[y][x] += pro[y-1][x];
            }
        }
        for(int x =1; x<pro[0].length; ++x){
            for(int y = 0; y<pro.length; ++y){
                pro[y][x] += pro[y][x-1];
            }
        }
        
        for(int i=0; i<board.length; ++i){
            for(int j=0; j<board[i].length; ++j){
                if(board[i][j] + pro[i][j] > 0) ++answer;
            }
        }
        
        return answer;
    }
}