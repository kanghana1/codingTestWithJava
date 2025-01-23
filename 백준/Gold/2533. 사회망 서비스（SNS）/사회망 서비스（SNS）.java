import java.io.*;
import java.util.*;
// 2533
public class Main {
    static List<List<Integer>> lst = new ArrayList<>();

    static boolean[] canInfo;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        canInfo = new boolean[N + 1];
        dp = new int[N + 1][2];
        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }

        for (int i = 0 ; i < N - 1 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            lst.get(u).add(v);
            lst.get(v).add(u);
        }
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int now) {
        canInfo[now] = true;
        dp[now][1] = 1;

        for (int child : lst.get(now)) {
            if (!canInfo[child]) {
                dfs(child);
                dp[now][0] += dp[child][1];
                dp[now][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}
