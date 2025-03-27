import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] inputs;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int startY = -1, startX = 0, endY = -1, endX = 0;
        inputs = new int[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char ch = str.charAt(j);
                if (ch == '*') inputs[i][j] = 1;
                else if (ch == 'C') {
                    if (startY == -1) {
                        startY = i;
                        startX = j;
                    } else {
                        endY = i;
                        endX = j;
                    }
                }
            }
        }

        int ans = find(startY, startX, endY, endX);
        System.out.println(ans);
    }

    private static int find(int startY, int startX, int endY, int endX) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[][][] isVisit = new boolean[inputs.length][inputs[0].length][4];
        int[][] dp = new int[inputs.length][inputs[0].length];

        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[startY][startX] = 0;

        for (int i = 0; i < 4; i++) {
            queue.add(new Node(startY, startX, i, 0));
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.y == endY && node.x == endX) return node.cnt;

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (canGo(ny, nx) && inputs[ny][nx] == 0) {
                    int nextCnt = node.cnt + (node.dir == i ? 0 : 1);
                    if (!isVisit[ny][nx][i] || nextCnt < dp[ny][nx]) {
                        isVisit[ny][nx][i] = true;
                        dp[ny][nx] = nextCnt;
                        queue.add(new Node(ny, nx, i, nextCnt));
                    }
                }
            }
        }
        return -1; // 도달 불가능한 경우
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs[0].length;
    }
}

class Node implements Comparable<Node> {
    int y, x, dir, cnt;

    public Node(int y, int x, int dir, int cnt) {
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) {
        return this.cnt - o.cnt;
    }
}
