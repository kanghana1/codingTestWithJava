import java.io.*;
import java.util.*;
public class Main {
    static List<List<Node>> inputs = new ArrayList<>();
    static int[] dist;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        for (int i = 0 ; i <= N ; i++) {
             inputs.add(new ArrayList<>());
        }

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            inputs.get(s).add(new Node(e, w));
        }

        StringBuilder sb = new StringBuilder();
        dijkstra(start);
        for (int i = 1 ; i <= N ; i++) {
            if (dist[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            if (dist[curNode.end] < curNode.value) {
                continue;
            }
            for(int i = 0 ; i < inputs.get(curNode.end).size() ; i++) {
                Node next = inputs.get(curNode.end).get(i);
                if (curNode.value + next.value < dist[next.end]) {
                    dist[next.end] = curNode.value + next.value;
                    queue.add(new Node(next.end, dist[next.end]));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int end;
    int value;

    public Node(int e, int v) {
        this.end = e;
        this.value = v;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }
}