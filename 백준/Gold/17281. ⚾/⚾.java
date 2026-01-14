import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] array;
    static int N;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fillArray(N, br);

        int[] orders = new int[9];
        decideOrder(orders, 0);
        System.out.println(ans);
    }

    private static void decideOrder(int[] orders, int turn) {
        if (turn == 9) {
            start(orders);
            return;
        }

        if (turn == 3) {
            orders[3] = 0;
            decideOrder(orders, turn + 1);
        }
        for (int i = 0 ; i < 9 ; i++) {
            if (i == 0) continue;
            if (includable(i, turn, orders)) {
                orders[turn] = i;
                decideOrder(orders, turn + 1);
            }
        }
    }

    private static boolean includable(int idx, int turn, int[] orders) {
        for(int i = 0 ; i < turn ; i++) {
            if (orders[i] == idx) return false;
        }
        return true;
    }

    private static void start(int[] orders) { // index: 순서, value: 타자
        int sum = 0;
        int turn = 0;
        for (int inning = 0 ; inning < N ; inning++) {
            int out = 0;
            int score = 0;
            boolean[] inBase = new boolean[4];
            while (out < 3) {
                int player = orders[turn % 9];
                switch (array[inning][player]) {
                    case 0: // 아웃
                        out++;
                        break;
                    case 1: // 안타
                        if (inBase[3]) score++;
                        inBase[3] = inBase[2];
                        inBase[2] = inBase[1];
                        inBase[1] = true;
                        break;
                    case 2: // 2루타
                        if (inBase[3]) score++;
                        if (inBase[2]) score++;
                        inBase[3] = inBase[1];
                        inBase[2] = true;
                        inBase[1] = false;
                        break;
                    case 3: // 3루타
                        if (inBase[3]) score++;
                        if (inBase[2]) score++;
                        if (inBase[1]) score++;
                        inBase[3] = true;
                        inBase[2] = false;
                        inBase[1] = false;
                        break;
                    case 4: // 홈런
                        score++;
                        if (inBase[3]) score++;
                        if (inBase[2]) score++;
                        if (inBase[1]) score++;
                        inBase = new boolean[4];
                        break;
                }
                turn++;
            }
            sum += score;
        }
        ans = Math.max(ans, sum);
    }

    private static void fillArray(int n, BufferedReader br) throws IOException {
        array = new int[n][9];
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < 9 ; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

