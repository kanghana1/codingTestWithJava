import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 6549
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            long[] heights = new long[n];
            for (int i = 0 ; i < n ; i++) {
                heights[i] = Long.parseLong(st.nextToken());
            }

            long ans = findMaxWeight(heights);
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    private static long findMaxWeight(long[] heights) {
        long maxWeight = 0;
        Stack<Integer> histogram = new Stack<>();

        for (int i = 0 ; i < heights.length ; i++) {
            while (!histogram.isEmpty() && heights[histogram.peek()] > heights[i]) {
                int height = (int) heights[histogram.pop()];
                int width = histogram.isEmpty() ? i : i - histogram.peek() - 1;
                maxWeight = Math.max(maxWeight, (long) height * width);
            }
            histogram.push(i);
        }
        while (!histogram.isEmpty()) {
            int height = (int) heights[histogram.pop()];
            int width = histogram.isEmpty() ? heights.length : heights.length - histogram.peek() - 1;
            maxWeight = Math.max(maxWeight, (long) height * width);
        }

        return maxWeight;
    }
}
