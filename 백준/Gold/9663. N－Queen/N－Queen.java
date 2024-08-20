import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {
    static int N;
    static int[] isVisit;
    static BufferedWriter bw;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        isVisit = new int[N + 1];

        for (int i = 1 ; i <= N ; i++) {
            isVisit[i] = 1;
            backtracking(1);
            isVisit[i] = 0; 
        }
        System.out.println(answer);
    }

    private static void backtracking(int row) throws IOException {
        if (row == N) answer++;
        if (row < N) {
            for (int i = 1 ; i <= N ; i++) {
                if (!isCross(i, row) && !isSameLine(i)) {
                    isVisit[i] = row + 1;
                    backtracking(row + 1);
                    isVisit[i] = 0;
                }
            }
        }
    }

    private static boolean isSameLine(int idx) {
        for (int i = 1 ; i <= N ; i++) {
            if (isVisit[i] != 0 && idx == i) return true;
        }
        return false;
    }

    private static boolean isCross(int idx, int row) {
        for (int i = 1 ; i <= N ; i++) {
            if (isVisit[i] != 0 && (abs(idx - i) == abs((row + 1) - isVisit[i]))) {
                return true;
            }
        }
        return false;
    }

}