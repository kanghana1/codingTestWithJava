import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int max = 0;
        for (int i = 0 ; i < N ; i++) {
            input[i] = Integer.parseInt(br.readLine());
            max = Math.max(input[i], max);
        }
        int[][] dp = new int[max + 1][5];
        if (max < 4) {
            for (int i = 0 ; i < N ; i++) {
                sb.append(input[i]).append("\n");
            }
            System.out.println(sb);
            return;
        }
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        dp[1][4] = 1;
        dp[2][4] = 2;
        dp[3][4] = 3;
        for (int i = 4 ; i <= max ; i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
            dp[i][4] = dp[i][1] + dp[i][2] + dp[i][3];
        }
        for (int i = 0 ; i < N ; i++) {
            sb.append(dp[input[i]][4]).append("\n");
        }
        System.out.println(sb);
    }
}