import java.util.*;

class Solution {
    public long solution(int distance, int[] rocks, int n) {
        long answer = 0;
        
        Arrays.sort(rocks);
        
        long left = 1;
        long right = distance;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            int delCnt = 0;
            long prev = 0;
            for (int i = 0; i < rocks.length; i++) {
                if ((rocks[i] - prev) < mid) {
                    delCnt++;
                } else {
                    prev = rocks[i];
                }
            }
            
            long end = distance;
            if ((end - prev) < mid) {
                delCnt++;
            }
            
            if (delCnt <= n) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
}