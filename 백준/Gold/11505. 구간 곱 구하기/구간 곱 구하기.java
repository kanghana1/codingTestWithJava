import java.io.*;
import java.util.*;
public class Main {
    static long[] inputs;
    final static long DIV = 1000000007;
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
                update(tree, 1, 1, N, b, c);
                inputs[b] = c;
            } else {
                long mult = mult(tree, 1, 1, N, b, c);
                sb.append(mult).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long makeTree(long[] tree, int now, int start, int end) {
        if (start == end) {
            return tree[now] = inputs[start];
        }
        return tree[now] = (makeTree(tree, now * 2, start, (start + end) / 2)
                * makeTree(tree, now * 2 + 1, (start + end) / 2 + 1, end)) % DIV;
    }

    private static long update(long[] tree, int now, int start, int end, int goalIdx, long after) {
        if (start > goalIdx || end < goalIdx) {
            return tree[now];
        }
        if (start == end) return tree[now] = after;
        return  tree[now] = (update(tree, now * 2, start, (start + end) / 2, goalIdx, after) *
        update(tree, now * 2 + 1, (start + end) / 2 + 1, end, goalIdx, after)) % DIV;
    }

    private static long mult(long[] tree, int now, int start, int end, int left, long right) {
        if (left > end || right < start) return 1;

        if (right >= end && start >= left) {
            return tree[now];
        }

        return (mult(tree, now * 2, start, (start + end) / 2, left, right) *
                mult(tree, now * 2 + 1, (start + end) / 2 + 1, end, left, right)) % DIV;

    }
}