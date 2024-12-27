import java.util.*;

class Solution {
    public int solution(int n, int k) {
        
        int count = 0;
        String[] nums = Integer.toString(n, k).split("0");
        for (String num : nums) {
            if (num.length() < 1) continue;
            if (isPrime(Long.parseLong(num))) count++;
        }
        
        return count;
        
    }
    
    boolean isPrime(long n) {
        if(n == 1)  return false;
        
        long a = (long) Math.sqrt(n) + 1L;
        for(int i = 2; i < a; i++) {
            if(n % i == 0) return false;
        }
        
        return true;
    }
}