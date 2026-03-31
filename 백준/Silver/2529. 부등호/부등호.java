import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static char[] signs;
    static boolean[] visited;
    static boolean isMax = false;
    static boolean isMin = false;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        signs = new char[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            signs[i] = st.nextToken().charAt(0);
        }

        visited = new boolean[10];
        int[] nums = new int[K + 1];
        for (int i = 9 ; i >= 0 ; i--) {
            visited[i] = true;
            nums[0] = i;
            solveMax(i, 1, nums);
            visited[i] = false;
            if (isMax) break;
        }

        visited = new boolean[10];
        nums = new int[K + 1];
        for (int i = 0 ; i <= 9 ; i++) {
            visited[i] = true;
            nums[0] = i;
            solveMin(i, 1, nums);
            visited[i] = false;
            if (isMin) break;
        }

        System.out.println(sb);
    }

    private static void solveMin(int prevNum, int idx, int[] nums) {
        if (idx == nums.length) {
            Arrays.stream(nums)
                .forEach(num -> sb.append(num));
            isMin = true;
            return;
        }
        if (signs[idx - 1] == '>') {
            for (int i = 0 ; i < prevNum ; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                nums[idx] = i;
                solveMin(i, idx + 1, nums);
                visited[i] = false;
                if (isMin) break;
            }

        } else if (signs[idx - 1] == '<') {
            for (int i = prevNum + 1 ; i <= 9 ; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                nums[idx] = i;
                solveMin(i, idx + 1, nums);
                visited[i] = false;
                if (isMin) break;
            }
        }
    }

    private static void solveMax(int prevNum, int idx, int[] nums) {
        if (idx == signs.length + 1) {
            Arrays.stream(nums)
                    .forEach(num -> sb.append(num));
            sb.append("\n");
            isMax = true;
            return;
        }

        if (signs[idx - 1] == '>') {
            for (int i = prevNum - 1 ; i >= 0 ; i--) {
                if (visited[i]) continue;
                visited[i] = true;
                nums[idx] = i;
                solveMax(i, idx + 1, nums);
                visited[i] = false;
                if (isMax) break;
            }

        } else if (signs[idx - 1] == '<') {
            for (int i = 9 ; i > prevNum ; i--) {
                if (visited[i]) continue;
                visited[i] = true;
                nums[idx] = i;
                solveMax(i, idx + 1, nums);
                visited[i] = false;
                if (isMax) break;
            }
        }
    }
}
