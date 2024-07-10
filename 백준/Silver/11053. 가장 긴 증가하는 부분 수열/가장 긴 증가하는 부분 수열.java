import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0 ; i < n ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int result = 1;
        for (int i = 1 ; i < n ; i++) {
            for (int j = 0 ; j < i ; j++) {
                if (input[i] > input[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            result = Math.max(dp[i], result);
        }
        System.out.println(result);
    }
}