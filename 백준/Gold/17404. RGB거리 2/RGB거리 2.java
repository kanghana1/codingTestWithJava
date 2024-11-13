import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
    static final int INF = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] price = new int[N + 1][3];
        int ans = 10000000;

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            price[i][0] = Integer.parseInt(st.nextToken());
            price[i][1] = Integer.parseInt(st.nextToken());
            price[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][3];
        for(int k = 0; k < 3; k++) {
            for(int i = 0 ; i < 3; i++) {
                if(i == k) dp[1][i] = price[1][i];
                else dp[1][i] = INF;
            }

            for (int i = 2 ; i <= N ; i++) {
                dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + price[i][0];
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + price[i][1];
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + price[i][2];
            }

            for(int i = 0 ; i < 3; i++)
                if(i != k) ans = min(ans, dp[N][i]);
        }
        System.out.println(ans);
    }
}