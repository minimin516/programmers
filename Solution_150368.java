import java.util.*;

class Solution {
    static int[] percent = {0, 10, 20, 30, 40};
    static int total_join = 0, total_price = 0, min = Integer.MAX_VALUE;
    
    public int[] solution(int[][] users, int[] emoticons) {
        for (int[] user : users) {
            min = Math.min(min, user[0]); 
        }
        for (int i = 0; i < 5; i++) {
            if (min <= percent[i]) {
                min = i;
                break;
            }
        }
        
        int[] discounts = new int[emoticons.length];
        comb(discounts, 0, emoticons.length, users, emoticons);
        
        int[] answer = {total_join, total_price};
        return answer;
    }
    
    private void comb(int[] discounts, int s, int n, int[][] users, int[] emoticons) {
        if (s == n) {
            cal(users, emoticons, discounts);
            return;
        }
        
        for (int i = s; i < n; i++) {
            for (int j = min; j < 5; j++) {
                discounts[i] = percent[j];
                comb(discounts, i + 1, n, users, emoticons);
            }
        }
    }
    
    private void cal(int[][] users, int[] emoticons, int[] discounts) {
        int join = 0;
        int price = 0;
        
        for (int[] user : users) {
            int userMinDiscount = user[0];
            int userMaxPay = user[1];
            int sum = 0;
            
            for (int i = 0; i < discounts.length; i++) {
                if (discounts[i] < userMinDiscount) continue;
                sum += sale(emoticons[i], discounts[i]);
            }
            
            if (userMaxPay <= sum) join++;
            else price += sum;
        }
        
        if (join > total_join) {
            total_join = join;
            total_price = price;
        } else if (join == total_join && price > total_price) {
            total_price = price;
        }
    }
     
    private int sale(int price, int percent) {
        return (price / 100) * (100 - percent);
    }
}