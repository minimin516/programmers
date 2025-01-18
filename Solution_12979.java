class Solution {
    public int solution(int n, int[] stations, int w) {int answer = 0;
        int start = 1;

        for (int sub : stations) {
            if (start < sub - w) {
                int end = sub - w;

                int length = end - start;

                int cnt = length / (w * 2 + 1);
                if (length % (w * 2 + 1) != 0)
                    cnt++;

                answer += cnt;
            }

            start = sub + w + 1;
        }

        if(stations[stations.length-1] + w + 1 <= n){
            start = stations[stations.length-1] + w + 1;

            int end = n + 1;

            int length = end - start;

            int cnt = length / (w * 2 + 1);
            if (length % (w * 2 + 1) != 0)
                cnt++;

            answer += cnt;
        }

        return answer;
    }
}