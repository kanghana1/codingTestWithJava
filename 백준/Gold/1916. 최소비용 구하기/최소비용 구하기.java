import java.io.*;
import java.util.*;
public class Main {
    static List<List<Node>> list = new LinkedList<>();
    static boolean[] isVisit;
    static int[] weight;
    static int end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        isVisit = new boolean[N + 1];
        weight = new int[N + 1];

        // 리스트 생성
        for (int i = 0 ; i <= N ; i++) {
            list.add(new LinkedList<>());
            weight[i] = Integer.MAX_VALUE;
        }

        // 값 대입
        for (int i = 1 ; i <= M ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            list.get(key).add(node);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start));
    }

    private static int dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>();
        weight[start] = 0;
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();
            int now = nowNode.end;

            if (!isVisit[now]) {
                isVisit[now] = true;
                List<Node> nowList = list.get(now);
                for (Node node : nowList) {
                    if (!isVisit[node.end] && weight[node.end] > weight[now] + node.weight) {
                        weight[node.end] = weight[now] + node.weight;
                        queue.add(new Node(node.end, weight[node.end]));
                    }
                }
            }
        }
        return weight[end];
    }
}

class Node implements Comparable<Node> {
    int end;
    int weight;
    
    Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}