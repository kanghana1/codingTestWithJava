import java.util.*;
import java.io.*;
public class Main {
    static int[][] arr;
    static int ans = 0;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                block1(i, j);
                block2(i, j);
            }
        }
        System.out.print(ans);
    }

    public static void block1(int y, int x) {
        int sum = arr[y][x];
        if (canGo(y + 1, x) && canGo(y, x + 1)) {
            sum += (arr[y + 1][x] + arr[y][x + 1]);
            ans = Math.max(ans, sum);
        }
        sum = arr[y][x];
        if (canGo(y + 1, x) && canGo(y, x - 1)) {
            sum += (arr[y + 1][x] + arr[y][x - 1]);
            ans = Math.max(ans, sum);
        }
        sum = arr[y][x];
        if (canGo(y - 1, x) && canGo(y, x - 1)) {
            sum += (arr[y - 1][x] + arr[y][x - 1]);
            ans = Math.max(ans, sum);
        }
        sum = arr[y][x];
        if (canGo(y - 1, x) && canGo(y, x + 1)) {
            sum += (arr[y - 1][x] + arr[y][x + 1]);
            ans = Math.max(ans, sum);
        }
        sum = arr[y][x];

    }

    public static void block2(int y, int x) {
        int sum = 0;
        // 세로
        if (canGo(y, x) && canGo(y + 1, x) && canGo(y + 2, x)) {
            sum += (arr[y][x] + arr[y + 1][x] + arr[y + 2][x]);
            ans = Math.max(ans, sum);
        }

        sum = 0;
        // 가로
        if (canGo(y, x) && canGo(y, x + 1) && canGo(y, x + 2)) {
            sum += (arr[y][x] + arr[y][x + 1] + arr[y][x + 2]);
            ans = Math.max(ans, sum);
        }
    }

    public static boolean canGo(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

}