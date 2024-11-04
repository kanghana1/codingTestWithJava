import java.io.*;
import java.util.*;

public class Main {
    static int[] root;
    static int[][] inputs;
    static int edgeCnt = 0;
    static int price = 0;
    static int max = Integer.MIN_VALUE;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        inputs = new int[M][3];
        root = new int[N + 1];
        for (int i = 1 ; i <= N ; i++) {
            root[i] = i;
        }
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            inputs[i][0] = Integer.parseInt(st.nextToken());
            inputs[i][1] = Integer.parseInt(st.nextToken());
            inputs[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        kruskal();
        System.out.println(price - max);
    }

    private static void kruskal() {
        int idx = 0;
        while (true) {
            if (edgeCnt == N - 1) break;
            int parent = inputs[idx][0];
            int child = inputs[idx][1];
            int weight = inputs[idx][2];
            if (find(parent) != find(child)) {
                union(parent, child);
                if (max < weight) max = weight;
                price += weight;
                edgeCnt++;
            }
            idx++;
        }
    }

    private static int find(int a) {
        if (a != root[a]) root[a] = find(root[a]);
        return root[a];
    }

    private static void union(int p, int c) {
        int root1 = find(p);
        int root2 = find(c);
        root[root2] = root1;
    }
}