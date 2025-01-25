import java.io.*;
import java.util.*;
public class Main {
    static int r;
    static int c;
    static int[][] dp;
    static int[][] map;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dp = new int[r][c];
        map = new int[r][c];

        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < c ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        int cnt = dfs(0, 0);
        System.out.println(cnt);
    }

    private static int dfs(int y, int x) {
        int height = map[y][x];
        if (y == r - 1 && x == c - 1) {
            return 1;
        }

        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;
        for (int i = 0 ; i < 4 ; i++) {
            if (canGo(y + dy[i], x + dx[i], height)) {
                dp[y][x] += dfs(y + dy[i], x + dx[i]);
            }
        }
        return dp[y][x];
    }

    private static boolean canGo(int y, int x, int height) {
        return y >= 0 && y < r && x >= 0 && x < c && map[y][x] < height;
    }
}
