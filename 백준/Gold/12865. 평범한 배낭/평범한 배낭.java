import java.io.*;
import java.util.*;
import static java.lang.Math.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] weightValue = new int[N + 1][2];
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            weightValue[i][0] = Integer.parseInt(st.nextToken()); // weight
            weightValue[i][1] = Integer.parseInt(st.nextToken()); // value
        }

        for (int i = 1 ; i <= N ; i++) {
            int w = weightValue[i][0];
            int v = weightValue[i][1];
            for (int j = 1 ; j <= K ; j++) {
                if (dp[i - 1][j] != 0) {
                    dp[i][j] = max(dp[i][j], dp[i - 1][j]);
                }
                if (w > K) continue;
                if (dp[i - 1][j] != 0 && j + w <= K) {
                    dp[i][j + w] = max(dp[i - 1][j + w], dp[i - 1][j] + v);
                }
            }
            if (w > K) continue;
            dp[i][w] = max(dp[i][w], v);
        }
        int max = 0;
        for (int i = 1 ; i <= K ; i++) {
            if (max < dp[N][i]) max = dp[N][i];
        }
        System.out.println(max);
    }
}