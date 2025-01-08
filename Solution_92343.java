import java.util.*;

class Solution {
    public static int maxSheep;
    public static int[] infos;
    public static boolean[][][] isVisiteds;
    public static ArrayList<Integer>[] connects;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        infos = info;

        connects = new ArrayList[info.length];
        for(int i=0;i<info.length;i++){
            connects[i] = new ArrayList<Integer>();
        }
        
        for(int i=0;i<edges.length;i++){
            int a = edges[i][0];
            int b = edges[i][1];
            connects[a].add(b);
            connects[b].add(a);
            
        }
        
        isVisiteds = new boolean[info.length][info.length+1][info.length+1];
        dfs(0, 0, 0);
        answer = maxSheep;
        return answer;
    }
    
    public static void dfs(int s, int w, int now){
        
        if(infos[now] == 0){
            s++;
        } else if(infos[now] == 1){
            w++;
        }
        
        
        if(w >= s) return;
        
        maxSheep = Math.max(maxSheep, s);
     

        for(int i=0;i<connects[now].size();i++){
            int next = connects[now].get(i);
            int temp = infos[now];
            if(!isVisiteds[next][s][w]){
                infos[now] = -1;
                isVisiteds[now][s][w] = true;
                dfs(s, w, next);
                infos[now] = temp;
                isVisiteds[now][s][w] = false;
            }
        }
        
    }
}