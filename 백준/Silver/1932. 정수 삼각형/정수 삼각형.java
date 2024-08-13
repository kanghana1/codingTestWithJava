import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] inputs = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1 ; i <= n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= i ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = inputs[1][1];
        for (int i = 2 ; i <= n ; i++) {
            for (int j = 1 ; j <= i ; j++) {
                // 가장자리 예외처리
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j] + inputs[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + inputs[i][j];
                } else {
                    dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + inputs[i][j];
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1 ; i <= n ; i++) {
             if (max < dp[n][i]) max = dp[n][i];
        }
        System.out.println(max);
    }
}