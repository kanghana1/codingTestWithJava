import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        long[] exp = new long[N];
        fillArray(arr, br);
        fillExp(exp, N);

        long ans = 0;
        for (int j = 0; j < N; j++) {
            long coeff = (exp[j] - exp[N - 1 - j] + MOD) % MOD;
            ans = (ans + coeff * arr[j]) % MOD;
        }
        System.out.println(ans % MOD);
    }

    private static void fillExp(long[] exp, int N) {
        exp[0] = 1;
        for (int i = 1; i < N; i++) {
            exp[i] = exp[i - 1] * 2 % MOD;
        }
    }

    private static long exp(int n) {
        long ans = 1;
        for (int i = 0; i < n; i++) {
            ans = (ans * 2) % MOD;
        }
        return ans;
    }

    private static void fillArray(int[] arr, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
    }
}