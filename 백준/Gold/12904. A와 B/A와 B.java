import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        boolean isPossible = false;
        sb.append(T);
        // 뒤에가 A냐 B냐에 따라 함수 사용
        while (S.length() <= sb.length()) {
            if (S.length() == sb.length()) {
                boolean isTrue = true;
                for (int i = 0 ; i < S.length() ; i++) {
                    if (S.charAt(i) != sb.charAt(i)) {
                        isTrue = false;
                        break;
                    }
                }
                if (isTrue) isPossible = true;
                break;
            }

            if (sb.charAt(sb.length() - 1) == 'A') {
                func1();
            } else {
                func2();
            }
        }

        System.out.println(isPossible ? 1 : 0);
    }

    private static void func1() {
        sb.delete(sb.length() - 1, sb.length());
    }

    private static void func2() {
        sb.delete(sb.length() - 1, sb.length());
        sb.reverse();
    }
}