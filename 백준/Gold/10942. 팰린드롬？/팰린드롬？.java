import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] inputs;
    static boolean[][] dp;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        inputs = new int[N + 1];
        dp = new boolean[N + 1][N + 1];

        for (int i = 1 ; i <= N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        dp();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(dp[start][end] ? 1 : 0).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dp() {
        for (int i = 1 ; i <= N ; i++) {
            // 시작, 끝 idx 같은경우 -> 길이가 1
            dp[i][i] = true;
        }

        for (int i = 1 ; i < N ; i++) {
            // 길이가 2인경우 -> 값이 같으면 true
            if (inputs[i] == inputs[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // 그 이상 -> 안쪽거가 팰린드롬이고, 각 사이드의 값이 같으면 팰린드롬
        // 두 수의 간격이 작은 애들부터 채우기
        for (int i = 2 ; i < N ; i++) {
            for (int j = 1 ; j <= N - i ; j++) {
                if (inputs[j] == inputs[j + i] && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }
    }
}