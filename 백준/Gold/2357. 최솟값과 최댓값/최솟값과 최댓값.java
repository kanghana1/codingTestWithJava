import java.io.*;
import java.util.*;
public class Main {
    static int[] inputs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int cases = Integer.parseInt(st.nextToken());
        inputs = new int[N + 1];

        for (int i = 1 ; i <= N ; i++) {
            int obj = Integer.parseInt(br.readLine());
            inputs[i] = obj;
        }
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int[] maxTree = new int[(int) Math.pow(2, h + 1) + 1];
        int[] minTree = new int[(int) (Math.pow(2, h + 1) + 1)];
        makeMaxTree(maxTree, 1, 1, N);
        makeMinTree(minTree, 1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < cases ; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int max = seekMax(maxTree, 1, 1, N, left, right);
            int min = seekMin(minTree, 1, 1, N, left, right);
            sb.append(min + " " + max).append("\n");
        }
        System.out.println(sb);
    }
    private static int makeMinTree(int[] segmentTree, int now, int start, int end) {
        if (start == end) {
            return segmentTree[now] = inputs[start];
        }
        return segmentTree[now] =
                Math.min(makeMinTree(segmentTree, now * 2, start, (start + end) / 2),
                        makeMinTree(segmentTree, now * 2 + 1, (start + end) / 2 + 1, end));
    }

    private static int makeMaxTree(int[] segmentTree, int now, int start, int end) {
        if (start == end) {
            return segmentTree[now] = inputs[start];
        }
        return segmentTree[now] =
                Math.max(makeMaxTree(segmentTree, now * 2, start, (start + end) / 2),
                        makeMaxTree(segmentTree, now * 2 + 1, (start + end) / 2 + 1, end));
    }

    private static int seekMax(int[] tree, int now, int start, int end, int left, int right) {
        if (left > end || right < start) return -1;

        if (left <= start && right >= end) return tree[now];

        return Math.max(seekMax(tree, now * 2, start, (start + end) / 2, left, right),
                seekMax(tree, now * 2 + 1, (start + end) / 2 + 1, end, left, right));
    }

    private static int seekMin(int[] tree, int now, int start, int end, int left, int right) {
        if (left > end || right < start) return 1000000001;

        if (left <= start && right >= end) return tree[now];

        return Math.min(seekMin(tree, now * 2, start, (start + end) / 2, left, right),
                seekMin(tree, now * 2 + 1, (start + end) / 2 + 1, end, left, right));
    }
}