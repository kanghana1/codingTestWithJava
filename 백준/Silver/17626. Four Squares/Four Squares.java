import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static List<int[]> lst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        if (N == 1) {
            System.out.println(1);
            return;
        } else if (N == 2) {
            System.out.println(2);
            return;
        } else if (N == 3) {
            System.out.println(3);
            return;
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        int start = 2;
        while (start * start <= N) {
            dp[start * start] = 1;
            start++;
        }

        for (int i = 2 ; i <= N ; i++) {
            int prevSqrt = (int) sqrt(i);
            for (int j = 1 ; j <= prevSqrt ; j++) {
                dp[i] = min(dp[j * j] + dp[i - (j * j)], dp[i]);
            }
        }
        System.out.println(dp[N]);
    }
}