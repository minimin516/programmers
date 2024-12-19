import java.util.*;
class Solution {
    public int[] solution(String[] maps) {
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        Queue<int[]> que = new LinkedList<>();
        List<Integer> list = new ArrayList();
        
        for(int i = 0; i<maps.length; i++){
            for(int j = 0; j<maps[i].length(); j++){
                if(visit[i][j] || maps[i].charAt(j) == 'X') continue;
                
                visit[i][j] = true;
                que.add(new int[] {i, j});
                
                int sum = Integer.parseInt(maps[i].substring(j,j+1));
                
                while(!que.isEmpty()){
                    int len = que.size();
                    for(int q = 0; q<len; q++){
                        int[] temp = que.poll();
                        for(int k = 0; k<4; k++){
                            int x = temp[0] + dx[k];
                            int y = temp[1] + dy[k];
                            
                            if(x >= 0 && x < maps.length && y >= 0 && y < maps[0].length() 
                               && !visit[x][y] && maps[x].charAt(y) != 'X'){
                                visit[x][y] = true;
                                que.add(new int[] {x, y});
                                sum += Integer.parseInt(maps[x].substring(y,y+1));
                            }
                        }
                    }
                }
                
                list.add(sum);
            }
        }
        
        if(list.size() == 0){
            int[] result = {-1};
            return result;
        }
        
        int[] answer = new int[list.size()];
        for ( int i = 0; i<list.size(); i++ ) {
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}