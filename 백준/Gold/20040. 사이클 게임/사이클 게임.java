import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for (int i = 0 ; i < N ; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if (find(v1) != find(v2)) {
                union(v1, v2);
            } else {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    private static int find(int v) {
        if (v != parent[v]) parent[v] = find(parent[v]);
        return parent[v];
    }

    private static void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        parent[min(r1, r2)] = max(r1, r2);
    }
}