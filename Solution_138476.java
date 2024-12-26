import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int count = 0;
        int num = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int tan : tangerine) {
            map.put(tan, map.getOrDefault(tan, 0) + 1);
        }
        
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list, Collections.reverseOrder());
        
        for (int val : list) {
            if (num + val >= k) {
                count++;
                break;
            } else {
                num += val;
                count++;
            }
        }
        
        return count;
    }
}