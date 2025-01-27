class Solution {
    final int NUMBER = 1000000007;
    public int solution(int n, int[] money) {
        int[] dp= new int[n+1];
        dp[0]=1;
        for(int m : money){
            for(int i=m;i<=n; i++){
                dp[i] += dp[i-m]%NUMBER;
            }
        }
        return dp[n];
    }
}