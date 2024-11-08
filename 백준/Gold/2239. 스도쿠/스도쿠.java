import java.io.*;
import java.util.*;

public class Main {
    static int[][] sudoku;
    static boolean finish = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];

        for (int i = 0 ; i < 9 ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < 9 ; j++) {
                sudoku[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }


        play(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 9 ; i++) {
            for (int j = 0 ; j < 9 ; j++) {
                sb.append(sudoku[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void play(int depth) {
        boolean start = true;
        // y를 depth의 몫, x를 나머지로 접근함
        if (depth == 81) {
            finish = true;
            return;
        }

        int y = depth / 9;
        int x = depth % 9;

        if (sudoku[y][x] != 0) play(depth + 1);
        else {
            for (int v = 1 ; v <= 9 ; v++) {
                if (len(x, v) && wid(y, v) && square(y, x, v)) {
                    sudoku[y][x] = v;
                    play(depth + 1);
                    if (finish) return;
                    sudoku[y][x] = 0;
                }

            }
        }
    }

    private static boolean square(int ii, int jj, int value) {
        // 1~3 -> 1, 3
        // 4~6 -> 4, 6
        // 7~9 -> 7, 9
        int y = range(ii);
        int x = range(jj);
        for (int i = y ; i <= y + 2 ; i++) {
            for (int j = x ; j <= x + 2 ; j++) {
                if (sudoku[i][j] == value) return false;
            }
        }
        return true;
    }

    private static boolean wid(int i, int value) {
        // 가로 탐색
        for (int j = 0 ; j < 9 ; j++) {
            if (sudoku[i][j] == value) return false;
        }
        return true;
    }

    private static boolean len(int j, int value) {
        // 세로 탐색
        for (int i = 0 ; i < 9 ; i++) {
            if (sudoku[i][j] == value) return false;
        }
        return true;
    }

    private static int range(int y) {
        if (y >= 0 && y <= 2) return 0;
        else if (y >= 3 && y <= 5) return 3;
        else return 6;
    }
}