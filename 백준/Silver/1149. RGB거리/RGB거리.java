import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] inputs = new int[n][3];

        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < 3 ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[n][3];
        dp[0][0] = inputs[0][0]; // r
        dp[0][1] = inputs[0][1]; // g
        dp[0][2] = inputs[0][2]; // b

        for (int i = 1 ; i < n ; i++) {
            dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + inputs[i][0]; // 현재 r일 경우
            dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + inputs[i][1]; // 현재 g일 경우
            dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + inputs[i][2]; // 현재 b일 경우
        }
        System.out.println(min(dp[n - 1][0], min(dp[n - 1][1], dp[n - 1][2])));
    }
}