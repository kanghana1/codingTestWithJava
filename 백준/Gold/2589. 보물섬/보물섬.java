import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static boolean[][] isVisit;
    static String[][] map;
    static int[][] ans;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        map = new String[r + 1][c + 1];
        ans = new int[r + 1][c + 1];

        for (int i = 1 ; i <= r ; i++) {
            String str = br.readLine();
            for (int j = 1 ; j <= c ; j++) {
                map[i][j] = String.valueOf(str.charAt(j - 1));
            }
        }

        for (int i = 1 ; i <= r ; i++) {
            for (int j = 1 ; j <= c ; j++) {
                if (map[i][j].equals("L")) {
                    int[][] dist = bfs(i, j);
                    fill(dist);
                }
            }
        }
        System.out.println(max);
    }

    private static void fill(int[][] dist) {
        for (int i = 1 ; i < map.length ; i++) {
            for (int j = 1 ; j < map[0].length ; j++) {
                ans[i][j] = max(ans[i][j], dist[i][j]);
                max = max(ans[i][j], max);
            }
        }
    }

    private static int[][] bfs(int y, int x) {
        isVisit = new boolean[map.length][map[0].length];
        int[][] dist = new int[map.length][map[0].length];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(y, x, 0));
        isVisit[y][x] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nowY = now.y + dy[i];
                int nowX = now.x + dx[i];
                int d = now.dist + 1;
                if (canGo(nowY, nowX)) {
                    if (dist[nowY][nowX] == 0) {
                        dist[nowY][nowX] = d; // 최단거리 저장
                    }
                    queue.add(new Node(nowY, nowX, d));
                    isVisit[nowY][nowX] = true;
                }
            }
        }
        return dist;
    }

    private static boolean canGo(int y, int x) {
        return y > 0 && y < map.length && x > 0 && x < map[0].length && !isVisit[y][x] && map[y][x].equals("L");
    }
}
class Node {
    int y;
    int x;
    int dist;
    public Node(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }
}
