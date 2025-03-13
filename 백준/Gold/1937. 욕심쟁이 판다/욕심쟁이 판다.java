import java.io.*;
import java.util.*;
import static java.lang.Math.*;
public class Main {
    static int[][] maps;
    static int[][] dp;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        maps = new int[N][N];
        dp = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (dp[i][j] == 0) { // 첫 방문
                    dfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int y, int x) {
        for (int i = 0 ; i < 4 ; i++) {
            int nowY = y + dy[i];
            int nowX = x + dx[i];
            if (canGo(nowY, nowX, y, x) && dp[nowY][nowX] == 0) {
                dfs(nowY, nowX);
                dp[y][x] = max(dp[y][x], dp[nowY][nowX] + 1);
            } else if (canGo(nowY, nowX, y, x)) {
                // 방문을 해본 경우
                dp[y][x] = max(dp[y][x], dp[nowY][nowX] + 1);
            }
        }
        if (dp[y][x] == 0) dp[y][x] = 1;
        ans = max(ans, dp[y][x]);
    }

    private static boolean canGo(int y, int x, int preY, int preX) {
        return y >= 0 && y < maps.length && x >= 0 && x < maps.length && maps[preY][preX] < maps[y][x];
    }
}