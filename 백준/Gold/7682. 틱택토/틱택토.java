import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. O, X의 개수 비율이 안 맞는 경우 -> invalid
 * 2. 게임이 끝났어야 하는 상태인데 게임이 끝나지 않은 경우 -> invalid
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] board = new char[3][3];
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();

            // 종료조건
            if (str.equals("end")) {
                break;
            }

            int xCnt = 0;
            int oCnt = 0;
            for (int i = 0 ; i < 9 ; i++) {
                board[i / 3][i % 3] = str.charAt(i);
                if (board[i / 3][i % 3] == 'X') {
                    xCnt++;
                } else if (board[i / 3][i % 3] == 'O') {
                    oCnt++;
                }
            }

            String ans = solve(board, xCnt, oCnt);
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

    private static String solve(char[][] board, int xCnt, int oCnt) {
        if (xCnt == oCnt) {
            if (isWin(board, 'O')) {
                if (isWin(board, 'X')) {
                    return "invalid";
                } else {
                    return "valid";
                }
            } else return "invalid";
        } else if (xCnt == oCnt + 1) {
            if (isWin(board, 'X')) {
                if (isWin(board, 'O')) {
                    return "invalid";
                } else return "valid";

            } else {
                if (isFull(oCnt, xCnt)) {
                    if (isWin(board, 'O')) {
                        return "invalid";
                    } else return "valid";
                }
                 else return "invalid";
            }
        } else {
            return "invalid";
        }
    }

    private static boolean isFull(int oCnt, int xCnt) {
        return oCnt + xCnt == 9;
    }

    private static boolean isWin(char[][] board, char who) {
        for (int i = 0 ; i < 3 ; i++) {
            if (board[i][0] == who && board[i][1] == who && board[i][2] == who) {
                return true;
            }
            if (board[0][i] == who && board[1][i] == who && board[2][i] == who) {
                return true;
            }
        }

        if (board[0][0] == who && board[1][1] == who && board[2][2] == who) {
            return true;
        }

        if (board[0][2] == who && board[1][1] == who && board[2][0] == who) {
            return true;
        }

        return false;
    }
}
