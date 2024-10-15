import java.io.*;
import java.util.*;
public class Main {
    static int[][] map;
    static boolean[][][] isVisit;
    static int[] dx = {1, 0, -1, 0}; // 동 남 서 북
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static int M;
    static int sol = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        isVisit = new boolean[N + 1][M + 1][2];

        for (int i = 1 ; i <= N ; i++) {
            String s = br.readLine();
            for (int j = 1 ; j <= M ; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j - 1)));
            }
        }

        bfs(1, 1, 1);
        if (sol == Integer.MAX_VALUE) sol = -1;
        System.out.println(sol);
    }

    private static void bfs(int y, int x, int chance) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(y, x, true, 1));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int nowY = now.y;
            int nowX = now.x;
            boolean nowChance = now.chance;
            int nowDis = now.distance;
//            System.out.println("y : " + nowY + " x : " + nowX + " / chane : " + nowChance + " / dis : " + nowDis);
            if (nowY == N && nowX == M) {
                sol = Math.min(sol, nowDis);
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                if (canGo(nowY + dy[i], nowX + dx[i])) {
                    if (nowChance) {
                        if (isVisit[nowY + dy[i]][nowX + dx[i]][0]) continue;
                        // 벽 안 부숴본 애들
                        if (map[nowY + dy[i]][nowX + dx[i]] == 1) {
                            queue.add(new Node(nowY + dy[i], nowX + dx[i], false, nowDis + 1));
                            isVisit[nowY + dy[i]][nowX + dx[i]][1] = true;
                        } else {
                            queue.add(new Node(nowY + dy[i], nowX + dx[i], true, nowDis + 1));
                            isVisit[nowY + dy[i]][nowX + dx[i]][0] = true;
                        }
                    } else {
                        // 벽 부숴본 애들
                        if (isVisit[nowY + dy[i]][nowX + dx[i]][1]) continue;
                        if (map[nowY + dy[i]][nowX + dx[i]] == 0) {
                            queue.add(new Node(nowY + dy[i], nowX + dx[i], false, nowDis + 1));
                            isVisit[nowY + dy[i]][nowX + dx[i]][1] = true;
                        }
                    }
                }
            }

        }
    }

    private static boolean canGo(int y, int x) {
        return y > 0 && x > 0 && y <= N && x <= M;
    }
}

class Node {
    int y;
    int x;
    boolean chance;
    int distance;

    public Node(int y, int x, boolean chance, int distance) {
        this.y = y;
        this.x = x;
        this.chance = chance;
        this.distance = distance;
    }
}