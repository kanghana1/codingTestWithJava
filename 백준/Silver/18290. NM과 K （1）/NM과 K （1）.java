import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] maps;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};
    static boolean[][] isVisit;
    static int M;
    static int K;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        isVisit = new boolean[N][M];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (N == 1 && M == 1) {
            System.out.println(maps[0][0]);
            return;
        }
        backtracking(0, 0, 0);
        System.out.println(ans);
    }

    private static void backtracking(int idx, int cnt, int sum) {
        if (cnt == K) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = idx ; i < maps.length * M ; i++) {
            int y = i / M;
            int x = i % M;
            if (isVisit[y][x] || isNeighbor(y, x)) continue;

            isVisit[y][x] = true;
            backtracking(i + 1, cnt + 1, sum + maps[y][x]);
            isVisit[y][x] = false;
        }
    }

    private static boolean isNeighbor(int y, int x) {
        for (int i = 0 ; i < 4 ; i++) {
            if (canGo(y + dy[i], x + dx[i])) {
                if (isVisit[y + dy[i]][x + dx[i]]) return true;
            }
        }
        return false;
    }


    private static boolean canGo(int y, int x) {
        return y >= 0 && y < maps.length && x >= 0 && x < maps[0].length;
    }
}
