import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<List<int[]>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        initList(N, M, br);
        int ans = explore(N);
        System.out.println(ans);
    }

    private static int explore(int N) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int cost = now[1];

            if (cost > dist[node]) continue;

            for (int[] next : list.get(node)) {
                int nextNode = next[0], nextCost = next[1];
                if (dist[node] + nextCost < dist[nextNode]) {
                    dist[nextNode] = dist[node] + nextCost;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        return dist[N];
    }

    private static void initList(int n, int m, BufferedReader br) throws IOException {
        for (int i = 0 ; i <= n ; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0 ; i < m ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());
            list.get(a).add(new int[]{b, cow});
            list.get(b).add(new int[]{a, cow});
        }
    }
}
