import java.util.*;

class Solution {
    static HashMap<String, Integer> result = new HashMap<>();
    static HashMap<String, String> recommend = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        for(int i = 0; i < enroll.length; i++){
            result.put(enroll[i], 0);
        }
        
        for(int i = 0; i < enroll.length; i++){
            recommend.put(enroll[i], referral[i]);
        }
        
        for(int i = 0; i < seller.length; i++){
            dfs(seller[i], amount[i] * 100);
        }
        
        for(int i = 0; i < enroll.length; i++){
            answer[i] = result.get(enroll[i]);
        }
        
        return answer;
    }
    public static void dfs(String name, int money){
        if("-".equals(name) || money == 0)
            return;
        
        int toMoney = (int)(money * 0.1);
        int surplusMoney = money - toMoney;
        int updateMoney = result.get(name) + surplusMoney;
        
        result.replace(name, updateMoney);
        
        dfs(recommend.get(name), toMoney);
    }
}