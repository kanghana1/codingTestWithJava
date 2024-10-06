import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] inputs = new int[N + 1];
        int[] sum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        sum[1] = inputs[1];
        for (int i = 2 ; i <= N ; i++) {
            sum[i] = sum[i - 1] + inputs[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int startIdx = Integer.parseInt(st.nextToken());
            int endIdx = Integer.parseInt(st.nextToken());
            sb.append(sum[endIdx] - sum[startIdx - 1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}