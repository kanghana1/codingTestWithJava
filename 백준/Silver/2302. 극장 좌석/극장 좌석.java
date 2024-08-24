import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] vip = new int[M];
        int[] dp = new int[41];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4 ; i <= 40 ; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        Long ans = 1L;
        int prev = 0;
        for (int i = 0 ; i < M ; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num - prev - 1 != 0) {
                ans *= dp[num - prev - 1];
            }
            prev = num;
        }
        if (prev <= N - 2) {
            ans *= dp[N - prev];
        }
        System.out.println(ans);
    }
}