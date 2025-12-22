import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {

    static Map<Integer, Integer> operands = new HashMap<>();
    static int[] numbers;
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        String nums = br.readLine();
        String ops = br.readLine();
        setting(nums, ops);
        backtracking(1, numbers[0]);
        System.out.println(max + "\n" + min);
    }

    private static void backtracking(int idx, int value) {
        if (idx == numbers.length) {
            if (value > max) max = value;
            if (value < min) min = value;
            return;
        }
        int num = numbers[idx];
        if (operands.get(0) > 0) {
            operands.put(0, operands.get(0) - 1);
            backtracking(idx + 1, value + num);
            operands.put(0, operands.get(0) + 1);
        }
        if (operands.get(1) > 0) {
            operands.put(1, operands.get(1) - 1);
            backtracking(idx + 1, value - num);
            operands.put(1, operands.get(1) + 1);
        }
        if (operands.get(2) > 0) {
            operands.put(2, operands.get(2) - 1);
            backtracking(idx + 1, value * num);
            operands.put(2, operands.get(2) + 1);
        }
        if (operands.get(3) > 0) {
            operands.put(3, operands.get(3) - 1);
            backtracking(idx + 1, value / num);
            operands.put(3, operands.get(3) + 1);
        }
    }

    private static void setting(String nums, String ops) {
        StringTokenizer st = new StringTokenizer(nums);
        for (int i = 0 ; i < numbers.length ; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(ops);
        for (int i = 0 ; i < 4 ; i++) {
            operands.put(i, Integer.valueOf(st.nextToken()));
        }
    }
}

