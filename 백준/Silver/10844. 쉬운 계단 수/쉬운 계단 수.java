import java.io.*;

public class Main {
    static int VALUE = 1000000000;
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int N = Integer.parseInt(br.readLine());

         int[][] dp = new int[N + 1][10];

         for (int i = 1 ; i <= 9 ; i++) {
             dp[1][i] = 1;
         }

         if (N > 1) {
             for (int i = 2 ; i <= N ; i++) {
                 for (int j = 0 ; j <= 9 ; j++) {
                     int value = 0;
                     if (check(j - 1)) value += (dp[i - 1][j - 1] % VALUE);
                     if (check(j + 1)) value += (dp[i - 1][j + 1] % VALUE);
                     dp[i][j] = value;
                 }
             }
         }


         Long ans = 0L;
         for (int i = 0 ; i <= 9 ; i++) {
             ans += (dp[N][i] % VALUE);
         }

        System.out.println(ans % VALUE);
    }

    public static boolean check(int idx) {
        return idx >= 0 && idx <= 9;
    }

}