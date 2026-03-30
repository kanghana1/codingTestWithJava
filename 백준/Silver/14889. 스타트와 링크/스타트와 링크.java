import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int[][] inputs;
    static int[][] powerArray;
    static boolean[] visited;
    static int power = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        powerArray = new int[N + 1][N + 1];
        makeInputs(br, N);
        makePowerArray(N);

        visited[1] = true;
        teamMatch(N / 2, 1, 1);
        System.out.println(power);
    }

    private static void teamMatch(int wholeCnt, int nowIdx, int cnt) {
        if (cnt == wholeCnt) {
            int visitedPower = calculatePower(true);
            int unVisitedPower = calculatePower(false);
            power = Math.min(power, Math.abs(visitedPower - unVisitedPower));
            return;
        }

        for (int i = nowIdx + 1 ; i < visited.length ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                teamMatch(wholeCnt, i, cnt + 1);
                visited[i] = false;
            }
        }

    }

    private static int calculatePower(boolean isVisited) {
        int power = 0;
        for (int i = 1 ; i < visited.length ; i++) {
            if (visited[i] == isVisited) {
                for (int j = i ; j < visited.length ; j++) {
                    if (visited[j] == isVisited && i != j) {
                        power += powerArray[i][j];
                    }
                }
            }
        }
        return power;
    }

    private static void makePowerArray(int n) {
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= n ; j++) {
                powerArray[i][j] = inputs[i][j] + inputs[j][i];
                powerArray[j][i] = powerArray[i][j];
            }
        }
    }

    private static void makeInputs(BufferedReader br, int N) throws IOException {
        inputs = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
