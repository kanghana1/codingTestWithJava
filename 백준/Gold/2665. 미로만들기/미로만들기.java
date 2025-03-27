import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] maze;
    static int[] dy = new int[]{0, 1, 0, -1}; // 오른 아래 왼 위
    static int[] dx = new int[]{1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        maze = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            String input = br.readLine();
            for (int j = 0 ; j < N ; j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }
        int ans = dijkstra(N);
        System.out.println(ans);
    }

    private static int dijkstra(int size) {
        int ans = 2501;
        Queue<Node> queue = new PriorityQueue<>();
        boolean[][] isVisit = new boolean[maze.length][maze.length];
        queue.add(new Node(0, 0, 0));
        isVisit[0][0] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.y == size - 1 && node.x == size - 1) {
                ans = Math.min(ans, node.cnt);
                continue;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (canGo(ny, nx) && !isVisit[ny][nx]) {
                    if (maze[ny][nx] == 1) {
                        queue.add(new Node(ny, nx, node.cnt));
                        isVisit[ny][nx] = true;
                    } else {
                        queue.add(new Node(ny, nx, node.cnt + 1));
                        isVisit[ny][nx] = true;
                    }
                }

            }
        }
        return ans;
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < maze.length && x >= 0 && x < maze.length;
    }
}
class Node implements Comparable<Node> {
    int y;
    int x;
    int cnt;
    public Node(int y, int x, int c) {
        this.y = y;
        this.x = x;
        this.cnt = c;
    }

    @Override
    public int compareTo(Node o) {
        return this.cnt - o.cnt;
    }
}
