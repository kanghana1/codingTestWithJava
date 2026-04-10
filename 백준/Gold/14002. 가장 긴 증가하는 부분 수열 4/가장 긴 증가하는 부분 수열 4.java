import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int[] parent = new int[N];
        Arrays.fill(parent, -1);
        dp[0] = 1;
        int ansIdx = 0;
        for (int i = 1 ; i < N ; i++) {
            dp[i] = 1;
            for (int j = i - 1;  j >= 0 ; j--) {
                if (arr[i] > arr[j]) {
                    if (dp[i] < dp[j] + 1) {
                        parent[i] = j;
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (dp[ansIdx] < dp[i]) {
                ansIdx = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[ansIdx]).append("\n");
        Stack<Integer> stack = new Stack<>();
        while (ansIdx != -1) {
            stack.push(arr[ansIdx]);
            ansIdx = parent[ansIdx];
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
