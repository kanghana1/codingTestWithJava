import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] mods = fillArray(br, N, M);
        long ans = solve(mods, M);
        System.out.println(ans);
    }

    private static long solve(long[] arr, int M) {
        long cnt = 0;
        for (int i = 0 ; i < M ; i++) {
            if (arr[i] > 1) {
                cnt += (arr[i] * (arr[i] - 1)) / 2;
            }
        }
        return cnt;
    }

    private static long[] fillArray(BufferedReader br, int n, int m) throws IOException {
        int[] arr = new int[n];
        long[] mods = new long[m];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sums = new int[n];
        sums[0] = arr[0] % m;
        mods[sums[0]]++;
        for (int i = 1 ; i < n ; i++) {
            sums[i] = (sums[i - 1] + arr[i]) % m;
            mods[sums[i]]++;
        }
        mods[0]++;
        return mods;
    }
}