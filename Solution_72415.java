import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int ans = solution(new int[][]{{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1, 0);
        System.out.println(ans);
    }

    public static boolean[] use;
    public static Node[] permu;

    public static int min_ans = Integer.MAX_VALUE;

    public static Node[][] nodes;

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] bd;
    public static int solution(int[][] board, int r, int c) {
        int answer = 0;
        use = new boolean[7];
        nodes = new Node[7][2];
        bd = board;
        int num = 0;
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                if(board[i][j] != 0) {
                    num++;
                    if(nodes[board[i][j]][0] == null){
                        nodes[board[i][j]][0] = new Node(i, j, board[i][j]);
                    } else {
                        nodes[board[i][j]][1] = new Node(i, j, board[i][j]);
                    }

                }
            }
        }
        permu = new Node[num];
        dfs(0, num/2, r, c);
        answer = min_ans;
        return answer;
    }

    public static void dfs(int depth, int end, int r, int c){
        if(depth == end){
            int dis = bfs(r, c);
            min_ans = Math.min(min_ans, dis);
            return;
        }

        for(int i=1;i<=end;i++){
            if(use[i]) continue;

            permu[2*depth] = new Node(nodes[i][0].x, nodes[i][0].y, nodes[i][0].num);
            permu[2*depth+1] = new Node(nodes[i][1].x, nodes[i][1].y, nodes[i][1].num);
            use[i] = true;
            dfs(depth+1, end, r, c);

            permu[2*depth+1] = new Node(nodes[i][0].x, nodes[i][0].y, nodes[i][0].num);
            permu[2*depth] = new Node(nodes[i][1].x, nodes[i][1].y, nodes[i][1].num);
            dfs(depth+1, end, r, c);
            use[i] = false;
        }
    }

    public static int bfs(int r, int c){
        int dis = 0;
        int[][] copy = new int[4][4];
        for(int i=0;i<4;i++)
            copy[i] = bd[i].clone();

        int s_x = r;
        int s_y = c;

        for(Node node_p:permu){
            boolean[][] visited = new boolean[4][4];

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{s_x, s_y, 0});
            visited[s_x][s_y] = true;
            while (!q.isEmpty()){
                int[] node = q.poll();
                if(node[0] == node_p.x && node[1] == node_p.y){
                    dis += (node[2]+1);
                    copy[node[0]][node[1]] = 0;
                    break;
                }

                // 상하좌우 한칸씩
                for(int i=0; i<4; i++){
                    int nx = node[0] + dx[i];
                    int ny = node[1] + dy[i];
                    if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;

                    if(!visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, node[2]+1});
                    }
                }

                // 컨트롤 포함 상하좌워
                for(int i=0; i<4; i++){
                    int nx = node[0];
                    int ny = node[1];

                    while (true){
                        nx += dx[i];
                        ny += dy[i];
                        if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4){
                            nx -= dx[i];
                            ny -= dy[i];
                            if(!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                q.add(new int[]{nx, ny, node[2] + 1});
                            }
                            break;
                        }
                        if(copy[nx][ny] > 0){
                            visited[nx][ny] = true;
                            q.add(new int[]{nx, ny, node[2]+1});
                            break;
                        }
                    }
                }


            }
            s_x = node_p.x;
            s_y = node_p.y;
        }
        return dis;
    }

    public static class Node {
        int x;
        int y;
        int num;
        public Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;

        }
    }
}