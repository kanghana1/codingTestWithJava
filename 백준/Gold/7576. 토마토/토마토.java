import java.util.*;
import java.io.*;

public class Main {
    static int[][] maze;
    static boolean[][] isVisit;
    static int[][] distance;
    static int rNum;
    static int cNum;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{-1, 0, 1, 0}; // 동 남 서 북
    static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        cNum = Integer.parseInt(st.nextToken());
        rNum = Integer.parseInt(st.nextToken());

        maze = new int[rNum][cNum];
        isVisit = new boolean[rNum][cNum];
        distance = new int[rNum][cNum];

        for (int i = 0 ; i < rNum ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < cNum ; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                if (maze[i][j] == 1) {
                    queue.add(new int[] {i, j});
                    isVisit[i][j] = true;
                    distance[i][j] = 1;
                } else if (maze[i][j] == -1) {
                    isVisit[i][j] = true; // 벽인 경우 방문한 것으로 처리
                }
            }
        }

        bfs();

        int max = 0;
        boolean cannot = false;
        for (int i = 0 ; i < rNum ; i++) {
            for (int j = 0 ; j < cNum ; j++) {
                if (maze[i][j] == 0 && !isVisit[i][j]) { // 방문하지 않은 0이 있는지 확인
                    cannot = true;
                }
                if (max < distance[i][j]) max = distance[i][j];
            }
        }
        if (cannot) max = 0;
        System.out.println(max - 1);
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int presentY = poll[0];
            int presentX = poll[1];

            for (int i = 0 ; i < 4 ; i++) {
                int yIdx = presentY + dy[i];
                int xIdx = presentX + dx[i];
                if (check(yIdx, xIdx) && !isVisit[yIdx][xIdx] && maze[yIdx][xIdx] == 0) {
                    queue.add(new int[] {yIdx, xIdx});
                    isVisit[yIdx][xIdx] = true;
                    distance[yIdx][xIdx] = distance[presentY][presentX] + 1;
                }
            }
        }
    }

    public static boolean check(int y, int x) {
        return y >= 0 && y < rNum && x >= 0 && x < cNum;
    }
}
