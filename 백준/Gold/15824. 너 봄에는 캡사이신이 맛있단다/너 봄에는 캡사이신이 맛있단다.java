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
        fillArray(arr, br);

        long ans = 0;
        for (int gap = 1 ; gap < N; gap++) {
            int cntBetween = gap - 1;
            long cntCase = exp(cntBetween);
            long sum = 0;
            for (int i = 0 ; i < N - gap; i++) {
                int j = i + gap;
                sum += (arr[j] - arr[i]);
            }
            ans = (ans + (sum % MOD) * cntCase % MOD) % MOD;
        }
        System.out.println(ans % MOD);
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