import java.io.*;
import java.util.*;

public class Main {
    static int[][] inputs;
    static int edges = 0;
    static int mst_sum = 0;
    static int N;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        inputs = new int[E][3];
        parent = new int[N + 1];

        for (int i = 1 ; i <= N ; i++) {
            parent[i] = i;
        };

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            inputs[i][0] = v1;
            inputs[i][1] = v2;
            inputs[i][2] = weight;
        }
        Arrays.sort(inputs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        kruskal();
        System.out.println(mst_sum);
    }

    private static void kruskal() {
        int idx = 0;
        while (true) {
            if (edges == N - 1) break;
            int parent = inputs[idx][0];
            int child = inputs[idx][1];
            int weight = inputs[idx][2];

            if (find(parent) != find(child)) {
                union(parent, child);
                mst_sum += weight;
                edges++;
            }
            idx++;
        }
    }

    private static void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        parent[root2] = root1;
    }

    private static int find(int node) {
        if (node != parent[node]) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
}