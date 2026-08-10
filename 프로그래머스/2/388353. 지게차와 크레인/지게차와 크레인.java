import java.util.*;

class Solution {
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        String[][] g = new String[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                if (i == 0 || i == n + 1 || j == 0 || j == m + 1) g[i][j] = "0";
                else g[i][j] = storage[i - 1].charAt(j - 1) + "";
            }
        }

        for (String req : requests) {
            if (req.length() == 2) {
                String obj = req.charAt(0) + "";
                for (int i = 1; i <= n; i++)
                    for (int j = 1; j <= m; j++)
                        if (g[i][j].equals(obj)) g[i][j] = "0";
            } else {
                String obj = req;
                boolean[][] visited = new boolean[n + 2][m + 2];
                boolean[][] toRemove = new boolean[n + 2][m + 2];
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{0, 0});
                visited[0][0] = true;
                while (!q.isEmpty()) {
                    int[] node = q.poll();
                    int y = node[0], x = node[1];
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d], nx = x + dx[d];
                        if (ny < 0 || ny >= n + 2 || nx < 0 || nx >= m + 2) continue;
                        if (visited[ny][nx]) continue;
                        if (g[ny][nx].equals("0")) {
                            visited[ny][nx] = true;
                            q.add(new int[]{ny, nx});
                        } else if (g[ny][nx].equals(obj)) {
                            toRemove[ny][nx] = true;
                        }
                    }
                }
                for (int i = 1; i <= n; i++)
                    for (int j = 1; j <= m; j++)
                        if (toRemove[i][j]) g[i][j] = "0";
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                if (!g[i][j].equals("0")) ans++;
        return ans;
    }
}