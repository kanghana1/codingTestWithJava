import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 구해야하는 횟수
        int[][] origin = new int[N + 1][N + 1];
        int[][] arr = new int[N + 1][N + 1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= N ; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = arr[i][j - 1] + origin[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            int sum = 0;

            for (int y = y1 ; y <= y2 ; y++) {
                sum += (arr[y][x2] - arr[y][x1 - 1]);
            }
            bw.write(sum + "\n");
        }

        bw.flush();
        bw.close();
    }
}