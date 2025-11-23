import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static List<int[]> rotation = new ArrayList<>();
    static int N;
    static int M;
    static int K;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int[][] players = input();
        dfs(0, new boolean[K], players);
        System.out.println(ans);
    }

    private static void dfs(int cnt, boolean[] isVisit, int[][] players) {
        if (cnt == K) {
            int arraySum = sum(players);
            ans = Math.min(ans, arraySum);
        }
        for (int i = 0 ; i < K ; i++) {
            if (!isVisit[i]) {
                int[][] results = rotations(i, players);
                isVisit[i] = true;
                dfs(cnt + 1, isVisit, results);
                isVisit[i] = false;
            }
        }
    }

    private static int[][] rotations(int idx, int[][] players) {
        int[][] player = copy(players);
        int[] rotateOps = rotation.get(idx);
        int r = rotateOps[0];
        int c = rotateOps[1];
        int s = rotateOps[2];
        for (int i = 1 ; i <= s ; i++) {
            int v = player[r - i][c - i];
            int y = r - i;
            int x = c - i;
            while (true) {
                if (y == r - i && x == c - i + 1) {
                    player[y][x] = v;
                    break;
                }
                if (x == c - i) {
                    if (y == r - i) {
                        player[y][x] = player[y + 1][x];
                        y++;
                    } else if (y == r + i) {
                        player[y][x] = player[y][x + 1];
                        x++;
                    } else {
                        player[y][x] = player[y + 1][x];
                        y++;
                    }
                } else if (x == c + i) {
                    if (y == r - i) {
                        player[y][x] = player[y][x - 1];
                        x--;
                    } else if (y == r + i) {
                        player[y][x] = player[y - 1][x];
                        y--;
                    } else {
                        player[y][x] = player[y - 1][x];
                        y--;
                    }
                } else if (y == r - i) {
                    player[y][x] = player[y][x - 1];
                    x--;
                } else if (y == r + i) {
                    player[y][x] = player[y][x + 1];
                    x++;
                }
            }
        }
        return player;
    }

    private static int sum(int[][] players) {
        int min = Integer.MAX_VALUE;
        for (int i = 1 ; i <= N ; i++) {
            int sum = 0;
            for (int j = 1 ; j <= M ; j++) {
                sum += players[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    private static int[][] copy(int[][] arr) {
        int[][] newArray = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                newArray[i][j] = arr[i][j];
            }
        }
        return newArray;
    }


    private static int[][] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] players = new int[N + 1][M + 1];
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= M ; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotation.add(new int[]{r, c, s});
        }

        return players;
    }
}

