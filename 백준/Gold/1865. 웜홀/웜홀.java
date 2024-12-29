import java.io.*;
import java.util.*;

public class Main {
    static List<Edge> lst = new ArrayList<>();
    static List<Integer> warm = new ArrayList<>();
    static final int INF = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0 ; t < T ; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            // 일반 노드 넣기
            for (int i = 0 ; i < M ; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                lst.add(new Edge(start, end, time));
                lst.add(new Edge(end, start, time));
            }
            // 웜홀 넣기
            for (int i = 0 ; i < W ; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                lst.add(new Edge(start, end, -time));
                warm.add(start);
            }
            boolean ans = false;
            for (int i = 0 ; i < W ; i++) {
                boolean possible = BF(N, M, warm.get(i));
                if (possible) {
                    ans = true;
                    break;
                }
            }
            sb.append(ans ? "YES\n" : "NO\n");
            lst.clear();
            warm.clear();
        }
        System.out.println(sb);
    }

    private static boolean BF(int n, int m, int dest) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[dest] = 0;
        for (int i = 1 ; i <= n ; i++) {
            for (Edge edge : lst) {
                if (dist[edge.start] == INF) continue;
                if (dist[edge.end] > dist[edge.start] + edge.time) {
                    dist[edge.end] = dist[edge.start] + edge.time;

                    if (i == n) return true;
                }
            }
        }
        return false;
    }
}

class Edge {
    int start;
    int end;
    int time;
    public Edge(int s, int e, int t) {
        this.start = s;
        this.end = e;
        this.time = t;
    }
}