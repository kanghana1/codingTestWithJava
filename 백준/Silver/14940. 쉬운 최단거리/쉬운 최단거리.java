import java.io.*;
import java.util.*;
public class Main {
    static int[][] inputs;
    static boolean[][] isVisit;
    static int[][] outputs;
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, 1, 0, -1};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inputs = new int[N + 1][M + 1];
        isVisit = new boolean[N + 1][M + 1];
        outputs = new int[N + 1][M + 1];

        int startY = 0;
        int startX = 0;
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= M ; j++) {
                int a = Integer.parseInt(st.nextToken());
                inputs[i][j] = a;
                outputs[i][j] = -1;
                if (a == 0) outputs[i][j] = 0;
                if (a == 2) {
                    startY = i;
                    startX = j;
                    outputs[i][j] = 0;
                }
            }
        }

        find(startY, startX, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                sb.append(outputs[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void find(int y, int x, int dis) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(y, x, dis));
        isVisit[y][x] = true;
        while(!queue.isEmpty()) {
            Node nowNode = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                if (canGo(nowNode.y + dy[i], nowNode.x + dx[i]) && inputs[nowNode.y + dy[i]][nowNode.x + dx[i]] != 0) {
                    outputs[nowNode.y + dy[i]][nowNode.x + dx[i]] = nowNode.distance + 1;
                    queue.add(new Node(nowNode.y + dy[i], nowNode.x + dx[i], nowNode.distance + 1));
                    isVisit[nowNode.y + dy[i]][nowNode.x + dx[i]] = true;
                }
                if (canGo(nowNode.y + dy[i], nowNode.x + dx[i]) && inputs[nowNode.y + dy[i]][nowNode.x + dx[i]] == 0) {
                    outputs[nowNode.y + dy[i]][nowNode.x + dx[i]] = 0;
                }
            }
        }
    }
    private static boolean canGo(int i, int j) {
        return i >= 1 && i <= N && j >= 1 && j <= M && !isVisit[i][j];
    }
}
class Node {
    int y;
    int x;
    int distance;
    public Node(int y, int x, int dis) {
        this.x = x;
        this.y = y;
        this.distance = dis;
    }
}