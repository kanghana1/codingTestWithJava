import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = fillMap(N, K);
        System.out.println(map[N][K] % 1000000000);

    }

    private static int[][] fillMap(int N, int K) {
        int[][] map = new int[N + 1][K + 1];
        for (int i = 1 ; i <= K ; i++) {
            map[0][i] = 1;
        }
        
        for (int i = 1 ; i <= N ; i++) {
            map[i][1] = 1;
        }

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= K ; j++) {
                map[i][j] = (map[i - 1][j] % 1000000000) + (map[i][j - 1] % 1000000000);
            }
        }

        return map;
    }
}
