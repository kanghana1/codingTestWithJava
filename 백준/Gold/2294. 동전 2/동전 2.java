import java.io.*;
import java.util.*;
import static java.lang.Math.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int sum = Integer.parseInt(st.nextToken());
        int[] inputs = new int[N];
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(inputs);
        int[] dp = new int[100001];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for (int i = 0 ; i < inputs.length ; i++) {
            dp[inputs[i]] = 1;
        }

        for (int i = 1 ; i <= sum ; i++) {
            for (int j = inputs.length - 1 ; j >= 0 ; j--) {
                if (i > inputs[j]) {
                    dp[i] = Math.min(dp[i], dp[i - inputs[j]] + 1);
                }
            }
        }
        System.out.println(dp[sum] == 100001 ? -1 : dp[sum]);
    }
}