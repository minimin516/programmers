class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i=0; i<s.length; i++) {
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<s[i].length(); j++) {
                sb.append(s[i].charAt(j));

                if(sb.length() < 3) continue;

                if("110".equals(sb.substring(sb.length()-3))) { 
                    sb.setLength(sb.length()-3);
                    cnt++;
                }
            }
            
            int idx = sb.length();
            while(idx > 0) {
                if(sb.charAt(idx-1) == '1')
                    idx--;
                else
                    break;
            }
            
            sb.insert(idx, "110".repeat(cnt));
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}