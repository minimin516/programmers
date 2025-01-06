class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = 0;
        Result result = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, 0);
        
        answer = result.moveCnt;
        return answer;
    }
    
    private Result dfs(int[][] board, int ax, int ay, int bx, int by, int adepth, int bdepth) {
        boolean win = false;
        int winCnt = 25;
        int loseCnt = adepth + bdepth;
        //A
        if(adepth == bdepth && board[ax][ay] == 1) {
            for(int i=0; i<4; i++) {
                int x = ax + dy[i];
                int y = ay + dx[i];
                
                if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == 0) continue;
                
                board[ax][ay] = 0;
                
                Result result = dfs(board, x, y, bx, by, adepth + 1, bdepth);
                
                if(!result.win) win = true;
                
                if(result.win) 
                    loseCnt = Math.max(loseCnt, result.moveCnt);
                else 
                    winCnt = Math.min(winCnt, result.moveCnt);
                
                board[ax][ay] = 1;
            }
        }
        
        //B
        if(adepth > bdepth && board[bx][by] == 1) {
            for(int i=0; i<4; i++) {
                int x = bx + dy[i];
                int y = by + dx[i];
                
                if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == 0) continue;
                
                board[bx][by] = 0;
                
                Result result = dfs(board, ax, ay, x, y, adepth, bdepth + 1);
                
                if(!result.win) win = true;
                
                if(result.win) 
                    loseCnt = Math.max(loseCnt, result.moveCnt);
                else 
                    winCnt = Math.min(winCnt, result.moveCnt);
                
                board[bx][by] = 1;
            }
        }
        
        return new Result(win, win ? winCnt : loseCnt);
    }
    
    class Result {
        boolean win;
        int moveCnt;
        
        Result(boolean win, int moveCnt) {
            this.win = win;
            this.moveCnt = moveCnt;
        }
    }
    
}