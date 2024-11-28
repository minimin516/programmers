import java.util.*;

class Solution {
    static int n, m;
    static int[] oils;

    public int solution(int[][] land) {
        int answer = 0;

        n = land.length;
        m = land[0].length;
        oils = new int[m];

        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && visited[i][j] == false) {
                    bfs(land, visited, i, j);
                }
            }
        }

        answer = Arrays.stream(oils).max().getAsInt();
        System.out.println(answer);
        return answer;
    }

    public void bfs(int[][] land, boolean[][] visited, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int cnt = 1;
        Set<Integer> set = new HashSet<>();

        while (!q.isEmpty()) {
            int[] now = q.poll();
            set.add(now[1]);

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (!checkRange(nx, ny)) {
                    continue;
                }

                if (land[nx][ny] == 1 && visited[nx][ny] == false) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    cnt += 1;
                }
            }
        }

        for (int idx : set) {
            oils[idx] += cnt;
        }
    }

    public boolean checkRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m ? false : true;
    }
}