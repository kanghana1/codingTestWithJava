import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> lst = new ArrayList<>();
    static List<List<Integer>> tree = new ArrayList<>();
    static int N;
    static boolean[] visit;
    static int[] count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        count = new int[N + 1];
        visit = new boolean[N + 1];
        int root = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }
        for (int i = 0 ; i < N - 1 ; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            lst.get(v1).add(v2);
            lst.get(v2).add(v1);
        }
        makeTree(root);
        find(root);
        for (int i = 0 ; i < Q ; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(count[query]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void find(int root) {
        count[root] = 1;
        if (tree.get(root).isEmpty()) return;
        for (int node : tree.get(root)) {
            find(node);
            count[root] += count[node];
        }
    }
    private static void makeTree(int root) {
        visit[root] = true;
        for (int child : lst.get(root)) {
            if (!visit[child]) {
                tree.get(root).add(child);
                makeTree(child);
            }
        }
    }
}