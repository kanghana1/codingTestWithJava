import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cards = new int[N + 1];
        int[] dp = new int[N + 1];
        for (int i = 1 ; i <= N ; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            dp[i] = cards[i];
        }

        if (N == 1) {
            System.out.println(dp[1]);
            return;
        }
        for (int i = 2 ; i <= N ; i++) {
            for (int j = 1 ; j <= (i - 1) / 2 ; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
            if (i % 2 == 0) {
                dp[i] = Math.max(dp[i], dp[i / 2] * 2);
            }
        }
        System.out.println(dp[N]);
    }
}