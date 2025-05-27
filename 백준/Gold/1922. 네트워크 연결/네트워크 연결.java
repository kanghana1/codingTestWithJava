import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Node>> graph = new ArrayList<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        for (int i = 0 ; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pc1 = Integer.parseInt(st.nextToken());
            int pc2 = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            graph.get(pc1).add(new Node(pc2, price));
            graph.get(pc2).add(new Node(pc1, price));
        }
        int ans = explore();
        System.out.println(ans);
    }

    private static int explore() {
        Queue<Node> queue = new PriorityQueue<>();
        boolean[] isVisit = new boolean[N + 1];
        int sum = 0;
        int edge = -1;
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (edge == N - 1) break;
            if (isVisit[node.dest]) continue;

            isVisit[node.dest] = true;
            sum += node.price;
            edge++;

            for (Node next : graph.get(node.dest)) {
                if (!isVisit[next.dest]) {
                    queue.add(next);
                }
            }
        }
        return sum;
    }
}

class Node implements Comparable<Node>{
    int dest;
    int price;
    public Node(int d, int p) {
        this.dest = d;
        this.price = p;
    }

    @Override
    public int compareTo(Node o) {
        return this.price - o.price;
    }
}