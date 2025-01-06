import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int alpMax = 0;
        int copMax = 0;
        
        for (int[] nums : problems) {
            alpMax = Math.max(nums[0], alpMax);
            copMax = Math.max(nums[1], copMax);
        }

        alpMax = Math.max(alpMax,alp);
        copMax = Math.max(copMax,cop);

        if(alp >= alpMax && cop >= copMax) return 0;
        
        int[][] dp = new int[alpMax + 1][copMax +1];
        for (int i = 0; i < alpMax+1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE-10000);
        }
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= alpMax; i++) {
            for (int j = cop; j <= copMax; j++) {
                if (i < alpMax) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j < copMax) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        int next_alp = Math.min(alpMax, i + problem[2]);
                        int next_cop = Math.min(copMax, j + problem[3]);
                        dp[next_alp][next_cop] = Math.min(dp[next_alp][next_cop], dp[i][j] + problem[4]);
                    }
                }
            }

        }
        return dp[alpMax][copMax];
    }
}
