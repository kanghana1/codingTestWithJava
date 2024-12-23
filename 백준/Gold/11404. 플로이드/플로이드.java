import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static int MAX = 10000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); // city
        int M = Integer.parseInt(br.readLine()); // bus
        dp = new int[N + 1][N + 1];

        fill(N);
        for (int i = 1 ; i <= M ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dep = Integer.parseInt(st.nextToken());
            int des = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            dp[dep][des] = Math.min(dp[dep][des], price);
        }

        floyd(N);

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (dp[i][j] == MAX) dp[i][j] = 0;
                sb.append(dp[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());


    }

    private static void floyd(int N) {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }

    private static void fill(int n) {
        for (int i = 0 ; i <= n ; i++) {
            for (int j = 0 ; j <= n ; j++) {
                if (i == j) dp[i][j] = 0;
                else dp[i][j] = MAX;
            }
        }
    }
}