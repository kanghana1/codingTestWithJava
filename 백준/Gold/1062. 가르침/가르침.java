import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<String> lst = new ArrayList<>();
    static boolean[] alphabet;
    static int ans = 0;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        alphabet = new boolean[26];
        if (M < 5) {
            System.out.println(0);
            return;
        }
        alphabet['a' - 'a'] = true;
        alphabet['c' - 'a'] = true;
        alphabet['t' - 'a'] = true;
        alphabet['n' - 'a'] = true;
        alphabet['i' - 'a'] = true;

        M -= 5;
        for (int i = 0 ; i < N ; i++) {
            String s = br.readLine();
            lst.add(s.substring(4, s.length() - 4));
        }

        backtracking(0, 0);
        System.out.println(ans);
    }

    private static void backtracking(int idx, int cnt) {
        if (cnt == M) {
            readableCnt();
            return;
        }

        for (int i = idx ; i < 26 ; i++) {
            if (!alphabet[i]) {
                alphabet[i] = true;
                backtracking(i + 1, cnt + 1);
                alphabet[i] = false;
            }
        }
    }

    private static void readableCnt() {
        int cnt = 0;
        for (String str : lst) {
            boolean canRead = true;
            for (int i = 0 ; i < str.length() ; i++) {
                if (!alphabet[str.charAt(i) - 'a']) {
                    canRead = false;
                    break;
                }
            }
            if (canRead) cnt++;
        }
        ans = Math.max(ans, cnt);
    }
}
