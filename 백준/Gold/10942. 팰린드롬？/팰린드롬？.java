import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] inputs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        inputs = new int[N + 1];
        for (int i = 1 ; i <= N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            palindrome(start, end);
        }
        System.out.println(sb.toString());
    }

    private static void palindrome(int start, int end) {
        for (int i = 0 ; i <= (end - start) / 2 ; i++) {
            if (inputs[start + i] != inputs[end - i]) {
                sb.append(0).append("\n");
                return;
            }
        }
        sb.append(1).append("\n");
    }
}