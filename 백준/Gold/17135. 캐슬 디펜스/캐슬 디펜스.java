import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] maps;
    static Map<Integer, Boolean> enemies = new HashMap<>();
    static int[] dy = new int[]{0, -1, 0};
    static int[] dx = new int[]{-1, 0, 1};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        makeMaps(br);

        int ans = 0;
        for (int i = 1 ; i <= M ; i++) {
            for (int j = i + 1 ; j <= M ; j++) {
                for (int k = j + 1 ; k <= M ; k++) {
                    initMap();
                    int killCount = playGame(i, j, k, D);
                    ans = Math.max(ans, killCount);
                }
            }
        }
        System.out.println(ans);
    }

    private static int playGame(int i, int j, int k, int dis) {
        int cnt = 0;
        int nowY = maps.length;
        while (nowY > 1) {
            int fstNodeidx = move(nowY, i, dis, nowY);
            int scdNodeidx = move(nowY, j, dis, nowY);
            int trdNodeidx = move(nowY, k, dis, nowY);
            cnt += (killEnemy(fstNodeidx) + killEnemy(scdNodeidx) + killEnemy(trdNodeidx));

            nowY--;
        }

        return cnt;
    }

    private static int move(int y, int x, int dis, int limit) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x, dis});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int distance = node[2];
            if (distance <= 0) continue;

            for (int i = 0 ; i < 3 ; i++) {
                int ny = node[0] + dy[i];
                int nx = node[1] + dx[i];
                int idx = ny * M + nx;
                if (canGo(ny, nx, limit)) {
                    if (enemies.get(idx) != null && enemies.get(idx)) {
                        return idx;
                    } else {
                        queue.add(new int[]{ny, nx, distance - 1});
                    }
                }
            }
        }

        return 0;
    }

    private static int killEnemy(int idx) {
        if (idx > 0 && enemies.get(idx)) {
            enemies.put(idx, false);
            return 1;
        }
        return 0;
    }

    private static boolean canGo(int y, int x, int limitY) {
        return y > 0 && y < limitY && x > 0 && x <= M;
    }

    private static void initMap() {
        enemies.replaceAll((key, value) -> {
            if (!value) return true;
            return value;
        });
    }

    private static void makeMaps(BufferedReader br) throws IOException {
        maps = new int[N + 1][M + 1];
        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= M ; j++) {
                int idx = M * i + j;
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 1) {
                    enemies.put(idx, true);
                }
            }
        }
    }


}
