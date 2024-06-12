import java.util.*;
import java.io.*;


public class Main {
    static int[][] maze;
    static boolean[][] isVisit;
    static int rNum;
    static int cNum;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{-1, 0, 1, 0}; // 동 남 서 북
    static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rNum = Integer.parseInt(st.nextToken());
        cNum = Integer.parseInt(st.nextToken());

        maze = new int[rNum][cNum];
        isVisit = new boolean[rNum][cNum];

        for (int i = 0 ; i < rNum ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < cNum ; j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        System.out.println(bfs(0,0));

    }
    public static int bfs(int y, int x) {
        int[][] distance = new int[rNum][cNum];
        queue.add(new int[] {y, x});
        isVisit[y][x] = true;
        distance[y][x] = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int presentY = poll[0];
            int presentX = poll[1];

            if (presentY == rNum - 1 && presentX == cNum - 1) return distance[presentY][presentX];

            for (int i = 0 ; i < 4 ; i++) {
                int yIdx = presentY + dy[i];
                int xIdx = presentX + dx[i];
                if (check(yIdx, xIdx) && !isVisit[yIdx][xIdx] && maze[yIdx][xIdx] == 1) {
                    queue.add(new int[] {yIdx, xIdx});
                    isVisit[yIdx][xIdx] = true;
                    distance[yIdx][xIdx] = distance[presentY][presentX] + 1;
                }
            }
        }
        return 0;
    }
    public static boolean check(int y, int x) {
        return y >= 0 && y < rNum && x >= 0 && x < cNum;
    }
}
