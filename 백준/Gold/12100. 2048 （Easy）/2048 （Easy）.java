import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = fillBoard(br, N);
        playGame(0, board);
        System.out.println(ans);
    }

    private static void playGame(int cnt, int[][] board) {
        if (cnt == 5) {
            int max = findMaxBlock(board);
            ans = Math.max(ans, max);
            return;
        }

        playGame(cnt + 1, moveLeft(board));
        playGame(cnt + 1, moveUp(board));
        playGame(cnt + 1, moveRight(board));
        playGame(cnt + 1, moveDown(board));
    }

    private static int findMaxBlock(int[][] board) {
        int max = 0;
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board.length ; j++) {
                if (board[i][j] > max) {
                    max = board[i][j];
                }
            }
        }
        return max;
    }

    private static int[][] moveDown(int[][] board) {
        int n = board.length;
        int[][] newBoard = new int[n][n];

        for (int j = 0 ; j < n ; j++) {
            int idx = n - 1;
            for (int i = n - 1 ; i >= 0 ; i--) {
                if (board[i][j] == 0) continue;
                if (newBoard[idx][j] == 0) {
                    newBoard[idx][j] = board[i][j];
                } else if (newBoard[idx][j] == board[i][j]) {
                    newBoard[idx--][j] *= 2;
                } else {
                    newBoard[--idx][j] = board[i][j];
                }
            }
        }
        return newBoard;
    }

    private static int[][] moveRight(int[][] board) {
        int n = board.length;
        int[][] newBoard = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            int idx = n - 1;
            for (int j = n - 1 ; j >= 0 ; j--) {
                if (board[i][j] == 0) continue;
                if (newBoard[i][idx] == 0) {
                    newBoard[i][idx] = board[i][j];
                } else if (newBoard[i][idx] == board[i][j]) {
                    newBoard[i][idx--] *= 2;
                } else {
                    newBoard[i][--idx] = board[i][j];
                }
            }
        }
        return newBoard;
    }

    private static int[][] moveUp(int[][] board) {
        int n = board.length;
        int[][] newBoard = new int[n][n];

        for (int j = 0 ; j < n ; j++) {
            int idx = 0;
            for (int i = 0 ; i < n ; i++) {
                if (board[i][j] == 0) continue;
                if (newBoard[idx][j] == 0) {
                    newBoard[idx][j] = board[i][j];
                } else if (newBoard[idx][j] == board[i][j]) {
                    newBoard[idx++][j] *= 2;
                } else {
                    newBoard[++idx][j] = board[i][j];
                }
            }
        }

        return newBoard;
    }

    private static int[][] moveLeft(int[][] board) {
        int n = board.length;
        int[][] newBoard = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            int idx = 0;
            for (int j = 0 ; j < n ; j++) {
                if (board[i][j] == 0) continue;
                if (newBoard[i][idx] == 0) {
                    newBoard[i][idx] = board[i][j];
                } else if (newBoard[i][idx] == board[i][j]) {
                    newBoard[i][idx++] *= 2;
                } else {
                    newBoard[i][++idx] = board[i][j];
                }
            }
        }

        return newBoard;
    }

    private static int[][] fillBoard(BufferedReader br, int n) throws IOException {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return board;
    }
}
