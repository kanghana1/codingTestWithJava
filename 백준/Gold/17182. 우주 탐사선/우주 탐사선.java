import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 17182 (중복 가능 -> 플로이드-워셜 알고리즘 사용)
public class Main {
    static int N;
    static int startIdx;
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        fillArray();
        floyd(); // 중복 허용 경로까지 탐색한 최단거리, 근데 중간에 어디를 거쳤는지 모름
        fillDp();
        int ans = explore(startIdx, 1 << startIdx);
        System.out.println(ans);
    }

    private static void fillDp() {
        dp = new int[N][1 << N];
        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    private static int explore(int nowIdx, int isVisit) {
        if (isVisit == (1 << N) - 1) return 0;
        if (dp[nowIdx][isVisit] != -1) return dp[nowIdx][isVisit];

        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < N ; i++) {
            if ((isVisit & 1 << i) == 0) {
                int time = arr[nowIdx][i] + explore(i, isVisit | (1 << i));
                min = Math.min(min, time);
            }
        }
        return dp[nowIdx][isVisit] = min;
    }

    private static void floyd() {
        for (int k = 0 ; k < N ; k++) {
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
                }
            }
        }
    }

    private static void fillArray() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        startIdx = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}