import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] input = new int[n + 1][m + 1];
        for (int i = 1 ; i <= n ; i++) {
            String str = br.readLine();
            for (int j = 1 ; j <= m ; j++) {
                input[i][j] = Integer.parseInt(String.valueOf(str.charAt(j - 1)));
            }
        }
        int[][] psum = new int[n + 1][m + 1];
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= m ; j++) {
                psum[i][j] = psum[i][j - 1] + psum[i - 1][j] - psum[i - 1][j - 1] + input[i][j];
            }
        }

        int ans = 0;
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= m ; j++) {
                if (input[i][j] == 1) {
                    int idx = 1;
                    int res = 1;
                    while (i + idx <= n && j + idx <= m) {
                        int space = psum[i + idx][j + idx] - psum[i + idx][j - 1] - psum[i - 1][j + idx] + psum[i - 1][j - 1];
                        idx++;

                        if (space != idx * idx) break;

                        res = idx * idx;
                    }
                    ans = Math.max(ans, res);
                }
            }
        }
        System.out.println(ans);
    }
}
