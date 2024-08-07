import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            int col = Integer.parseInt(br.readLine());
            int[][] input = new int[2][col + 1];
            int[][] dp = new int[2][col + 1];

            for (int j = 0 ; j < 2 ; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 1 ; k <= col ; k++) {
                    input[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][1] = input[0][1];
            dp[1][1] = input[1][1];

            for (int j = 2 ; j <= col ; j++) {
                dp[0][j] = max(dp[1][j - 1], dp[1][j - 2]) + input[0][j];
                dp[1][j] = max(dp[0][j - 1], dp[0][j - 2]) + input[1][j];
            }

            bw.write(max(dp[0][col], dp[1][col]) + "\n");
        }

        bw.flush();
        bw.close();
    }
}

