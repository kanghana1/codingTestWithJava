import java.io.*;
import java.util.*;
public class Main {
    static char[][] board;
    static boolean[] isVisit;
    static int[] dy = new int[]{0, 1, 0, -1}; // 동, 남, 서, 북
    static int[] dx = new int[]{1, 0, -1, 0};
    static int max = 0;
    static int r;
    static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r + 1][c + 1];
        isVisit = new boolean[26];

        for (int i = 1 ; i <= r ; i++) {
            String str = br.readLine();
            for (int j = 1 ; j <= c ; j++) {
                board[i][j] = str.charAt(j - 1);
            }
        }
        isVisit[board[1][1] - 65] = true;
        dfs(1, 1, 1);
        System.out.println(max);
    }

    private static boolean canGo(int y, int x) {
        return y > 0 && y <= r && x > 0 && x <= c && !isVisit[board[y][x] - 65];
    }
    private static void dfs(int y, int x, int cnt) {
        for (int i = 0 ; i < 4 ; i++) {
            if (canGo(y + dy[i], x + dx[i])) {
                isVisit[board[y + dy[i]][x + dx[i]] - 65] = true;
                dfs(y + dy[i], x + dx[i], cnt + 1);
                isVisit[board[y + dy[i]][x + dx[i]] - 65] = false;
            }
        }
        max = Math.max(max, cnt);
    }
}