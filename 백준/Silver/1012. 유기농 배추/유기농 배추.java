import java.util.*;
import java.io.*;

public class Main {

    static int col;
    static int row;
    static int cnt;
    static int[][] mainArr;
    static boolean[][] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numTestCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < numTestCase ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            cnt = 0;
            mainArr = new int[row][col];
            isVisit = new boolean[row][col];

            for (int j = 0 ; j < n ; j++) {
                st = new StringTokenizer(br.readLine());
                int cIdx = Integer.parseInt(st.nextToken());
                int rIdx = Integer.parseInt(st.nextToken());
                mainArr[rIdx][cIdx] = 1;
            }
            for (int j = 0 ; j < row ; j++) {
                for (int k = 0 ; k < col ; k++) {
                    if (!isVisit[j][k] && (mainArr[j][k] == 1)) {
                        dfs(j, k);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int r, int c) {
        isVisit[r][c] = true;
        if (r != 0 && (!isVisit[r - 1][c]) && mainArr[r - 1][c] == 1) { // 위
            dfs(r - 1, c);
        }
        if (r != row - 1 && (!isVisit[r + 1][c]) && mainArr[r + 1][c] == 1) { // 아래
            dfs(r + 1, c);
        }
        if (c != 0 && (!isVisit[r][c - 1]) && mainArr[r][c - 1] == 1) { // 왼쪽
            dfs(r, c - 1);
        }
        if (c != col - 1 && (!isVisit[r][c + 1]) && mainArr[r][c + 1] == 1) { // 오른쪽
            dfs(r, c + 1);
        }
    }
}
