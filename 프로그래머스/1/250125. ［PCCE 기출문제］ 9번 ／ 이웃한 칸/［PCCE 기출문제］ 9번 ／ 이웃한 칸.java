class Solution {
    public int solution(String[][] board, int h, int w) {
        int[] dy = {0, 1, -1, 0};
        int[] dx = {1, 0, 0, -1};
        int cnt = 0;
        for (int i = 0 ; i < 4 ; i++) {
            if (canGo(h + dy[i], w + dx[i], board.length, board[0].length) && board[h + dy[i]][w + dx[i]].equals(board[h][w])) cnt++;
        }
        return cnt;
    }
    
    private static boolean canGo(int y, int x, int a, int b) {
        return y >= 0 && y < a && x >= 0 && x < b;
    }
}