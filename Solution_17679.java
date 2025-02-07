class Solution {
    static char[][] map;
    static boolean[][] check;
    
    public int solution(int m, int n, String[] board) {
        map = new char[m][n];
        check = new boolean[m][n];
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        int answer = 0;
        while (true) {
            findBlock();
            
            int cnt = removeBlock();
            if (cnt == 0) {
                break;
            }
            answer += cnt;
            moveDown();
        }
        return answer;
    }
    
    static void findBlock() {
        for (int i=0; i<map.length-1; i++) {
            for (int j=0; j<map[0].length-1; j++) {
                char ch = map[i][j];
                if (ch == 'o') continue;
                
                if (checkBlock(i, j, ch)) {
                    check[i][j] = true;
                    check[i+1][j] = true;
                    check[i][j+1] = true;
                    check[i+1][j+1] = true;
                }
            }
        }
    }
    
    static boolean checkBlock(int r, int c, char ch) {
        for (int i=r; i<r+2; i++) {
            for (int j=c; j<c+2; j++) {
                if (map[i][j] != ch) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    static int removeBlock() {
        int cnt = 0;
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (check[i][j]) {
                    map[i][j] = 'x';
                    check[i][j] = false;
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
    
    static void moveDown() {
        for (int j = 0; j < map[0].length; j++) {
            int i = map.length - 1;
            while (i >= 0) {
                while (i >= 0 && map[i][j] != 'x') i--;
                if (i < 0) break;
                
                int prev = i;
            
                while (i >= 0 && map[i][j] == 'x') i--;
                if (i < 0 || map[i][j] == 'o') break;
            
                map[prev][j] = map[i][j];
                map[i][j] = 'x';
                i = prev;
            }
            
            for (i = 0; i < map.length; i++) {
                if (map[i][j] != 'x' && map[i][j] != 'o') break;
                map[i][j] = 'o';
            }
        }
    }
}