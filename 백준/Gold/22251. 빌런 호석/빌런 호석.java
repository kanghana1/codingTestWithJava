import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {
    static boolean[][] display;
    static int N;
    static int K;
    static int P;
    public static void main(String[] args) throws IOException {
        fillDisplay();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최대층수
        K = Integer.parseInt(st.nextToken()); // 최대자릿수
        P = Integer.parseInt(st.nextToken()); // 최대 변경개수
        int X = Integer.parseInt(st.nextToken()); // 현재층수

        int ans = findAnswer(X);
        System.out.println(ans);
    }

    private static int findAnswer(int floor) {
        List<int[]> changes = new ArrayList<>();
        int nowFloor = floor;
        for (int i = 1 ; i <= K ; i++) {
            int num = nowFloor % 10;
            int[] howMuchChanged = howMuchChanged(num);
            changes.add(howMuchChanged);
            nowFloor /= 10;
        }
        int cnt = 0;
        for (int f = 1 ; f <= N ; f++) {
            if (f == floor) continue;
            int changedCnt = 0;
            int nf = f;
            for (int j = 1 ; j <= K ; j++) {
                int num = nf % 10;
                changedCnt += changes.get(j - 1)[num];
                nf /= 10;
            }

            if (changedCnt <= P) {
                cnt++;
            }
        }

        return cnt;
    }

    private static int[] howMuchChanged(int num) {
        int[] changed = new int[10];
        for (int i = 0 ; i < 10 ; i++) {
            int cnt = 0;
            for (int j = 1 ; j <= 7 ; j++) {
                if (display[num][j] != display[i][j]) {
                    cnt++;
                }
            }
            changed[i] = cnt;
        }
        return changed;
    }

    private static void fillDisplay() {
        display = new boolean[][]{{false, true, true, true, false, true, true, true},
                {false, false, false, true, false, false, true, false},
                {false, true, false, true, true, true, false, true},
                {false, true, false, true, true, false, true, true},
                {false, false, true, true, true, false, true, false},
                {false, true, true, false, true, false, true, true},
                {false, true, true, false, true, true, true, true},
                {false, true, false, true, false, false, true, false},
                {false, true, true, true, true, true, true, true},
                {false, true, true, true, true, false, true, true}};
    }
}
