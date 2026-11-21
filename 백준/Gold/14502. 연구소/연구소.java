import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int[][] tempArr;
    static int maxSafeArea = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        buildWall(0, N, M);
        System.out.println(maxSafeArea);
    }

    private static void buildWall(int cnt, int N, int M) {
        if (cnt == 3) {
            // 벽을 세운 후 안전 영역 계산
            int safeArea = calculateSafeArea(N, M);
            maxSafeArea = Math.max(maxSafeArea, safeArea);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) { // 빈 공간이면 벽을 세움
                    arr[i][j] = 1; // 벽 세우기
                    buildWall(cnt + 1, N, M); // 재귀 호출
                    arr[i][j] = 0; // 벽 제거 (백트래킹)
                }
            }
        }
    }

    private static int calculateSafeArea(int n, int m) {
        tempArr = new int[n][m];
        // 배열 복사
        for (int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, tempArr[i], 0, m);
        }

        // 바이러스 퍼뜨리기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempArr[i][j] == 2) { // 바이러스 위치
                    spreadVirus(i, j, n, m);
                }
            }
        }

        // 안전 영역 계산
        int safeArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempArr[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

    private static void spreadVirus(int y, int x, int n, int m) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];

            // 상하좌우로 퍼뜨리기
            int[] dy = {-1, 1, 0, 0};
            int[] dx = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if (ny >= 0 && ny < n && nx >= 0 && nx < m && tempArr[ny][nx] == 0) {
                    tempArr[ny][nx] = 2; // 바이러스 퍼뜨리기
                    queue.offer(new int[]{ny, nx});
                }
            }
        }

    }
}