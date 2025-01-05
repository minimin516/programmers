import java.util.*;
class Solution {
    static boolean visited[]; 
    static List<Integer> edges[];
    static int cnt = 0;

    public int solution(int n, int[][] lighthouse) {

        visited = new boolean[n + 1];
        edges = new ArrayList[n + 1];
        for(int i = 1; i<=n; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int info[] : lighthouse){
            int a = info[0]; 
            int b = info[1];
            edges[a].add(b);
            edges[b].add(a);
        }
        visited[1] = true;
        dfs(1);

        return cnt;
    }
    public boolean dfs(int idx){

        boolean need = false;
        boolean flag = false;
        for(int nextIdx : edges[idx]){
            if(!visited[nextIdx]){
                flag = true;
                visited[nextIdx] = true;
                need = need | dfs(nextIdx);    
            }
        }
        if(!flag) return true;
        
        if(need){
            cnt++;
            return false;
        }else return true; 
    }
}
