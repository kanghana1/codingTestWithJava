import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        String T = br.readLine();

        boolean possible = makeString(T);
        System.out.println(possible ? 1 : 0);
    }

    private static boolean makeString(String t) {
        if (t.equals(S)) return true;
        if (t.length() <= S.length()) return false;

        boolean first = false, second = false;
        if (t.charAt(t.length() - 1) == 'A') {
            first = makeString(firstOps(t));
        }

        if (t.charAt(0) == 'B'){
            second = makeString(secondOps(t));
        }

        return first || second;
    }

    private static String secondOps(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.delete(0, 1).reverse();
        return sb.toString();
    }

    private static String firstOps(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
