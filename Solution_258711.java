import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {

    private static List<List<Integer>> graph;
    
    private static boolean[] visited;
    private static int[] edgesRoot;
    
    private static int startLine;
    private static int maxLine;
    private static int graphNum;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        initGraph(edges);
        
        startLine = findCreatedLine();
        graphNum = graph.get(startLine).size();
        
        answer[0] = startLine;
        
        removeEdgesFromCreatedLine(startLine);
        
        answer[2] = countBarGraphs();
        answer[3] = countEight();
        answer[1] = graphNum - (answer[2] + answer[3]);

        return answer;
    }

    private void initGraph(int[][] edges) {
        maxLine = 0;

        for (int[] edge : edges) {
            maxLine = Math.max(maxLine, Math.max(edge[0], edge[1]));
        }

        visited = new boolean[maxLine + 1];
        edgesRoot = new int[maxLine + 1];
        graph = new ArrayList<>(maxLine + 1);

        for (int i = 0; i <= maxLine; i++) {
            graph.add(new LinkedList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            edgesRoot[edges[i][1]]++;
        }
    }



    private int countBarGraphs() {
        int count = 0;
        for (int i = 1; i < graph.size(); i++) {
            if (i == startLine) {
                continue;
            }
            if (graph.get(i).isEmpty()) {
                count++;
                visited[i] = true;
            }
        }
        return count;
    }

    private int countEight() {
        int count = 0;
        for (int i = 1; i < graph.size(); i++) {
            if (!visited[i]) {
                if (graph.get(i).size() == 2 && countIncomingEdges(i) == 2) {
                    System.out.println(i);
                    count++;
                }
            }
        }
        return count;
    }

    private int findCreatedLine() {
        int createdLine = -1;
        for (int i = 1; i < graph.size(); i++) {
            if (graph.get(i).size() >= 2 && countIncomingEdges(i) == 0) {
                createdLine = i;
                break;
            }
        }
        visited[createdLine] = true;
        return createdLine;
    }

    private int countIncomingEdges(int line) {
        return edgesRoot[line];
    }

    private void removeEdgesFromCreatedLine(int line) {
        for(int end:graph.get(line)){
            edgesRoot[end]--;
        }
        graph.set(line, new LinkedList<>());
    }
}