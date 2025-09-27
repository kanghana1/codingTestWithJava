import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 16637
public class Main {
    static String comp = "";

    static int N;
    static long ans = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        comp = br.readLine();
        comp(1, comp.charAt(0) - '0');
        System.out.println(ans);
    }

    private static void comp(int idx, long prev) {
        if (idx >= N) {
            ans = Math.max(ans, prev);
            return;
        }

        String op = String.valueOf(comp.charAt(idx));
        long next = 0;
        if (idx + 2 < N) {
            String nextOp = String.valueOf(comp.charAt(idx + 2));
            if (nextOp.equals("+")) next = (comp.charAt(idx + 1) - '0') + (comp.charAt(idx + 3) - '0');
            else if (nextOp.equals("-")) next = (comp.charAt(idx + 1) - '0') - (comp.charAt(idx + 3) - '0');
            else next = (comp.charAt(idx + 1) - '0') * (comp.charAt(idx + 3) - '0');

            if (op.equals("+")) {
                comp(idx + 4, prev + next);
            } else if (op.equals("-")) {
                comp(idx + 4, prev - next);
            } else {
                comp(idx + 4, prev * next);
            }
        }
        if (op.equals("+")) {
            comp(idx + 2, prev + (comp.charAt(idx + 1) - '0'));
        } else if (op.equals("-")) {
            comp(idx + 2, prev - (comp.charAt(idx + 1) - '0'));
        } else {
            comp(idx + 2, prev * (comp.charAt(idx + 1) - '0'));
        }
    }
}
