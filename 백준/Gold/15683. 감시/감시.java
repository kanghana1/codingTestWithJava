import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class Main {
    static int r;
    static int c;
    static int[] dy = new int[]{0, 1, 0, -1}; // 오, 아래, 왼, 위
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[][] maps;
    static int ans = Integer.MAX_VALUE;
    static List<int[]> cctv = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        maps = new int[r][c];

        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < c ; j++) {
                int value = Integer.parseInt(st.nextToken());
                maps[i][j] = value;
                if (value >= 1 && value <= 5) {
                    cctv.add(new int[]{i, j});
                }
            }
        }
        dfs(0);
        System.out.println(ans);
    }

    private static void dfs(int idx) {
        if (idx == cctv.size()) {
            int cnt = 0;
            for (int i = 0 ; i < r ; i++) {
                for (int j = 0 ; j < c ; j++) {
                    if (maps[i][j] == 0) cnt++;
                }
            }
            ans = Math.min(cnt, ans);
            return;
        }

        int y = cctv.get(idx)[0];
        int x = cctv.get(idx)[1];
        int value = maps[y][x];


        if (value == 1) {
            dfsWithDir(y, x, new int[]{0}, idx);
            dfsWithDir(y, x, new int[]{1}, idx);
            dfsWithDir(y, x, new int[]{2}, idx);
            dfsWithDir(y, x, new int[]{3}, idx);

        } else if (value == 2) {
            dfsWithDir(y, x, new int[]{0, 2}, idx);
            dfsWithDir(y, x, new int[]{1, 3}, idx);

        } else if (value == 3) {
            dfsWithDir(y, x, new int[]{0, 1}, idx);
            dfsWithDir(y, x, new int[]{1, 2}, idx);
            dfsWithDir(y, x, new int[]{2, 3}, idx);
            dfsWithDir(y, x, new int[]{3, 0}, idx);

        } else if (value == 4) {
            dfsWithDir(y, x, new int[]{0, 1, 2}, idx);
            dfsWithDir(y, x, new int[]{0, 1, 3}, idx);
            dfsWithDir(y, x, new int[]{1, 2, 3}, idx);
            dfsWithDir(y, x, new int[]{0, 2, 3}, idx);

        } else if (value == 5) {
            dfsWithDir(y, x, new int[]{0, 1, 2, 3}, idx);
        }

    }

    private static void dfsWithDir(int y, int x, int[] dirs, int idx) {
        List<int[]> visited = new ArrayList<>();

        for (int dir : dirs) {
            search(y, x, dir, visited);
        }

        dfs(idx + 1);
        rollback(visited);
    }

    private static void search(int y, int x, int dir, List<int[]> visited) {
        while (true) {
            y += dy[dir];
            x += dx[dir];

            if (y < 0 || y >= r || x < 0 || x >= c) break;
            if (maps[y][x] == 6) break;

            if (maps[y][x] == 0) {
                maps[y][x] = 7;
                visited.add(new int[]{y, x});
            }
        }
    }

    private static void rollback(List<int[]> visit) {
        for (int[] arr : visit) {
            maps[arr[0]][arr[1]] = 0;
        }
    }

}
