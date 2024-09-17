import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int blue = 0;
    static int white = 0;
    static int[][] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        input = new int[N + 1][N + 1];

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= N ; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(1, 1, N, N);
        sb.append(white).append("\n").append(blue);
        System.out.println(sb.toString());
    }

    private static void find(int startY, int startX, int endY, int endX) {
        int centerY = (startY + endY) / 2;
        int centerX = (startX + endX) / 2;
        if (!isfull(startY, startX, endY, endX)) {
            find(startY, startX, centerY, centerX);
            find(startY, centerX + 1, centerY, endX);
            find(centerY + 1, startX, endY, centerX);
            find(centerY + 1, centerX + 1, endY, endX);
        }
    }

    private static boolean isfull(int startY, int startX, int endY, int endX) {
        int point = 0;
        for (int i = startY ; i <= endY ; i++) {
            for (int j = startX ; j <= endX ; j++) {
                if (i == startY && j == startX) point = input[i][j];
                else {
                    if (input[i][j] != point) return false;
                }
            }
        }
        if (point == 1) blue++;
        else white++;
        return true;
    }
}