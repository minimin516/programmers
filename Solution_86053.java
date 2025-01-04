class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = (long) (2 * 10e9 * 2 * 10e5);
        long min = 0;
        long max = (long) (2 * 10e9 * 2 * 10e5);
        
         while (min <= max) {
            long mid = (min + max) / 2; 
            long gold = 0;
            long silver = 0;
            long add = 0;
             
            for(int i = 0; i < g.length; i++) {
                int curGold = g[i];
                int curSilver = s[i];
                int curWeight = w[i];
                int curTime = t[i];

                long iter = Math.round(mid / curTime / 2.0);
                
                gold += Math.min(curGold, iter * curWeight);
                silver += Math.min(curSilver, iter * curWeight);
                add += Math.min(curGold + curSilver, iter * curWeight);
            }
             if (a <= gold && b <= silver && a+b <= add){
                 max = mid - 1;
                 answer = Math.min(mid, answer);
                 continue;
             }
             min = mid + 1;
         }
        return answer;
    }
}
