import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        postfix(0);
        System.out.println(sb.toString());
    }

    private static int postfix(int startIdx) {
        Stack<Character> stk = new Stack<>();
        boolean mult = false;
        int idx = startIdx;
        while (idx < input.length()) {
            char obj = input.charAt(idx);
            if (range(obj)) {
                sb.append(obj);
                if (mult) {
                    sb.append(stk.pop());
                    mult = false;
                }
            } else if (obj == '(') {
                idx = postfix(idx + 1);
            } else if (obj == ')') {
                break;
            } else {
                if (obj == '*' || obj == '/') {
                    if (!stk.isEmpty() && (stk.peek() == '*' || stk.peek() == '/')) sb.append(stk.pop());
                    stk.push(obj);
                    mult = true;
                } else {
                    if (stk.isEmpty()) stk.push(obj);
                    else {
                        while (!stk.isEmpty()) {
                            sb.append(stk.pop());
                        }
                        stk.push(obj);
                    }
                }
            }
            idx++;
        }
        if (!stk.isEmpty()) {
            while (!stk.isEmpty()) {
                sb.append(stk.pop());
            }
        }
        return idx;
    }

    private static boolean range(int idx) {
        return idx >= 65 && idx <= 90;
    }
}