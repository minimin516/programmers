import java.util.*;

public class Solution {

    ArrayList<Integer> keyList;
    int n, m;

    public int solution(String[][] relation) {

        keyList = new ArrayList<>();
        m = relation[0].length;
        n = relation.length;

        for(int i = 1; i < (1<<m); i++) {
            Set<String> uniqueStr = new HashSet<>();
            for (int j = 0; j < n; j++) {
                StringBuilder sb = new StringBuilder(); 
                for (int k = 0; k < m; k++) {
                    if ((i & (1 << k)) > 0)
                        sb.append(relation[j][k]);
                }
                uniqueStr.add(sb.toString());
            }
            if (uniqueStr.size() != n) continue;
            checkMin(i);
        }
        return keyList.size();
    }
    public void checkMin(int i) {
        for (Integer cKey : keyList)
            if ((cKey & i) == cKey) return;
        keyList.add(i);
    }
}