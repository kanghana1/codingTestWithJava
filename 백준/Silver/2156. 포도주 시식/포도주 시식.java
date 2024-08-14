import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] inputs = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1 ; i <= n ; i++) {
            inputs[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) dp[1] = inputs[1];
        else if (n == 2) dp[2] = inputs[1] + inputs[2];
        else {
            dp[1] = inputs[1];
            dp[2] = inputs[1] + inputs[2];
            for (int i = 3 ; i <= n ; i++) {
                dp[i] = max(max(dp[i - 3] + inputs[i - 1] + inputs[i], dp[i - 2] + inputs[i]), dp[i - 1]);
            }
        }
        System.out.println(dp[n]);
    }
}