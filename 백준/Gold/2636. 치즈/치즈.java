import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] inputs;
    static int total = 0;
    static boolean[][] isVisit;
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};
    static Queue<int[]> prevCheck = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        inputs = new int[r][c];
        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < c ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
                if (inputs[i][j] == 1) total++;
            }
        }
        isVisit = new boolean[r][c];
        int prevCnt = total;
        aircheckFirst();
        int cnt = 1;
        while (total > 0) {
            prevCnt = total;
            aircheck();
            cnt++;
        }
        System.out.println(cnt + "\n" + prevCnt);
    }

    private static void aircheck() {
        Queue<int[]> next = new ArrayDeque<>();
        while(!prevCheck.isEmpty()) {
            int[] nowIdx = prevCheck.poll();
            for (int i = 0 ; i < 4 ; i++) {
                if (canGo(nowIdx[0] + dy[i], nowIdx[1] + dx[i])) {
                    if (inputs[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] == 1) {
                        isVisit[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] = true;
                        inputs[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] = 0;
                        next.add(new int[]{nowIdx[0] + dy[i], nowIdx[1] + dx[i]});
                        total--;
                    } else {
                        prevCheck.add(new int[]{nowIdx[0] + dy[i], nowIdx[1] + dx[i]});
                        isVisit[nowIdx[0] + dy[i]][nowIdx[1] + dx[i]] = true;
                    }
                }
            }
        }
        prevCheck = next;
    }

    private static void aircheckFirst() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        isVisit[0][0] = true;
        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int y = idx[0];
            int x = idx[1];
            for (int i = 0 ; i < 4 ; i++) {
                if (canGo(y + dy[i], x + dx[i]))
                    if (inputs[y + dy[i]][x + dx[i]] == 1) {
                        isVisit[y + dy[i]][x + dx[i]] = true;
                        inputs[y + dy[i]][x + dx[i]] = 0;
                        prevCheck.add(new int[]{y + dy[i], x + dx[i]});
                        total--;
                    } else {
                        isVisit[y + dy[i]][x + dx[i]] = true;
                        queue.add(new int[]{y + dy[i], x + dx[i]});
                    }
            }
        }
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs[0].length && !isVisit[y][x];
    }
}
