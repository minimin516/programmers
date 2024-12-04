import java.util.Arrays;

class Solution {    
    public int solution(int[][] scores) {
        int answer = 1;
        int size = scores.length;
        int[] rank = {scores[0][0], scores[0][1]};
        
       Arrays.sort(scores, (s1, s2) -> {
            if(s1[0] == s2[0])
                return s1[1] - s2[1];
            else
                return s2[0] - s1[0];
        });
        
        int max = scores[0][1];

        for(int i = 0; i < size; i++){
            if(scores[i][1] < max){
                if(scores[i][0] == rank[0] && scores[i][1] == rank[1])
                    return -1;
                else{
                    scores[i][0] = -1;
                    scores[i][1] = -1;
                }
            }
            else
                max = scores[i][1];
        }
        
       Arrays.sort(scores, (s1, s2) -> (s2[0] + s2[1]) - (s1[0] + s1[1]));

        for(int i = 0; i < size; i++){
            if(scores[i][0] + scores[i][1] > rank[0] + rank[1])
                answer++;
            else
                break;
        }
        
        return answer;
    }
}