import java.io.*;

public class Main {
    static int recursive = 1;
    static int dpCnt = 0;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        fib(N);
        fibonacci(N);
        System.out.println(recursive + " " + dpCnt);
    }

    public static int fib(int n) {
        if (n == 1 || n == 2) return 1;
        else {
            recursive++;
            return fib(n - 1) + fib(n - 2);
        }
    }
    public static int fibonacci(int n) {
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3 ; i <= n ; i++) {
            dpCnt++;
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}