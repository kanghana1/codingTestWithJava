import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static int M;
    static int N;
    static int H;
    static int[] dz = {1, -1, 0, 0, 0, 0}; // 위, 아래
    static int[] dy = {0, 0, -1, 1, 0, 0}; // 북, 남
    static int[] dx = {0, 0, 0, 0, -1, 1}; // 서, 동
    static int[][][] tomatoes;
    static int[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomatoes = new int[H][N][M];
        map = new int[H][N][M];

        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < N ; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0 ; k < M ; k++) {
                    tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomatoes[i][j][k] == 0) map[i][j][k] = -1;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (tomatoes[z][y][x] == 1) {
                        queue.add(new int[]{z, y, x});
                    }
                }
            }
        }
        bfs(queue);

        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (map[z][y][x] == -1) {
                        System.out.println(-1);
                        return;
                    }
                    ans = Math.max(ans, map[z][y][x]);
                }
            }
        }
        System.out.println(ans);
    }

    private static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0 ; i < dy.length ; i++) {
                int nowZ = now[0] + dz[i];
                int nowY = now[1] + dy[i];
                int nowX = now[2] + dx[i];

                if (canExplore(nowZ, nowY, nowX)) {
                    queue.add(new int[]{nowZ, nowY, nowX});
                    map[nowZ][nowY][nowX] = map[now[0]][now[1]][now[2]] + 1;
                }
            }
        }
    }

    private static boolean canExplore(int z, int y, int x) {
        return z >= 0 && z < H && y >= 0 && y < N && x >= 0 && x < M && map[z][y][x] == -1;
    }
}
