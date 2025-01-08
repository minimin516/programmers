class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer;
        boolean possible = true;
        int minX = x;
        int maxX = x;
        int minY = y;
        int maxY = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int queryType = queries[i][0], moveCount = queries[i][1];

            if (queryType == 0) {
                if (minY == 0) {
                    maxY = Math.min(m - 1, maxY + moveCount);
                } else {
                    if (minY + moveCount >= m) {
                        possible = false;
                        break;
                    } else {
                        minY = minY + moveCount;
                        maxY = Math.min(m - 1, maxY + moveCount);
                    }
                }
            }else if (queryType == 1) {
                if (maxY == m - 1) {
                    minY = Math.max(0, minY - moveCount);
                } else {
                    if (maxY - moveCount < 0) {
                        possible = false;
                        break;
                    } else {
                        maxY = maxY - moveCount;
                        minY = Math.max(0, minY - moveCount);
                    }
                }
            }else if (queryType == 2) {
                if (minX == 0) {
                    maxX = Math.min(n - 1, maxX + moveCount);
                } else {
                    if (minX + moveCount >= n) {
                        possible = false;
                        break;
                    } else {
                        minX = minX + moveCount;
                        maxX = Math.min(n - 1, maxX + moveCount);
                    }
                }
            }else if (queryType == 3) {
                if (maxX == n - 1) {
                    minX = Math.max(0, minX - moveCount);
                } else {
                    if (maxX - moveCount < 0) {
                        possible = false;
                        break;
                    } else {
                        maxX = maxX - moveCount;
                        minX = Math.max(0, minX - moveCount);
                    }
                }
            }
        }

        if (possible) {
            answer = (long) (maxX - minX + 1) * (long) (maxY - minY + 1);
        } else {
            answer = 0;
        }

        return answer;
    }
}
