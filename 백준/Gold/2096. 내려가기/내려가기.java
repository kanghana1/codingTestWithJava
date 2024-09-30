import java.io.*;
import java.util.*;
public class Main {
    static int[] move = new int[]{-1, 0, 1};
    static int[][] inputs;
    static int[][] minDp;
    static int[][] maxDp;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        inputs = new int[N + 1][3];
        minDp = new int[N + 1][3];
        maxDp = new int[N + 1][3];

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < 3 ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
                minDp[i][j] = Integer.MAX_VALUE;
                maxDp[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = 0 ; i < 3 ; i++) {
            minDp[1][i] = inputs[1][i];
            maxDp[1][i] = inputs[1][i];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxDp()).append(" ").append(minDp());
        System.out.println(sb.toString());
    }

    private static int minDp() {
        for (int i = 2 ; i <= N ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                for (int k = 0 ; k < 3 ; k++) {
                    if (check(j + move[k]) && minDp[i][j] > minDp[i - 1][j + move[k]] + inputs[i][j]) {
                        minDp[i][j] = minDp[i - 1][j + move[k]] + inputs[i][j];
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0 ; i < 3 ; i++) {
            if (ans > minDp[N][i]) ans = minDp[N][i];
        }
        return ans;
    }

    private static int maxDp() {
        for (int i = 2 ; i <= N ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                for (int k = 0 ; k < 3 ; k++) {
                    if (check(j + move[k]) && maxDp[i][j] < maxDp[i - 1][j + move[k]] + inputs[i][j]) {
                        maxDp[i][j] = maxDp[i - 1][j + move[k]] + inputs[i][j];
                    }
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0 ; i < 3 ; i++) {
            if (ans < maxDp[N][i]) ans = maxDp[N][i];
        }
        return ans;
    }

    private static boolean check(int idx) {
        return idx >= 0 && idx < 3;
    }
}