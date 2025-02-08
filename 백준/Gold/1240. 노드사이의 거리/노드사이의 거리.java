import java.io.*;
import java.util.*;

public class Main {
    static List<List<Node>> lst = new ArrayList<>();
    static boolean[] isVisit;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }
        for (int i = 0 ; i < N - 1 ; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            lst.get(n1).add(new Node(n2, dist));
            lst.get(n2).add(new Node(n1, dist));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < M ; i++) {
            isVisit = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dfs(start, end, 0);
            sb.append(ans).append("\n");
            ans = 0;
        }
        System.out.print(sb);
    }

    private static void dfs(int start, int end, int dist) {
        isVisit[start] = true;
        if (start == end) {
            ans = dist;
            return;
        }
        List<Node> child = lst.get(start);
        if (child.isEmpty()) return;
        for (Node node : child) {
            if (!isVisit[node.val]) {
               dfs(node.val, end, dist + node.dist);
            }
        }
    }
}
class Node {
    int val;
    int dist;

    public Node(int v, int d) {
        this.val = v;
        this.dist = d;
    }

}