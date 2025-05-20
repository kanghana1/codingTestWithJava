import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] isVisit;
    static int[][] inputs;
    static int[][] melt;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        inputs = new int[N][M];
        isVisit = new boolean[N][M];
        melt = new int[N][M];

        for (int i =  0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int islandCnt = 0;
        int turn = 0;
        while (true) {
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < M ; j++) {
                    if (inputs[i][j] != 0 && !isVisit[i][j]) {
                        bfs(i, j);
                        islandCnt++;
                    }
                    if (islandCnt >= 2) {
                        System.out.println(turn);
                        return;
                    }

                }
            }
            if (islandCnt == 0) {
                System.out.println(0);
                return;
            }
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < M ; j++) {
                    if (melt[i][j] != 0) {
                        if (inputs[i][j] > melt[i][j]) inputs[i][j] -= melt[i][j];
                        else inputs[i][j] = 0;
                        melt[i][j] = 0;
                    }
                }
            }

            islandCnt = 0;
            turn++;
            isVisit = new boolean[N][M];
        }
    }

    private static void bfs(int y, int x) {
        Queue<Node> queue = new ArrayDeque<>();
        Node node = new Node(y, x);
        queue.add(node);
        isVisit[y][x] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            meltIce(now.y, now.x);
            for (int i = 0; i < 4 ; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (canGo(ny, nx) && !isVisit[ny][nx] && inputs[ny][nx] > 0) {
                    queue.add(new Node(ny, nx));
                    isVisit[ny][nx] = true;
                }
            }
        }
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs[0].length;
    }

    private static void meltIce(int y, int x) {
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (canGo(ny,nx)) {
                if (inputs[ny][nx] == 0) {
                    melt[y][x]++;
                }
            }
        }
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