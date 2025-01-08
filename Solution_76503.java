import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        if(Arrays.stream(a).sum() !=0) return -1;

        long answer = 0;
        int[] indegree = new int[a.length];
        boolean[] isVisiteds = new boolean[a.length];
        long[] temp = new long[a.length];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < a.length; i++) {
            temp[i] = a[i];
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);

            indegree[node1]++;
            indegree[node2]++;
        }

        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 1) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            isVisiteds[now] = true;

            ArrayList<Integer> nodes = graph.get(now);
            for(int i = 0 ; i < nodes.size(); i++) {
                int curr = nodes.get(i);
                if(isVisiteds[curr]) continue;
                indegree[curr]--;
                temp[curr] += temp[now];
                answer += Math.abs(temp[now]);
                temp[now] = 0;

                if(indegree[curr] == 1) {
                    q.offer(curr);
                }
            }
        }

        for(int i = 0; i < temp.length; i++) {
            if(temp[i] != 0) {
                answer = -1;
                break;

            }
        }
        return answer;
    }
}