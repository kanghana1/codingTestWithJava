import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] dy = {-1, 0, 1, 0}; // 위 오 아 왼
    static int[] dx = {0, 1, 0, -1};
    static int ans = 0;
    static int[][] inputs;
    static boolean[][] isVisit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inputs = new int[N][M];
        isVisit = new boolean[N][M];


        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                isVisit[i][j] = true;
                dfs(i, j, 1, inputs[i][j]);
                isVisit[i][j] = false;
            }
        }
        System.out.println(ans);
    }
    private static void dfs(int y, int x, int cnt, int sum) {
        if (cnt == 4) {
            ans = Math.max(ans, sum);
            return ;
        }
        for (int i = 0 ; i < 4 ; i++) {
            int row = y + dy[i];
            int col = x + dx[i];
            if (range(row, col) &&  !isVisit[row][col]) {
                if (cnt == 2) {
                    isVisit[row][col] = true;
                    dfs(y, x, cnt + 1, sum + inputs[row][col]);
                    isVisit[row][col] = false;
                }
                isVisit[row][col] = true;
                dfs(row, col, cnt + 1, sum + inputs[row][col]);
                isVisit[row][col] = false;
            }
        }
    }

    private static boolean range(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}