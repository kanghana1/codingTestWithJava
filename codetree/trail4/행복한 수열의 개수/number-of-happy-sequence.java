import java.util.*;
import java.io.*;
public class Main {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if (M == 1) {
            System.out.print(2 * N);
            return;
        }

        arr = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = find(N, M);
        System.out.print(ans);
    }

    public static int find(int N, int M) {
        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            if (col(i, N, M)) cnt++;
            if (row(i, N, M)) cnt++;
        }

        return cnt;
    }

    public static boolean row(int y, int N, int M) {
        int prev = arr[y][0];
        int cnt = 1;
        for (int i = 1 ; i < N ; i++) {
            if (arr[y][i] == prev) {
                cnt++;
                if (cnt == M) return true;
            } else {
                prev = arr[y][i];
                cnt = 1;
            }
        }

        return false;
    }

    public static boolean col(int x, int N, int M) {
        int prev = arr[0][x];
        int cnt = 1;
        for (int i = 1 ; i < N ; i++) {
            if (arr[i][x] == prev) {
                cnt++;
                if (cnt == M) return true;
            } else {
                prev = arr[i][x];
                cnt = 1;
            }
        }
        return false;
    }

}