import java.io.*;
import java.util.*;
public class Main {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int blen = Long.toBinaryString(B).length();
        dp = new long[blen + 1]; // 1 ~ 2^N까지 누적합

        dp[0] = 1;
        for (int i = 1 ; i <= blen ; i++) {
            dp[i] = (dp[i - 1] << 1) + (1L << i);
        }

        long ans = sum(B) - sum(A - 1);
        System.out.println(ans);
    }

    private static long sum(long b) {
        long cnt = b & 1; // b의 1의 개수
        int size = (int) (Math.log(b) / Math.log(2));
        for (int i = size ; i > 0 ; i--) {
            if ((b & (1L << i)) != 0L) {
                cnt += dp[i - 1] + (b - (1L << i) + 1);
                b -= (1L << i);
            }
        }
        return cnt;
    }
}
