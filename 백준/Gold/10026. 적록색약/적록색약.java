import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static char[][] mainArr;
    static boolean[][] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        mainArr = new char[n][n];
        isVisit = new boolean[n][n];
        int cnt = 0;

        for (int i = 0 ; i < n ; i++) { // 배열에 값 넣기
            String st = br.readLine();
            for (int j = 0 ; j < n ; j++) {
                mainArr[i][j] = st.charAt(j);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < n ; i++) { // 색맹X
            for (int j = 0 ; j < n ; j++) {
                if (!isVisit[i][j]) {
                    cnt++;
                    dfs(i, j);
                }
            }
        }
        sb.append(cnt + " ");
        cnt = 0;
        isVisit = new boolean[n][n];
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (mainArr[i][j] == 'G') mainArr[i][j] = 'R';
            }
        }
        for (int i = 0 ; i < n ; i++) { // 색맹X
            for (int j = 0 ; j < n ; j++) {
                if (!isVisit[i][j]) {
                    cnt++;
                    dfs(i, j);
                }
            }
        }
        sb.append(cnt);
        System.out.println(sb.toString());

    }

    public static void dfs(int r, int c) {
        isVisit[r][c] = true;

        if (r != 0 && (!isVisit[r - 1][c]) && mainArr[r - 1][c] == mainArr[r][c]) { // 위
            dfs(r - 1, c);
        }
        if (r != n - 1 && (!isVisit[r + 1][c]) && mainArr[r + 1][c] == mainArr[r][c]) { // 아래
            dfs(r + 1, c);
        }
        if (c != 0 && (!isVisit[r][c - 1]) && mainArr[r][c - 1] == mainArr[r][c]) { // 왼쪽
            dfs(r, c - 1);
        }
        if (c != n - 1 && (!isVisit[r][c + 1]) && mainArr[r][c + 1] == mainArr[r][c]) { // 오른쪽
            dfs(r, c + 1);
        }
    }
}
