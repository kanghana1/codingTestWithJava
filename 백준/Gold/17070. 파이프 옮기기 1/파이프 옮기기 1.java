import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17070
public class Main {
    static int N;
    static boolean[][] canGo;
    static final int WIDTH = 0;
    static final int HEIGHT = 1;
    static final int CROSS = 2;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        canGo = new boolean[N + 1][N + 1];
        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= N ; j++) {
                canGo[i][j] = Integer.parseInt(st.nextToken()) == 0;
            }
        }

        movePipe(1, 2, WIDTH);
        System.out.println(ans);
    }

    private static void movePipe(int y, int x, int type) {
        if (y == N && x == N) {
            ans++;
            return;
        }

        if (moveWidth(y, x, type)) movePipe(y, x + 1, WIDTH);
        if (moveHeight(y, x, type)) movePipe(y + 1, x, HEIGHT);
        if (moveCross(y, x)) movePipe(y + 1, x + 1, CROSS);
    }

    private static boolean moveWidth(int y, int x, int type) {
        if (type == HEIGHT) return false;
        if (canGO(y, x + 1)) return true;
        return false;
    }

    private static boolean moveHeight(int y, int x, int type) {
        if (type == WIDTH) return false;
        if (canGO(y + 1, x)) return true;
        return false;
    }

    private static boolean moveCross(int y, int x) {
        if (canGO(y + 1, x) && canGO(y + 1, x + 1) && canGO(y, x + 1)) return true;
        return false;
    }

    private static boolean canGO(int y, int x) {
        return y >= 1 && y <= N && x >= 1 && x <= N && canGo[y][x];
    }
}
