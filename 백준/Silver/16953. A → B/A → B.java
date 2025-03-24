import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int goal = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        System.out.println(bfs(start, goal));
    }

    private static int bfs(int start, int goal) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int val = node.val;
            if (val < goal) return -1;
            if (val == goal) {
                return node.cnt;
            } else if (val % 2 == 0) {
                queue.add(new Node(val / 2, node.cnt + 1));
            } else if (val % 10 == 1) {
                queue.add(new Node((val - 1) / 10, node.cnt + 1));
            } else {
                return -1;
            }
        }
        return -1;
    }
}

class Node {
    int val;
    int cnt;
    public Node(int v, int c) {
        this.val = v;
        this.cnt = c;
    }
}
