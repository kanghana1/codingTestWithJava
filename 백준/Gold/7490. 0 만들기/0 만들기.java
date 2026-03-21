import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < T ; i++) {
            int N = Integer.parseInt(br.readLine());
            findExpression(2,"1", N);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void findExpression(int now, String nowString, int N) {
        if (now > N) {
            if (calculate(nowString) == 0) {
                sb.append(nowString).append("\n");
            }
            return;
        }

        findExpression(now + 1, nowString + " " + now, N);
        findExpression(now + 1, nowString + "+" + now, N);
        findExpression(now + 1, nowString + "-" + now, N);
    }

    private static int calculate(String nowString) {
        String replace = nowString.replace(" ", "");
        String[] split = replace.split("[+-]");
        int idx = 1;
        int sum = Integer.parseInt(split[0]);
        for (int i = 0 ; i < replace.length() ; i++) {
            char c = replace.charAt(i);
            if (c == '+') {
                sum += Integer.parseInt(split[idx++]);
            } else if (c == '-') {
                sum -= Integer.parseInt(split[idx++]);
            }
        }
        return sum;
    }
}
