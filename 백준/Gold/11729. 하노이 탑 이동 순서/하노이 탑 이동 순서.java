import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        hanoi(1, 3, N);
        System.out.println(cnt);
        System.out.println(sb);
    }

    private static void hanoi(int start, int end, int N) {
        if (N == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            cnt++;
            return;
        }
        hanoi(start, 6 - start - end, N - 1);
        sb.append(start).append(" ").append(end).append("\n");
        cnt++;
        hanoi(6 - start - end, end, N - 1);
    }
}
