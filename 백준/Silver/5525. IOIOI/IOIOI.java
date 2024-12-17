import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int len = Integer.parseInt(br.readLine());
        int cnt = 0;
        String str = br.readLine();
        String goal = makeStr(N);
        for (int i = 0 ; i <= len - (2 * N + 1) ; i++) {
            if (str.charAt(i) == 'I') {
                if (str.substring(i, i + (2 * N + 1)).equals(goal)) cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static String makeStr(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("I");
        for (int i = 0 ; i < n ; i++) {
            sb.append("OI");
        }
        return sb.toString();
    }
}