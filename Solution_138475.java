class Solution {
    public int[] solution(int e, int[] starts) {
        int startsLen = starts.length;
        int[] answer = new int[startsLen];
        int[] cntInfos = new int[e + 1];
        int[] maxValues = new int[e + 1];
        
        for (int i = 1; i <= e; ++i) {
            for (int j = i; j <= e; j += i) {
                ++cntInfos[j];
            }
        }

        int maxInfo = 0;
        int maxValue = 0;
        for (int i = e; i >= 1; --i) {
            if (cntInfos[i] >= maxInfo) {
                maxInfo = cntInfos[i];
                maxValue = i;
            }
            
            maxValues[i] = maxValue;
        }
        
        for (int i = 0; i < startsLen; ++i) {
            answer[i] = maxValues[starts[i]];
        }
        
        return answer;
    }
}
