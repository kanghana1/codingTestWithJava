import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static int[][] floyd;
    static int INF = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        floyd = new int[N + 1][N + 1];

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (i == j) {
                    floyd[i][j] = 0;
                } else {
                    floyd[i][j] = INF;
                }
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            floyd[a][b] = 1;
            floyd[b][a] = 1;
        }

        relation();
        int min = INF;
        int idx = 0;
        for (int i = 1 ; i <= N ; i++) {
            int sum = 0;
            for (int j = 1 ; j <= N ; j++) {
                if (floyd[i][j] != INF) {
                    sum += floyd[i][j];
                }
            }
            if (min > sum) {
                min = sum;
                idx = i;
            }
        }

        System.out.println(idx);
    }

    private static void relation() {
        for (int i = 1 ; i < floyd.length ; i++) {
            for (int j = 1 ; j < floyd.length ; j++) {
                for (int k = 1 ; k < floyd.length ; k++) {
                    floyd[j][k] = Math.min(floyd[j][k], floyd[j][i] + floyd[i][k]);
                }
            }
        }
    }
}
