import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] maps;
    static int[] papers = new int[]{0, 5, 5, 5, 5, 5};
    private static final int SIZE = 10;
    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        fillMaps();
        calculator(0);
        if (ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    private static void calculator(int usedPaper) {
        if (usedPaper >= ans) return;

        int y = -1;
        int x = -1;
        for (int i = 0 ; i < SIZE ; i++) {
            for (int j = 0 ; j < SIZE ; j++) {
                if (maps[i][j]) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y != -1) break;
        }

        if (y == -1) {
            ans = Math.min(ans, usedPaper);
            return;
        }

        explore(y, x, usedPaper);
    }

    private static void explore(int y, int x, int usedPaper) {
        for (int i = 5 ; i >= 1 ; i--) {
            if (papers[i] > 0) {
                if (checkFill(y, x, i)) {
                    fill(y, x, i);
                    papers[i]--;
                    calculator(usedPaper + 1);
                    unfill(y, x, i);
                    papers[i]++;
                }
            }
        }
    }


    private static void fill(int y, int x, int size) {
        for (int i = y ; i < y + size ; i++) {
            for (int j = x ; j < x + size ; j++) {
                maps[i][j] = false;
            }
        }
    }

    private static void unfill(int y, int x, int size) {
        for (int i = y ; i < y + size ; i++) {
            for (int j = x ; j < x + size ; j++) {
                maps[i][j] = true;
            }
        }
    }


    private static boolean checkFill(int y, int x, int size) {
        if (y + size - 1 >= SIZE || x + size - 1 >= SIZE) return false;
        for (int i = y ; i < y + size ; i++) {
            for (int j = x ; j < x + size ; j++) {
                if (!maps[i][j]) return false;
            }
        }
        return true;
    }

    private static void fillMaps() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maps = new boolean[SIZE][SIZE];
        for (int i = 0 ; i < SIZE ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < SIZE ; j++) {
                maps[i][j] = (Integer.parseInt(st.nextToken()) == 1);
            }
        }
    }
}

