import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        Long[][] dp = new Long[N + 1][10];
        for (int i = 0 ; i <= 9 ; i++) {
            dp[1][i] = i + 1L;
        }

        for (int i = 2 ; i <= N ; i++) {
            dp[i][0] = 1L;
            for (int j = 1 ; j <= 9 ; j++) {
                dp[i][j] = dp[i][j - 1] % 10007 + dp[i - 1][j] % 10007;
            }
        }
        System.out.println(dp[N][9] % 10007);
    }
}