import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static boolean[] isVisit;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isVisit = new boolean[N + 1];

        for (int i = 1 ; i <= N ; i++) {
            int[] arr = new int[M];
            arr[0] = i;
            isVisit[i] = true;
            backtracking(arr, 1);
            isVisit[i] = false;
        }
        bw.flush();
        bw.close();
    }

    private static void backtracking(int[] arr, int cnt) throws IOException {
        if (cnt == M) {
            // 출력
            for (int i = 0 ; i < M ; i++) {
                bw.write(arr[i] + " ");
            }
            bw.write("\n");
        } else {
            for (int i = arr[cnt - 1] + 1 ; i <= N ; i++) {
                if (!isVisit[i]) {
                    arr[cnt] = i;
                    isVisit[i] = true;
                    backtracking(arr, cnt + 1);
                    isVisit[i] = false;
                }
            }
        }
    }
}