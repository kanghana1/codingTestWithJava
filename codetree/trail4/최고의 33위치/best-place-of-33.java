import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0 ; i <= N - 3 ; i++) {
            for (int j = 0 ; j <= N - 3 ; j++) {
                int ans = seek(arr, i, j);
                max = Math.max(max, ans);
            }
        }

        System.out.print(max);
    }

    public static int seek(int[][] arr, int y, int x) {
        int cnt = 0;
        for (int i = y ; i < y + 3 ; i++) {
            for (int j = x ; j < x + 3 ; j++) {
                if (arr[i][j] == 1) cnt++;
            }
        }

        return cnt;
    }
}