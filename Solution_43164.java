import java.util.*;

class Solution {
    
    boolean[] isVisited;
    ArrayList<String> list = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, new Comparator<String[]>() {
			
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[1].toString().compareTo(o2[1].toString());
            }			
        });
        
        isVisited = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN", tickets);
        
        String[] answer = list.get(0).split(" ");
        
        return answer;
    }

    public void dfs(int depth, String now, String path, String[][] tickets){
        if(depth == tickets.length){
            list.add(path);
            return;
        }
        
        for(int i=0; i<isVisited.length; i++){
            if(!isVisited[i] && now.equals(tickets[i][0])){
                isVisited[i] = true;
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
                isVisited[i] = false;
            }
        }
        
    }
}