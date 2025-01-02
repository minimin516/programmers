class Solution {
    
    int possible;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int k = 0; k < answer.length; k++) {
            String binaryNum = Long.toBinaryString(numbers[k]);
            
            int len = 0;
            int h = 1;
            
            while (len < binaryNum.length()) {
                len = (int) Math.pow(2, h++) - 1;
            }
            
            boolean[] isOne = new boolean[len];
            
            int idx = isOne.length - binaryNum.length(); 
            for (int i = 0; i < binaryNum.length(); i++) {
                isOne[idx++] = binaryNum.charAt(i) == '1';
            }
            
            possible = 1; //초기화
            dfs(0, isOne.length - 1, false, isOne); 
            answer[k] = possible;
            
        }
        return answer;
    }
    
    void dfs(int start, int end, boolean isParentZero, boolean[] isOne) {
        if (possible == 0) return;
        
        int mid = (start + end) / 2; 

        if(isParentZero && isOne[mid]) {
            possible = 0;
            return;
        }

        if(start != end) {
            dfs(start, mid - 1, !isOne[mid], isOne);
            dfs(mid + 1, end, !isOne[mid], isOne);
        }

    }
}
