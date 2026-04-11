import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
    static int[][] now;
    static int[][] goal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        fillGraph(N, M, br);

        if (isSame()){
            System.out.println(0);
            return;
        }

        if (N < 3 || M < 3) {
            System.out.println(-1);
            return;
        }

        int ans = solve();
        System.out.println(ans);
    }

    private static int solve() {
        int count = 0;
        for (int i = 0 ; i < now.length - 2 ; i++) {
            for (int j = 0 ; j < now[0].length - 2 ; j++) {
                if (now[i][j] != goal[i][j]) {
                    count++;
                    for (int k = i ; k < i + 3 ; k++) {
                        for (int l = j ; l < j + 3 ; l++) {
                            now[k][l] = 1 - now[k][l];
                        }
                    }
                }
            }
        }

        if (!isSame()) {
            return -1;
        }

        return count;
    }

    private static boolean isSame() {
        for (int i = 0 ; i < now.length ; i++) {
            for (int j = 0 ; j < now[0].length ; j++) {
                if (now[i][j] != goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void fillGraph(int N, int M, BufferedReader br) throws IOException {
        now = new int[N][M];
        goal = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                now[i][j] = Integer.parseInt(str.charAt(j) + "");
            }
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                goal[i][j] = Integer.parseInt(str.charAt(j) + "");
            }
        }

    }
}
