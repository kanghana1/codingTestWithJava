import java.io.*;
import java.util.*;
public class Main {
    static long[] inputs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());
        int cases = Integer.parseInt(st.nextToken());
        inputs = new long[N + 1];
        for (int i = 1 ; i <= N ; i++) {
            inputs[i] = Long.parseLong(br.readLine());
        }

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        long[] tree = new long[(int) (Math.pow(2, h + 1) + 1)];
        makeTree(tree, 1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < change + cases ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(tree, 1, 1, N, b, c - inputs[b]);
                inputs[b] = c;
            } else {
                long sum = sum(tree, 1, 1, N, b, c);
                sb.append(sum).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long makeTree(long[] tree, int now, int start, int end) {
        if (start  == end) {
            return tree[now] = inputs[start];
        }

        return tree[now] = makeTree(tree, now * 2, start, (start + end) / 2) +
                makeTree(tree, now * 2 + 1, (start + end) / 2 + 1, end);
    }

    private static void update(long[] tree, int now, int start, int end, int goalIdx, long diff) {
        if (goalIdx < start || goalIdx > end) return;

        tree[now] += diff;
        if (end != start) {
            update(tree, now * 2, start, (start + end) / 2, goalIdx, diff);
            update(tree, now * 2 + 1, (start + end) / 2 + 1, end, goalIdx, diff);
        }

    }

    private static long sum(long[] tree, int now, int start, int end, int left, long right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[now];

        return sum(tree, now * 2, start, (start + end) / 2, left, right) +
                sum(tree, now * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }
}