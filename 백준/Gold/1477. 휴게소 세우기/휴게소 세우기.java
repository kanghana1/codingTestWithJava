import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int highway = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 2];
        arr[0] = 0;
        arr[N + 1] = highway;
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.distinct - a.distinct);
        for (int i = 1 ; i <= N + 1 ; i++) {
            pq.add(new Node( arr[i - 1], arr[i], 0));
        }

        solve(pq, M);
    }

    private static void solve(PriorityQueue<Node> pq, int m) {
        for (int i = 0 ; i < m ; i++) {
            Node node = pq.poll();
            pq.add(new Node(node.start, node.end, node.distinctCnt + 1));
        }
        Node node = pq.poll();
        System.out.println(node.distinct);
    }
}

class Node {
    int start;
    int end;
    int distinctCnt;
    int distinct;
    public Node(int s, int e, int cnt) {
        this.start = s;
        this.end = e;
        this.distinctCnt = cnt;
        this.distinct = (int) Math.ceil((double)(e - s) / (cnt + 1));
    }
}
