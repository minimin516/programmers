import java.util.*;
/*
제시 변수 
n -> 퍼즐의 수, diff -> 퍼즐의 난이도, time_cur -> 현재 퍼즐의 소요 시간, time_prev -> 이전 퍼즐의 소요 시간, level -> 숙련도
요구사항 1. 숙련도(level)에 따라 틀리는 횟수가 변경되어야 함
요구사항 2. 퍼즐의 난이도(diff)가 숙련도(level)보다 작거나 같으면 퍼즐을 틀리지 않고 time_cur 동안 해결해야함
요구사항 3. 퍼즐 난이도가 숙련도보다 더 클 경우 diff - level번 틀리는 것을 허용.
       3-1. 틀릴 때 마다 time_cur + time_prev만큼 시간을 사용함.
       3-2. 해당 문제를 다시 풀어야함 이떄 시간은 time_cur만큼 주어짐
*/
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int level = Arrays.stream(diffs)
            .min()
            .getAsInt();
        
        Map<Integer, long[]> numMap = new HashMap<>();
        for(int i=0; i<diffs.length; i++) {
            if(i > 0) {
                numMap.put(i, new long[]{diffs[i], times[i-1], times[i]});    
            } else {
                numMap.put(i, new long[]{diffs[i], 0, times[i]});    
            }
            
        }
        
        while(true) {
            long time = 0;
            for(int key : numMap.keySet()) {
                long[] value = numMap.get(key);
                
                if(level >= value[0]) {
                    time += value[2];
                } else {
                    long wrongCount = value[0] - level;
                    time += (value[1] + value[2]) * wrongCount + value[2];
                }
                
                if(time > limit) {
                    time = limit+1;
                    break;
                }
            }
            
            if(time <= limit) break;
            
            level++;
        }
        return level;
    }
}