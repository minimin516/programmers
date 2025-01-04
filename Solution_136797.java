import java.util.*;

class Solution {

    int[][] pad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
    public int solution(String numbers) {
        char[] numbers_ = numbers.toCharArray();
        int[][][] dp = new int[numbers_.length][10][10];

        Arrays.stream(dp).forEach(d -> Arrays.stream(d).forEach(p -> Arrays.fill(p, Integer.MAX_VALUE)));

        dp[0][numbers_[0] - '0'][6] = cal(numbers_[0] - '0', 4);
        dp[0][4][numbers_[0] - '0'] = cal(numbers_[0] - '0', 6);

        for (int i = 1; i < numbers_.length; i++) {
            int n = numbers_[i] - '0';
            for (int l = 0; l < 10; l++) {
                for (int r = 0; r < 10; r++) {
                    if (l == r || dp[i - 1][l][r] == Integer.MAX_VALUE) continue;
                    dp[i][n][r] = Math.min(dp[i][n][r], dp[i-1][l][r] + cal(n, l));
                    dp[i][l][n] = Math.min(dp[i][l][n], dp[i-1][l][r] + cal(n, r));
                }
            }
        }
        return Arrays.stream(dp[numbers_.length - 1]).flatMapToInt(Arrays::stream).min().orElse(0);
    }
    int cal(int s, int e) {
        int[] sp = null, ep = null;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (pad[r][c] == s) sp = new int[]{r, c};
                if (pad[r][c] == e) ep = new int[]{r, c};
            }
        }

        int rd = Math.abs(sp[0] - ep[0]), cd = Math.abs(sp[1] - ep[1]);

        if (rd == 0 && cd == 0) return 1;

        return Math.min(rd, cd) * 3 + (Math.max(rd, cd) - Math.min(rd, cd)) * 2;
    }
}
