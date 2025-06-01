import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;

    static int[] dice = new int[7];
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dice[6] = map[startY][startX];

        // 0, 0, 0, 0, 0, 0 -> 위 앞 오 왼 뒤 아래
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < K ; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (now == EAST) {
                if (!canGo(startY, startX + 1)) continue;
                startX++;
                goEast();
                mapCheck(startY, startX);
            } else if (now == WEST) {
                if (!canGo(startY, startX - 1)) continue;
                startX--;
                goWest();
                mapCheck(startY, startX);
            } else if (now == SOUTH) {
                if (!canGo(startY + 1, startX)) continue;
                startY++;
                goSouth();
                mapCheck(startY, startX);
            } else if (now == NORTH) {
                if (!canGo(startY - 1, startX)) continue;
                startY--;
                goNorth();
                mapCheck(startY, startX);
            }
            sb.append(dice[1] + "\n");
        }
        System.out.println(sb);
    }

    private static void mapCheck(int y, int x) {
        if (map[y][x] == 0) {
            map[y][x] = dice[6];
        } else {
            dice[6] = map[y][x];
            map[y][x] = 0;
        }
    }

    private static void goNorth() {
        int swap = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[2];
        dice[2] = swap;
    }

    private static void goSouth() {
        int swap = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[5];
        dice[5] = swap;
    }

    private static void goWest() {
        int swap = dice[1];
        dice[1] = dice[3];
        dice[3] = dice[6];
        dice[6] = dice[4];
        dice[4] = swap;
    }

    private static void goEast() {
        int swap = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[3];
        dice[3] = swap;
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < map.length && x >= 0 && x < map[0].length;
    }
}
