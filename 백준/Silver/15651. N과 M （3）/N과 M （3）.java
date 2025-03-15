import java.io.*;
import java.util.*;

public class Main {
    static int max;
    static int size;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        max = Integer.parseInt(st.nextToken());
        size = Integer.parseInt(st.nextToken());

        dfs(0, new int[size]);
        System.out.println(sb);
    }

    private static void dfs(int cnt, int[] arr) {
        if (cnt == size) {
            for (int i = 0 ; i < arr.length ; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1 ; i <= max ; i++) {
            arr[cnt] = i;
            dfs(cnt + 1, arr);
        }
    }
}