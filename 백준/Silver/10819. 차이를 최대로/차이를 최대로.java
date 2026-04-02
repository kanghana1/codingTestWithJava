import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static boolean[] visited;
    static int[] arr;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visited = new boolean[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0 ; i < N ; i++) {
            visited[i] = true;
            solve(i, 0);
            visited[i] = false;
        }
        System.out.println(ans);
    }

    private static void solve(int idx, int sum) {
        if (allVisit()) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0 ; i < arr.length ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                solve(i, sum + Math.abs(arr[idx] - arr[i]));
                visited[i] = false;
            }
        }
    }

    private static boolean allVisit() {
        for (int i = 0 ; i < arr.length ; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
