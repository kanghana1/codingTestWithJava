import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] tops;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tops = new int[N + 1];
        Stack<Integer> stack = new Stack<>();
        for (int i = 1 ; i <= N ; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= N ; i++) {
            while (!stack.isEmpty() && tops[stack.peek()] < tops[i]) {
                stack.pop();
            }
            sb.append(stack.isEmpty() ? 0 : stack.peek()).append(" ");
            stack.push(i);
        }

        System.out.println(sb);
    }
}
