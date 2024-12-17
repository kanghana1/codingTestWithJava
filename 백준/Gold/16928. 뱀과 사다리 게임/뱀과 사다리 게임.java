import java.io.*;
import java.util.*;
public class Main {
    static Map<Integer, Integer> laddersAndSnakes = new HashMap<>();
    static int[] board;
    static boolean[] isVisit;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new int[101];
        isVisit = new boolean[101];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = 1;
            laddersAndSnakes.put(start, end);
        }
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = -1;
            laddersAndSnakes.put(start, end);
        }

        game(1, 0);
        System.out.println(ans);
    }

    private static void game(int start, int cnt) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, cnt));
        isVisit[start] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.now >= 100) {
                ans = node.cnt;
                return;
            }

            for (int i = 1 ; i <= 6 ; i++) {
                if (range(node.now + i)) {
                    if (board[node.now + i] == 0) {
                        // 아무것도 없는 칸
                        queue.add(new Node(node.now + i, node.cnt + 1));
                        isVisit[node.now + i] = true;
                    } else {
                        // 사다리 또는 뱀 있는 칸
                        int end = laddersAndSnakes.get(node.now + i);
                        queue.add(new Node(end, node.cnt + 1));
                        isVisit[node.now + i] = true;
                        isVisit[end] = true;
                    }
                }
            }
        }
    }

    private static boolean range(int i) {
        return i >= 1 && i <= 100 && !isVisit[i];
    }

}
class Node {
    public int now;
    public int cnt;
    public Node(int s, int c) {
        this.now = s;
        this.cnt = c;
    }
}