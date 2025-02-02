import java.util.*;

class Solution {
    
    static int answer; 
    static boolean[] visited;

    
    public int solution(String begin, String target, String[] words) {
        
        visited = new boolean[words.length];
        
        dfs(begin, target, words, 0);
        
        return answer;
    }
    
    public void dfs(String begin, String target, String[] words, int count) {
        
        if (begin.equals(target)) {
            answer = count;
            return;
        }
        
        for (int i = 0; i< words.length; i++) {
            if (visited[i] == true) continue;
            
            String word = words[i];
            int notEqual = 0;

            for (int c = 0; c<begin.length(); c++) {
                if (begin.charAt(c) != word.charAt(c)) {
                    notEqual++;
                }      
            }
            if (notEqual == 1) {
                visited[i] = true;
                dfs(word, target, words, count+1);
                visited[i] = false;
            }
        }
    }
}