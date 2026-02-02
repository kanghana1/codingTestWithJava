import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static List<int[]>[] graph;
    static int[] dp;
    static int n;
    static int D;
    public static void main(String[] args) throws IOException {
        input();

        for (int i = 1 ; i <= D ; i++) {
            search(i);
        }
        System.out.println(dp[D]);

    }

    private static void search(int startIdx) {
        dp[startIdx] = Math.min(dp[startIdx], dp[startIdx - 1] + 1);
        if (graph[startIdx].size() > 0) {
            for (int[] edge : graph[startIdx]) {
                int start = edge[0];
                int dis = edge[1];
                dp[startIdx] = Math.min(dp[startIdx], dp[start] + dis);
            }
        }
    }


    private static void graphInit() {
        graph = new List[D + 1];
        for (int i = 0 ; i <= D ; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graphInit();


        dp = new int[D + 1];
        for (int i = 0 ; i <= D ; i++) {
            dp[i] = i;
        }
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            if (end <= D) {
                graph[end].add(new int[] {start, dis});
            }
        }

    }
}
