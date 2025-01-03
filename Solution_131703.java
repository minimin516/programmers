class Solution {
    static int[][] map;
    static int[][] tgt;
    static int min = Integer.MAX_VALUE;
    static int n;
    static int m;

    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        map = beginning;
        tgt = target;
        dfs(0,0);
        return min==Integer.MAX_VALUE?-1:min;
    }

    static void dfs(int row, int count){
        if(row==n) {
            int result = count;
            for(int col=0 ; col<m ; col++){
                if(diffCnt(col) == n) result++;
                else if(diffCnt(col) != 0) return;
            }
            min = Math.min(min, result);
            return;
        }

        turnRow(row);
        dfs(row+1, count+1);
        turnRow(row);
        dfs(row+1,count);
    }
    
    static void turnRow(int row){
        for(int i=0;i<m;i++){
            map[row][i] = turn(map[row][i]);
        }
    }

    static int turn(int k){
        if(k==0) return 1;
        return 0;
    }

    static int diffCnt(int col){
        int count = 0;
        for(int i=0;i<n;i++){
            if(map[i][col] != tgt[i][col]) count++;
        }
        return count;
    }
}