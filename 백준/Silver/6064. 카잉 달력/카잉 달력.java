import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < T ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int ans = solve(N, M, x, y);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int n, int m, int x, int y) {
        for (int i = x ; i <= n * m ; i += n) {
            if (i < y) continue;
            if ((i - y) % m == 0) return i;
        }
        return -1;
    }
}
