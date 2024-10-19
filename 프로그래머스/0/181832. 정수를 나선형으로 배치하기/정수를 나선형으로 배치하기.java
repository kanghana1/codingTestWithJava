class Solution {
    public int[][] solution(int n) {
        int[][] arr = new int[n][n];
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int start = 1;
        int y = 0;
        int x = 0;
        int dirInit = 0;
        while (true) {
            if (start > n * n) break;
            
            if (!range(y, x, n) || (range(y, x, n) && arr[y][x] != 0)) {
                y -= direct[dirInit][0];
                x -= direct[dirInit][1];
                dirInit = (dirInit + 1) % 4;
                y += direct[dirInit][0];
                x += direct[dirInit][1];
            } else if (range(y, x, n) && arr[y][x] == 0) {
                arr[y][x] = start;
                start++;
                y += direct[dirInit][0];
                x += direct[dirInit][1];
            }
        }
        return arr;
    }
    
    private static boolean range(int y, int x, int n) {
        return y >= 0 && x >= 0 && y < n && x < n;
    }
}