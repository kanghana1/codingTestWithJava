import java.io.*;
import java.util.*;
public class Main {
    static int[] dx = new int[]{1, 0, -1, 0}; // 동 남 서 북
    static int[] dy = new int[]{0, -1, 0, 1};
    static boolean[][] isWall;
    static boolean[][] isVisit;
    static int Y;
    static int X;
    static List<Integer> space = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        isWall = new boolean[Y + 1][X + 1];
        isVisit = new boolean[Y + 1][X + 1];

        for (int t = 0 ; t < K ; t++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + 1;
            int y1 = Integer.parseInt(st.nextToken()) + 1;
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = y1 ; i <= y2 ; i++) {
                for (int j = x1 ; j <= x2 ; j++) {
                    isWall[i][j] = true;
                }
            }
        }

        for (int i = 1 ; i <= Y ; i++) {
            for (int j = 1 ; j <= X ; j++) {
                if (!isWall[i][j] && !isVisit[i][j]) {
                    int cnt = start(i, j);
                    space.add(cnt);
                }
            }
        }
        Collections.sort(space);
        StringBuilder sb = new StringBuilder();
        sb.append(space.size()).append("\n");
        for (int a : space) sb.append(a).append(" ");

        System.out.println(sb);
    }

    private static int start(int y, int x) {
        int cnt = 1;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(y, x));
        isVisit[y][x] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                if (canGo(node.y + dy[i], node.x + dx[i])) {
                    queue.add(new Node(node.y + dy[i], node.x + dx[i]));
                    isVisit[node.y + dy[i]][node.x + dx[i]] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean canGo(int i, int j) {
        return i > 0 && i <= Y && j > 0 && j <= X && !isVisit[i][j] && !isWall[i][j];
    }
}
class Node {
    int y;
    int x;
    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}