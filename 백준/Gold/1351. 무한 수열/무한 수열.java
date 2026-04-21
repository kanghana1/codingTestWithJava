import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static Map<Long, Double> ans = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        if (N == 0) {
            System.out.println(1);
            return;
        }

        if (N == 1) {
            System.out.println(2);
            return;
        }
        ans.put(0L, 1.0);
        ans.put(1L, 2.0);

        solve(P, Q, N);
        System.out.println(ans.get(N).longValue());
    }

    private static double solve(int P, int Q, long N) {
        if (N == 0) return 1;
        if (N == 1) return 2;

        if (ans.containsKey(N)) return ans.get(N);

        double result = solve(P, Q, N / P) + solve(P, Q, N / Q);
        ans.put(N, result);
        return result;
    }
}