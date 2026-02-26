import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] blocksHeight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        blocksHeight = new int[W];
        for (int i = 0 ; i < W ; i++) {
            blocksHeight[i] = Integer.parseInt(st.nextToken());
        }

        int ans = totalSum(blocksHeight);
        System.out.println(ans);
    }

    private static int totalSum(int[] blocksHeight) {
        Stack<Integer> stack = new Stack<>(); // 인덱스 저장
        int sum = 0;
        for (int i = 0; i < blocksHeight.length; i++) {
            while (!stack.isEmpty() && blocksHeight[stack.peek()] < blocksHeight[i]) {
                int mid = stack.pop();
                if (stack.isEmpty()) break;

                int left = stack.peek();
                int height = Math.min(blocksHeight[left], blocksHeight[i]) - blocksHeight[mid];
                int width = i - left - 1;
                sum += height * width;
            }
            stack.push(i);
        }

        return sum;
    }
}
