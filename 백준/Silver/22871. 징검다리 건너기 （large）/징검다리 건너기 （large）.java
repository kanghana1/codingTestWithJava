import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {
    static int[] inputs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        inputs = new int[N];
        long[] dp = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1 ; i < N ; i++) {
            dp[i] = Long.MAX_VALUE;
            for (int j = 0 ; j < i ; j++) {
                dp[i] = min(dp[i], max(power(i, j), dp[j]));
            }
        }

        System.out.println(dp[N - 1]);
    }

    public static long power(int big, int small) {
        return (long) (big - small) * (1 + abs(inputs[big] - inputs[small]));
    }
}

