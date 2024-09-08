import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Long[][] dp = new Long[N + 1][2];
        dp[1][0] = 0L;
        dp[1][1] = 1L;
        if (N >= 2) {
            dp[2][0] = 1L;
            dp[2][1] = 0L;
            for (int i = 3 ; i <= N ; i++) {
                dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
                dp[i][1] = dp[i - 1][0];
            }
        }
        System.out.println(dp[N][1] + dp[N][0]);
    }
}