import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = gcd(arrayA);
        int gcdB = gcd(arrayB);

        if (check(arrayA, gcdB)) ans = Math.max(ans, gcdB);
        if (check(arrayB, gcdA)) ans = Math.max(ans, gcdA);
        return answer;
    }
    
    private boolean check(int[] arr, int gcd){
        for (int i:arr){
            if (i%gcd==0) return false;
        }
        return true;
    }

    private int gcd(int[] arr){
        return Arrays.stream(arr).reduce(arr[0], (a, b)->gcd(a, b));
    }

    private int gcd(int a, int b){
        if (a%b == 0) return b;
        return gcd(b, a%b);
    }
}