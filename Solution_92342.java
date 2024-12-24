class Solution {

    static int[] points, answer;
    static int MAX_SCORE = Integer.MIN_VALUE;
    
    public int[] solution(int n, int[] info) {
        answer = new int[] { -1 };
        
        points = new int[11];
        
        dfs(info, 1, n);
        
        return answer;
    }
    
    static void dfs(int[] info, int arrows, int n) {
        
        if (arrows == n + 1) {
            int apeach_score = 0;
            int lion_score = 0;     
            for (int i = 0; i < 11; i++) {
                
                if (info[i] == 0 && points[i] == 0) continue;
                
                if (info[i] < points[i]) lion_score += 10 - i;
                
                else apeach_score += 10 - i;
            }
            
            if (lion_score > apeach_score) {
                if (lion_score - apeach_score >= MAX_SCORE) {
                    MAX_SCORE = lion_score - apeach_score;
                    answer = points.clone();
                }
            }
            return;
        }
        
        for (int i = 0; i <= 10 && points[i] <= info[i]; i++) {
            points[i]++;
            dfs(info, arrows + 1, n);
            points[i]--;
        }
        
    }
    
}