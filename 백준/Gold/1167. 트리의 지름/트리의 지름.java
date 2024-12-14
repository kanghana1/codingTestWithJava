import java.io.*;
import java.util.*;
// 1167
public class Main {
    static List<List<Node>> lst = new ArrayList<>();
    static boolean[] isVisit;
    static int max = 0;
    static int further = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        isVisit = new boolean[N + 1];
        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startN = Integer.parseInt(st.nextToken());
            while (true) {
                int endN = Integer.parseInt(st.nextToken());
                if (endN == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                lst.get(startN).add(new Node(endN, dist));
            }
        }
        isVisit[1] = true;
        dfs(1, 0);
        isVisit = new boolean[N + 1];
        isVisit[further] = true;
        dfs(further, 0);
        System.out.println(max);
    }

    private static void dfs(int start, int dis) {
        if (dis > max) {
            max = dis;
            further = start;
        }
        List<Node> children = lst.get(start);
        for (Node child : children) {
            if (!isVisit[child.node]) {
                isVisit[child.node] = true;
                dfs(child.node, dis + child.dist);
            }
        }
    }
}

class Node {
    int node;
    int dist;

    public Node(int n, int d) {
        this.node = n;
        this.dist = d;
    }
}