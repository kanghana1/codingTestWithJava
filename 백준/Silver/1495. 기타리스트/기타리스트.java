import java.util.*;
import java.io.*;

public class Main {
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        boolean[][] dp = new boolean[N + 1][max + 1];
        dp[0][start] = true;

        st = new StringTokenizer(br.readLine());

        for (int i = 1 ; i <= N ; i++) {
            int input = Integer.parseInt(st.nextToken());
            for (int j = 0 ; j <= max ; j++) {
                if (dp[i - 1][j]) {
                    int p = j + input;
                    int m = j - input;

                    if (check(p)) dp[i][p] = true;
                    if (check(m)) dp[i][m] = true;
                }
            }
        }

        int ans = -1;
        for (int i = max ; i >= 0 ; i--) {
            if (dp[N][i]) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    private static boolean check(int num) {
        return num <= max && num >= 0;
    }
}