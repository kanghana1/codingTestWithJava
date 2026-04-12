import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String numStr = br.readLine();

        StringBuilder sb = new StringBuilder();
        Queue<int[]> largeNum = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return a[1] - b[1];
        });
        for (int i = 0 ; i < K ; i++) {
            largeNum.add(new int[]{numStr.charAt(i) - '0', i});
        }

        int idx = -1;
        for (int i = K ; i < N ; i++) {
            largeNum.add(new int[]{numStr.charAt(i) - '0', i});

            while (!largeNum.isEmpty() && largeNum.peek()[1] < idx) {
                largeNum.poll();
            }
            int[] node = largeNum.poll();
            sb.append(node[0]);
            idx = node[1];
        }

        System.out.println(sb);
    }
}
