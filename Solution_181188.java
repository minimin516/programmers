import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (t1, t2) -> {
            if(t1[1] == t2[1]){
                return t1[0] - t2[0];
            }
            return t1[1] - t2[1];
        });
        
        int end = targets[0][1];
        answer++;
        
        for(int[] trgt : targets){
            if(trgt[0] >= end){
                end = trgt[1];
                answer++;
            }
        }
        
        return answer;
    }
}