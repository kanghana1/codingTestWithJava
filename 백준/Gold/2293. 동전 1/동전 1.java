import java.io.*;
import java.util.*;

// 2293
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n + 1];
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0 ; i < n ; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coin);

        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= k ; j++) {
                if (coin[i] > j) continue;
                dp[j] += dp[j - coin[i]];
            }
        }
        System.out.println(dp[k]);
    }
}